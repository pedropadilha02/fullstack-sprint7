package br.com.rchlo.store.dto;

import br.com.rchlo.store.domain.Category;

public class CategoryDto {
    private String name;
    private String slug;

    public CategoryDto(Category category) {
        this.name = category.getName();
        this.slug = category.getSlug();
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }
}
