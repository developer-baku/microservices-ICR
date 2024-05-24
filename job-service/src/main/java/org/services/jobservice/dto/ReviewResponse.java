package org.services.jobservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private String description;
    private String title;
    private double rating;
    private Long companyId;

}
