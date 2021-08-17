package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Product;
import br.com.rchlo.store.dto.ProductByColorDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select distinct p from Product p join fetch p.category left join fetch p.images left join fetch p.availableSizes")
    List<Product> findAllWithCategoryAndImagesAndAvailableSizes(Pageable pageable);

    @Query("select new br.com.rchlo.store.dto.ProductByColorDto(p.color, count(p)) from Product p group by p.color")
    List<ProductByColorDto> productsByColor();

}
