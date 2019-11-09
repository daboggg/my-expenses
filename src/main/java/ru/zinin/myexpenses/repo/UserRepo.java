package ru.zinin.myexpenses.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zinin.myexpenses.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User getUserByEmail(String email);

    User getUserById(Long id);
}
