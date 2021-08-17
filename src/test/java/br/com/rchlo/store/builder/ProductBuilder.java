package br.com.rchlo.store.builder;

import br.com.rchlo.store.domain.Category;
import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;

import java.math.BigDecimal;

public class ProductBuilder {

    private Long code;
    private String name;
    private String description;
    private String slug;
    private String brand;
    private BigDecimal price;
    private BigDecimal discount;
    private Color color;
    private Integer weightInGrams;

    private ProductBuilder() {
    }

    public ProductBuilder withCode(Long code) {
        this.code = code;
        return this;
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public ProductBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductBuilder withDiscount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public ProductBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    public ProductBuilder withWeightInGrams(Integer weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public Product build() {
        return new Product(code, name, description, slug, brand, price, discount, color, weightInGrams);
    }

    public static ProductBuilder aProduct(Category category) {
        return new ProductBuilder()
                .withCode(7L)
                .withName("Jaqueta Puffer Juvenil Com Capuz Super Mario Branco")
                .withDescription("A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.")
                .withSlug("jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku")
                .withBrand("Nintendo")
                .withPrice(new BigDecimal("199.90"))
                .withDiscount(null)
                .withColor(Color.WHITE)
                .withWeightInGrams(147);
    }

    public static ProductBuilder anotherProduct(Category category) {
        return new ProductBuilder()
                .withCode(1L)
                .withName("Regata Infantil Mario Bros Branco")
                .withDescription("A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!")
                .withSlug("regata-infantil-mario-bros-branco-14040174_sku")
                .withBrand("Nintendo")
                .withPrice(new BigDecimal("29.90"))
                .withDiscount(null)
                .withColor(Color.WHITE)
                .withWeightInGrams(98);
    }

    public static ProductBuilder yetAnotherProduct(Category category) {
        return new ProductBuilder()
                .withCode(2L)
                .withName("Blusa de Moletom Infantil Mario Bros Vermelho")
                .withDescription("A Blusa de Moletom Infantil Mario Bros Vermelho é quentinha e divertida!")
                .withSlug("blusa-infantil-moletom-mario-bros-vermelho-14125129_sku")
                .withBrand("Nintendo")
                .withPrice(new BigDecimal("79.90"))
                .withDiscount(null)
                .withColor(Color.RED)
                .withWeightInGrams(126);
    }

}