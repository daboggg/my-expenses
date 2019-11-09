package ru.zinin.myexpenses.dto;

import lombok.Data;
import ru.zinin.myexpenses.model.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategoryDto {

    public static CategoryDto getCategoryDto(Category category) {
        CategoryDto dto = new CategoryDto(category.getId(), category.getName());
        return dto;
    }

    public static List<CategoryDto> getListCategoryDto(List<Category> categories) {
        return categories.stream()
                .map(cat -> CategoryDto.getCategoryDto(cat))
                .collect(Collectors.toList());
    }

    private Long id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
