package ru.zinin.myexpenses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Double amount;
    private boolean income;
    private Long creationDate = System.currentTimeMillis();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    public Record() {
    }

    public Record(String description, Double amount, boolean income, Category category) {
        this.description = description;
        this.amount = amount;
        this.income = income;
        this.category = category;
    }
}
