package ua.com.blizartproduction.infohub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "vacancies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    @Id
    @Column(length = 50)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", name = "short_description")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String requirements;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.ACTIVE;

    @Column(name = "assigned_to", length = 100)
    private String assignedTo;

    @Column(name = "filled_date")
    private LocalDate filledDate;

    @Column(name = "archived_date")
    private LocalDate archivedDate;

    public enum Status {
        ACTIVE,
        FILLED,
        ARCHIVED
    }
}