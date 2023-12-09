package udacity.project1.controller;

import udacity.project1.models.Credential;
import udacity.project1.models.File;
import udacity.project1.models.Note;
import udacity.project1.services.CredentialService;
import udacity.project1.services.FileService;
import udacity.project1.services.NoteService;
import udacity.project1.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("home")
public class HomeController {
    private final UserService userService;
    private final NoteService noteService;
    private final FileService fileService;
    private final CredentialService credentialService;

    /* LOAD SCREEN HOME */
    @GetMapping()
    public String homeView(Model model) {
        int userId = userService.getCurrentUserId();

        model.addAttribute("notes", noteService.getAllByUser(userId));
        model.addAttribute("files", fileService.getAllByUser(userId));
        model.addAttribute("credentials", credentialService.getAllByUser(userId));

        return "home";
    }
        
    /* NOTE */        
    @PostMapping("/note")
    public String postNote(@ModelAttribute("noteModel") Note note, Model model) {
        int userId = userService.getCurrentUserId();
        note.setUserId(userId);

        noteService.upsert(note);

        return "redirect:/home";
    }
    
    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable("id") Integer id) {
        noteService.deleteById(id);

        return "redirect:/home";
    }

    /* FILE */ 
    @GetMapping("/file/delete/{id}")
    public String deleteFile(@PathVariable("id") Integer id) {

        if (fileService.deleteById(id)) {
            return "redirect:/home";
        } else {
            return "redirect:/home?error=File Not Exist";
        }
    }
    
    @PostMapping("/file")
    public String postFile(@RequestParam("fileUpload") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            return "redirect:/home?error=Empty File";
        }

        if (fileService.checkExistFile(file.getOriginalFilename())) {
            return "redirect:/home?error=Duplicate Filename";
        }

        int userId = userService.getCurrentUserId();
        try {
            fileService.add(file, userId);
        } catch (IOException e) {
            return "redirect:/home?error=Error happened when upload file";
        }

        return "redirect:/home";
    }


    @GetMapping("/file/view/{id}")
    public void viewFile(@PathVariable("id") Integer fileId, HttpServletResponse response) throws IOException {
        File file = fileService.findById(fileId);

        byte[] fileBytes = file.getFileData();
        response.setContentType(file.getContentType());
        response.setHeader("Content-Disposition", "inline");
        response.getOutputStream().write(fileBytes);
    }
    
    /* CREDENTIAL */ 

    @PostMapping("/credential")
    public String postCredential(@ModelAttribute("credentialModel") Credential credential, Model model) {
    	
        int userId = userService.getCurrentUserId();
        credential.setUserId(userId);
        credentialService.upsert(credential);
        return "redirect:/home";
    }
    
    @GetMapping("/credential/delete/{id}")
    public String deleteCredential(@PathVariable("id") Integer id) {

        if (credentialService.deleteById(id)) {
            return "redirect:/home";
        } else {
            return "redirect:/home?error=File Not Exist";
        }
    }

}
