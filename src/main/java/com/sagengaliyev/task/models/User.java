package com.sagengaliyev.task.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Name")
    private String name;
    @Column(name = "Email", unique = true)
    private String email;
    @Column(name = "Password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<Image> images = new ArrayList<>();
    private LocalDateTime dateOfCreated;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "new_status")
    private String newStatus;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }
}
