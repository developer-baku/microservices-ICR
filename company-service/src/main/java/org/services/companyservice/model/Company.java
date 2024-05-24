package org.services.companyservice.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Builder
@Table(name = "company")
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double averageRating;
//    @JsonIgnore
//    @OneToMany(mappedBy = "company")
//    private List<Job> jobs;
//
//    @OneToMany(mappedBy = "company")
//    @JsonIgnore
//    private List<Review> reviews;

}
