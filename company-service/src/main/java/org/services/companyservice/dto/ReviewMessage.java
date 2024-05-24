package org.services.companyservice.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewMessage {
    private Long id;
    private String description;
    private String title;
    private double rating;
    private Long companyId;

}
