package org.services.jobservice.dto;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobCompanyReviews {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private ResponseCompany Company;
    private List<ResponseReview> reviews;
}
