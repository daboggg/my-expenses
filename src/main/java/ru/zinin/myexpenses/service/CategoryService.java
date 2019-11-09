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
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    TokenHolder tokenHolder;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<CategoryDto> createCategory(Category category) throws InvalidToken, CategoryAlreadyExist {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();

        User userById = userRepo.getUserById(tokenHolder.getIdUser());

        for (Category userByIdCategory : userById.getCategories()) {
            if (userByIdCategory.getName().equals(category.getName())) {
                throw new CategoryAlreadyExist(category.getName());
            }
        }
        category.setUsr(userById);
        userById.getCategories().add(category);
        User saveUser = userRepo.save(userById);
        Optional<Category> respCat = saveUser.getCategories().stream().filter(cat -> cat.getName().equals(category.getName())).findFirst();

        return ResponseEntity.ok(CategoryDto.getCategoryDto(respCat.get()));
    }

    public ResponseEntity<List<CategoryDto>> getCategories() throws InvalidToken {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();

        User user = userRepo.getUserById(tokenHolder.getIdUser());

        return ResponseEntity.ok(CategoryDto.getListCategoryDto(user.getCategories()));
    }

    public ResponseEntity<CategoryDto> updateCategory(Category category) throws InvalidToken {
        if (!tokenHolder.isValidToken()) {
            throw new InvalidToken();
        }
        tokenHolder.updateTimeValidityToken();

        Category categoryFromDb = categoryRepo.getById(category.getId());
        categoryFromDb.setName(category.getName());
        Category savedCategory = categoryRepo.save(categoryFromDb);
        return ResponseEntity.ok(CategoryDto.getCategoryDto(savedCategory));
    }
}
