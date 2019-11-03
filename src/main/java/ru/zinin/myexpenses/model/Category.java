package ru.zinin.myexpenses.model;

import lombok.Data;

import javax.persistence.*;

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
    private User usr;


}
