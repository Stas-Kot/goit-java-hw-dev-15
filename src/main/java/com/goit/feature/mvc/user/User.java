package com.goit.feature.mvc.user;

import com.goit.feature.mvc.notes.Note;
import com.goit.feature.mvc.user.roles.EUserRole;
import com.goit.feature.mvc.user.roles.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name="users")
@Entity
public class User {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "id")
//    private long id;
    @Id
//    @Column(name = "id")
    @Column(name = "name", nullable = false, unique = true, length = 200)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private EUserRole role;

//    @ManyToOne
//    @JoinColumn(name="role_id", nullable=false)
//    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Note> notes;
}
