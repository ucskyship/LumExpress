package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
