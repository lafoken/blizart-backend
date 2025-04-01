package ua.com.blizartproduction.infohub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.blizartproduction.infohub.entity.CompanyDetails;
import java.util.Optional;

@Repository
public interface CompanyDetailsRepository extends JpaRepository<CompanyDetails, Long> {
    Optional<CompanyDetails> findFirstByOrderByIdAsc(); // Method to get the single record
}