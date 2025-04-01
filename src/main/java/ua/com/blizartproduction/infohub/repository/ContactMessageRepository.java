package ua.com.blizartproduction.infohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.blizartproduction.infohub.entity.ContactMessage;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}