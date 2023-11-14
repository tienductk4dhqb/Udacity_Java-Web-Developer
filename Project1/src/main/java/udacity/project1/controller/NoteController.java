package udacity.project1.controller;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import udacity.project1.model.Note;
import udacity.project1.service.NoteService;
import udacity.project1.service.UserService;

import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final UserService userService;
    private final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    public List<String> validate(Map<String, String> data) {
        List<String> errors = new ArrayList<String>();

        if (data.get("noteTitle").isEmpty())
            errors.add("Note title must not be empty.");

        if (data.get("noteDescription").isEmpty())
            errors.add("Note description must not be empty.");

        return errors;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createView(
        HttpServletResponse response,
        Authentication authentication,
        @RequestParam Map<String, String> data,
        Model model
    ) {
        var UID = userService.getUser(authentication.getName()).getUserId();
        noteService.add(
            new Note(
                null,
                data.get("noteTitle"),
                data.get("noteDescription"),
                UID
            )
        );

        var errors = validate(data);
        model.addAttribute("success", true);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return "result";
    }

    @PutMapping(value = "/{noteId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editView(
        HttpServletResponse response,
        Authentication authentication,
        @RequestParam Map<String, String> data,
        Model model
    ) {
        var UID = userService.getUser(authentication.getName()).getUserId();
        noteService.add(
            new Note(
                Integer.parseInt(data.get("noteId")),
                data.get("noteTitle"),
                data.get("noteDescription"),
                UID
            )
        );

        var errors = validate(data);
        model.addAttribute("success", true);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return "result";
    }

    @RequestMapping(value="/home/", method=RequestMethod.DELETE)
    public String removeView(
        HttpServletResponse response,
        @PathVariable Integer noteId,
        Authentication authentication,
        Model model
    ) {
        List<String> errors = new ArrayList<String>();
        model.addAttribute("success", true);
        try {
            var UID = userService.getUser(authentication.getName()).getUserId();
            noteService.remove(new Note(noteId, UID));

        } catch (Exception ignore) {
            errors.add("There was a server error. The note was not removed.");
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

        return "result";
    }

}
