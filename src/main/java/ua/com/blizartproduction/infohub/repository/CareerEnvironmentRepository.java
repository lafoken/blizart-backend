package ua.com.blizartproduction.infohub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.blizartproduction.infohub.entity.CareerEnvironment;
import java.util.Optional;

@Repository
public interface CareerEnvironmentRepository extends JpaRepository<CareerEnvironment, Long> {
    Optional<CareerEnvironment> findFirstByOrderByIdAsc();
}
