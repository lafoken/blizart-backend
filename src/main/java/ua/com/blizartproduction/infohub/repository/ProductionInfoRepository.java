package ua.com.blizartproduction.infohub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.blizartproduction.infohub.entity.ProductionInfo;
import java.util.Optional;

@Repository
public interface ProductionInfoRepository extends JpaRepository<ProductionInfo, Long> {
    Optional<ProductionInfo> findFirstByOrderByIdAsc();
}