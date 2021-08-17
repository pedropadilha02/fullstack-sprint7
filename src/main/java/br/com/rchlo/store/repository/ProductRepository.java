package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select distinct p from Product p join fetch p.category left join fetch p.images order by p.name")
    List<Product> findAllWithCategoryAndImages(Pageable pageable);

    @Query("select distinct p from Product p left join fetch p.availableSizes where p in :products order by p.name")
    List<Product> findAllAvailableSizesOfGivenProducts(@Param("products") List<Product> products);

    /** porque este método default?
        @see https://vladmihalcea.com/hibernate-multiplebagfetchexception/ */
    default List<Product> findAllWithCategoryAndImagesAndAvailableSizes(Pageable pageable) {
        List<Product> products = findAllWithCategoryAndImages(pageable);
        return findAllAvailableSizesOfGivenProducts(products);
    }

    @Query("select new br.com.rchlo.store.dto.ProductByColorDto(p.color, count(p)) from Product p group by p.color")
    List<ProductByColorDto> productsByColor();

}
