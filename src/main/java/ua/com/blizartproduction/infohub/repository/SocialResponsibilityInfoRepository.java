package ua.com.blizartproduction.infohub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.blizartproduction.infohub.entity.SocialResponsibilityInfo;
import java.util.Optional;

@Repository
public interface SocialResponsibilityInfoRepository extends JpaRepository<SocialResponsibilityInfo, Long> {
    Optional<SocialResponsibilityInfo> findFirstByOrderByIdAsc();
}