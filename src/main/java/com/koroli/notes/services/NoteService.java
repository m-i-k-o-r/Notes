package com.koroli.notes.services;

import com.koroli.notes.dto.NoteDto;
import com.koroli.notes.models.Note;
import com.koroli.notes.repos.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteDto createNote(String name, String text) {

        Note note = noteRepository.save(Note.builder()
                .name(name)
                .text(text)
                .date(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .build());
        return NoteDto.builder()
                .id(note.getId())
                .name(note.getName())
                .text(note.getText())
                .date(note.getDate())
                .build();
    }
    public NoteDto getNote(Long id){
        Note note = noteRepository.findById(id).orElseThrow();
        return NoteDto.builder()
                .id(note.getId())
                .name(note.getName())
                .text(note.getText())
                .date(note.getDate())
                .build();
    }
    public List<NoteDto> getAllNode(){
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map(note -> NoteDto.builder()
                .id(note.getId())
                .name(note.getName())
                .text(note.getText())
                .date(note.getDate())
                .build())
                .toList();
    }
    public NoteDto updateNote(Long id, String name, String text) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setName(name);
        note.setText(text);
        note.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        note = noteRepository.save(note);
        return NoteDto.builder()
                .id(note.getId())
                .name(note.getName())
                .text(note.getText())
                .date(note.getDate())
                .build();
    }
    public void deleteNote(Long id){
        noteRepository.delete(noteRepository.findById(id).orElseThrow());
    }
}
