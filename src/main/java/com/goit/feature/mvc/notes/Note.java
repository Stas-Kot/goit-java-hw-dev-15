package com.goit.feature.mvc.notes;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Note {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column
    private String title;
    @Column
    private String content;
}
