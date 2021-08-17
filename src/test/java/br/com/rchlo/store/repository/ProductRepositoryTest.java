package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Category;
import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Comparator;
import java.util.List;

import static br.com.rchlo.store.builder.CategoryBuilder.aCategory;
import static br.com.rchlo.store.builder.ProductBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/schema.sql")
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void loadData() {
        Category category = entityManager.persist(aCategory().build());
        entityManager.persist(aProduct(category)
                .withCode(7L)
                .withName("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco")
                .withColor(Color.WHITE)
                .build());
        entityManager.persist(anotherProduct(category)
                .withCode(1L)
                .withName("Regata Infantil Mario Bros Branco")
                .withColor(Color.WHITE)
                .build());
        entityManager.persist(yetAnotherProduct(category)
                .withCode(2L)
                .withName("Blusa de Moletom Infantil Mario Bros Vermelho")
                .withColor(Color.RED)
                .build());
    }

    @Test
    void shouldListAllProductsOrderedByName() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));
        List<Product> products = productRepository.findAllWithCategoryAndImages(pageRequest);

        assertEquals(3, products.size());

        Product aProduct = products.get(0);
        assertEquals(2L, aProduct.getCode());
        assertEquals("Blusa de Moletom Infantil Mario Bros Vermelho", aProduct.getName());

        Product anotherProduct = products.get(1);
        assertEquals(7L, anotherProduct.getCode());
        assertEquals("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco", anotherProduct.getName());

        Product yetAnotherProduct = products.get(2);
        assertEquals(1L, yetAnotherProduct.getCode());
        assertEquals("Regata Infantil Mario Bros Branco", yetAnotherProduct.getName());
    }

    @Test
    void shouldRetrieveProductsByColor() {
        List<ProductByColorDto> productsByColor = productRepository.productsByColor();
        productsByColor.sort(Comparator.comparing(ProductByColorDto::getColor));

        assertEquals(2, productsByColor.size());

        ProductByColorDto aProductDto = productsByColor.get(0);
        assertEquals("Branca", aProductDto.getColor());
        assertEquals(2, aProductDto.getAmount());

        ProductByColorDto anotherProductDto = productsByColor.get(1);
        assertEquals("Vermelha", anotherProductDto.getColor());
        assertEquals(1, anotherProductDto.getAmount());
    }

}