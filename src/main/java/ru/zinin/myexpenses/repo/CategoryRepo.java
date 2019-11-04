package ru.zinin.myexpenses.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zinin.myexpenses.model.Category;
import ru.zinin.myexpenses.model.User;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> getAllByUsr(User user);

    Category getByName(String name);
}
