package org.services.jobservice.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseCompany {
    private Long id;
    private String name;
    private String description;
//    private List<Review> reviews;
//
//    private List<Job> jobs;
}
