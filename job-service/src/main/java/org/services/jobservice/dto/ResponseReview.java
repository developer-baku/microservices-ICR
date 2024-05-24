package org.services.jobservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReview {
    private Long id;
    private String description;
    private String title;
    private double rating;
}
