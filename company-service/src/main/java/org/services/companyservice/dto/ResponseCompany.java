package org.services.companyservice.dto;


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
    private Double averageRating;

}
