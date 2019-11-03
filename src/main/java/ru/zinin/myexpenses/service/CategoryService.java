package ru.zinin.myexpenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zinin.myexpenses.component.TokenHolder;
import ru.zinin.myexpenses.dto.CategoryDto;
import ru.zinin.myexpenses.exception.InvalidToken;
import ru.zinin.myexpenses.model.User;
import ru.zinin.myexpenses.repo.CategoryRepo;
import ru.zinin.myexpenses.repo.UserRepo;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    TokenHolder tokenHolder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    public ResponseEntity<List<CategoryDto>> getCategory() throws InvalidToken {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();
        User user = userRepo.getUserById(tokenHolder.getIdUser());
        return ResponseEntity.ok(CategoryDto.getListCategoryDto(categoryRepo.getAllByUsr(user)));
    }
}
