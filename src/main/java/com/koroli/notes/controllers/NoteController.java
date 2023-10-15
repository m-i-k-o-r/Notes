package com.koroli.notes.controllers;

import com.koroli.notes.dto.NoteDto;
import com.koroli.notes.dto.requests.NoteRequest;
import com.koroli.notes.services.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping()
    @Operation(summary = "get all notes", responses = {
            @ApiResponse(responseCode = "200", description = "Notes found", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = NoteDto.class)))
            }),
            @ApiResponse(responseCode = "404", description = "No notes found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            })
    })
    public List<NoteDto> getAllNote() {
        return noteService.getAllNode();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get note by id", responses = {
            @ApiResponse(responseCode = "200", description = "Note found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NoteDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "No note found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            })
    })
    public NoteDto getNoteById(@PathVariable Long id) {
        return noteService.getNote(id);
    }

    @PostMapping("/create")
    @Operation(summary = "Create note", responses = {
            @ApiResponse(responseCode = "201", description = "Note create", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NoteDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "invalid fields to creating", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "An error occurred while creating", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            })
    })
    public NoteDto createNote(@RequestBody NoteRequest noteRequest) {
        return noteService.createNote(noteRequest.getName(), noteRequest.getText());
    }

    @PostMapping("/{id}/updateNote")
    @Operation(summary = "update note", responses = {
            @ApiResponse(responseCode = "200", description = "Note update", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NoteDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "invalid fields to updating", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            }),
            @ApiResponse(responseCode = "404", description = "No note found to updating", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "An error occurred while updating", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            })
    })
    public NoteDto updateNoteById(@PathVariable Long id, @RequestBody NoteRequest noteRequest) {
        return noteService.updateNote(id, noteRequest.getName(), noteRequest.getText());
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete note", responses = {
            @ApiResponse(responseCode = "200", description = "Note delete"),
            @ApiResponse(responseCode = "404", description = "No note found to deleting", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))
            })
    })
    public void deleteById(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}