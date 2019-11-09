package ru.zinin.myexpenses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "categ")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    @JsonIgnore
    private User usr;


    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Record> records;

    public Category() {
    }

    public Category(String name, User usr) {
        this.name = name;
        this.usr = usr;
    }
}
