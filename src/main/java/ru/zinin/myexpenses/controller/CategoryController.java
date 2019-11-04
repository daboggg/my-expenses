package ru.zinin.myexpenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zinin.myexpenses.dto.CategoryDto;
import ru.zinin.myexpenses.exception.CategoryAlreadyExist;
import ru.zinin.myexpenses.exception.InvalidToken;
import ru.zinin.myexpenses.model.Category;
import ru.zinin.myexpenses.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/category")
    public ResponseEntity<List<CategoryDto>> getCategory() throws InvalidToken {
        return categoryService.getCategory();
    }

    @CrossOrigin(methods = RequestMethod.PUT)
    @PutMapping("/api/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody Category category) throws InvalidToken, CategoryAlreadyExist {
        return categoryService.createCategory(category);
    }
}
