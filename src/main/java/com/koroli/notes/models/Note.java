package com.koroli.notes.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(55)")
    private String name;

    @Column(name = "text", nullable = false, columnDefinition = "text")
    private String text;

    @Column(name = "create_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime date;
}
