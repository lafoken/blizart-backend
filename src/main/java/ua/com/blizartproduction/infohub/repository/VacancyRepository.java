package ua.com.blizartproduction.infohub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.blizartproduction.infohub.entity.Vacancy;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, String> {

    List<Vacancy> findByStatusOrderByIdAsc(Vacancy.Status status);

    List<Vacancy> findAllByOrderByIdAsc();

}