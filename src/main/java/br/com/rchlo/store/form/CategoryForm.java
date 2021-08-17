package br.com.rchlo.store.form;

import br.com.rchlo.store.domain.Category;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.function.IntSupplier;

public class CategoryForm {

    @NotBlank @Size(max = 255)
    private final String name;

    @NotBlank @Size(max = 255) @Pattern(regexp = "[\\w-]+")
    private final String slug;

    @JsonCreator
    public CategoryForm(Category category) {
        this.name = category.getName();
        this.slug = category.getSlug();
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public Category convert(IntSupplier oldPositionSupplier) {
        int lastPosition = oldPositionSupplier.getAsInt() + 1;
        return new Category(this.name, this.slug, lastPosition);
    }

}
