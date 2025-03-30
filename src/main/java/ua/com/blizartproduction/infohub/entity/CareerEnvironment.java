package ua.com.blizartproduction.infohub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "career_environment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerEnvironment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "careerEnvironment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Benefit> benefits;
}