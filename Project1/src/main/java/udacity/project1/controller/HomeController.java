package udacity.project1.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import udacity.project1.service.CredentialService;
import udacity.project1.service.FileService;
import udacity.project1.service.NoteService;
import udacity.project1.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final NoteService noteService;
    private final FileService fileService;
    private final CredentialService credentialService;

    public HomeController(
    		UserService userService,
    		NoteService noteService,
    		FileService fileService,
    		CredentialService credentialService
    ) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String homeView(Authentication authentication, Model model) {

        try {
            var UID = userService.getUser(
                authentication.getName()
            ).getUserId()
             .toString();

            model.addAttribute("notes", noteService.allBy(UID));
            model.addAttribute("files", fileService.allBy(UID));
            model.addAttribute("credentials", credentialService.allBy(UID));

        } catch (Exception ignored) {
            return "redirect:/logout-success";
        }

        return "home";
    }

}
