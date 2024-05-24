package org.services.companyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private Long id;
    private String description;
    private String title;
    private double rating;
    private Long companyId;

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", companyId=" + companyId +
                '}';
    }
}
