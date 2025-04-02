package ua.com.blizartproduction.infohub.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.com.blizartproduction.infohub.dto.ContactFormDto;
import ua.com.blizartproduction.infohub.dto.ApiResponseDto;
import ua.com.blizartproduction.infohub.entity.ContactMessage;
import ua.com.blizartproduction.infohub.repository.ContactMessageRepository;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);
    private final ContactMessageRepository contactMessageRepository;

    @Override
    public ApiResponseDto handleContactForm(ContactFormDto formData) {
        log.info("Received contact form submission: {}", formData);
        try {
            ContactMessage message = new ContactMessage();
            message.setName(formData.getName());
            message.setEmail(formData.getEmail());
            message.setPhone(formData.getPhone());
            message.setSubject(formData.getSubject());
            message.setMessage(formData.getMessage());

            contactMessageRepository.save(message);
            log.info("Contact message saved successfully with ID: {}", message.getId());
            return new ApiResponseDto(true, "Повідомлення успішно отримано та збережено.");

        } catch (Exception e) {
            log.error("Error saving contact message: {}", e.getMessage(), e);
            return new ApiResponseDto(false, "Помилка збереження повідомлення. Спробуйте пізніше.");
        }
    }
}