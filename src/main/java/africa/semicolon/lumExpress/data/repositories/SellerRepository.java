package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
