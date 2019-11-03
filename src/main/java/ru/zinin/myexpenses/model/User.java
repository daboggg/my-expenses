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
    private String firstName;

    @OneToMany(mappedBy = "usr",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        categories.add(category);
        category.setUsr(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.setUsr(null);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
        categories.forEach(cat-> cat.setUsr(this));
    }
}
