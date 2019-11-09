package ru.zinin.myexpenses.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "usr",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Category> categories;

    public User() {
    }

    public User(String email, String password, String name, List<Category> categories) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.categories = categories;
    }
}
