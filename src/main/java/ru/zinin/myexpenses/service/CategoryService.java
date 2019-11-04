package ru.zinin.myexpenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zinin.myexpenses.component.TokenHolder;
import ru.zinin.myexpenses.dto.CategoryDto;
import ru.zinin.myexpenses.exception.CategoryAlreadyExist;
import ru.zinin.myexpenses.exception.InvalidToken;
import ru.zinin.myexpenses.model.Category;
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

    public ResponseEntity<CategoryDto> createCategory(Category category) throws InvalidToken, CategoryAlreadyExist {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();
        User user = userRepo.getUserById(tokenHolder.getIdUser());

        for (Category userCategory : user.getCategories()) {
            if (userCategory.getName().equals(category.getName())) {
                throw new CategoryAlreadyExist(category.getName());
            }
        }

        category.setUsr(user);
        user.getCategories().add(category);
        userRepo.save(user);
        Category byName = categoryRepo.getByName(category.getName());
        return ResponseEntity.ok(CategoryDto.getCategoryDto(byName));
    }
}
