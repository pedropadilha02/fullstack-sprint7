package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.CategoryDto;
import br.com.rchlo.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<CategoryDto> categories() {
        return categoryRepository.findAllByOrderByPosition().stream().map(CategoryDto::new).collect(Collectors.toList());
    }

}
