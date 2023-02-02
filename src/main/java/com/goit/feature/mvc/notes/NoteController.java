package com.goit.feature.mvc.notes;

import com.goit.feature.mvc.notes.dto.NoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getList() {
        ModelAndView result = new ModelAndView("notes/list");
        List<NoteDto> notes = noteService.listAll().stream().map(NoteDto::fromNote).collect(Collectors.toList());
        result.addObject("notes", notes);
        return result;
    }

    @PostMapping("/delete")
    public RedirectView delete(@RequestParam(name = "id") long noteId) {
        noteService.deleteById(noteId);
        return new RedirectView("list");
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id") long id) {
        ModelAndView result = new ModelAndView("notes/edit");
        Note note = noteService.getById(id);
        result.addObject("note", NoteDto.fromNote(note));
        return result;
    }

    @PostMapping("/edit")
    public RedirectView saveUpdatedNote(NoteDto noteDto) {
        noteService.update(noteDto.toNote());
        return new RedirectView("list");
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("notes/create");
    }


    @PostMapping("/create")
    public RedirectView saveCreatedNote(NoteDto noteDto) {
        noteService.add(noteDto.toNote());
        return new RedirectView("list");
    }
}
