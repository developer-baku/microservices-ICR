package org.services.reviewservice.service;

import org.services.reviewservice.dto.ReviewRequest;
import org.services.reviewservice.dto.ReviewResponse;
import org.services.reviewservice.model.Review;
import org.services.reviewservice.repository.RepositoryReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceReview {
    @Autowired
    private RepositoryReview repositoryReview;
//    @Autowired
//    private ServiceCompany serviceCompany;
    public List<ReviewResponse> getAllReview(Long companyId){

        return repositoryReview.findAll().stream()
                .filter(p->p.getCompanyId().equals(companyId))
                .map(f->
                        ReviewResponse.builder()
                                .id(f.getId())
                                .description(f.getDescription())
                                .title(f.getTitle())
                                .rating(f.getRating())
                                .companyId(f.getCompanyId())
                                .build()
                ).toList();
    }
    public List<ReviewResponse> getAllReviewByCompanyId(Long companyId){
        return repositoryReview.findByCompanyId(companyId).stream()
                .map(f->
                        ReviewResponse.builder()
                                .id(f.getId())
                                .description(f.getDescription())
                                .title(f.getTitle())
                                .rating(f.getRating())
                                .companyId(f.getCompanyId())
                                .build()
                ).toList();

    }
    public void addReview(ReviewRequest reviewRequest, Long companyId){


         repositoryReview.save(Review.builder()
                .description(reviewRequest.getDescription())
                .title(reviewRequest.getTitle())
                .rating(reviewRequest.getRating())
                .companyId(companyId)
                .build());
    }
    public ReviewResponse getReview(Long id) {
        var company = repositoryReview.getReferenceById(id);
        return ReviewResponse.builder()
                .id(company.getId())
                .description(company.getDescription())
                .rating(company.getRating())
                .title(company.getTitle())
                .companyId(company.getCompanyId())

                .build();
    }

    public void deleteReview(ReviewRequest reviewRequest){



      repositoryReview.delete(Review.builder()
                        .id(reviewRequest.getId())
                        .description(reviewRequest.getDescription())
                        .rating(reviewRequest.getRating())
                        .title(reviewRequest.getTitle())
                        .companyId(reviewRequest.getCompanyId())

                .build());
    }
    public void updateReview(ReviewRequest reviewRequest){

        repositoryReview.save(Review.builder()
                .description(reviewRequest.getDescription())
                .title(reviewRequest.getTitle())
                .rating(reviewRequest.getRating())
                .id(reviewRequest.getId())
                .companyId(reviewRequest.getCompanyId())
                .build());

    }


    public Double getAverageRating(Long companyId) {

     List<Double> reviewResponses= repositoryReview.findByCompanyId(companyId).stream()
                .map(Review::getRating
                ).toList();
      Double sum = 0.0;
      int count = 0;
        for(Double review : reviewResponses){ sum +=review; count++; }
        return sum/count;
    }
}
