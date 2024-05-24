package org.services.companyservice.service;

import org.services.companyservice.CompanyServiceApplication;
import org.services.companyservice.dto.RequestCompany;
import org.services.companyservice.dto.ResponseCompany;
import org.services.companyservice.dto.ReviewMessage;
import org.services.companyservice.model.Company;
import org.services.companyservice.repository.RepositoryCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCompany {
    @Autowired
    private RepositoryCompany repositoryCompany;
    @Autowired
    private CompanyServiceApplication.CompanyClient companyClient;
    public List<ResponseCompany> getAllCompany(){

        return repositoryCompany.findAll().stream()
                .map(f->
                    ResponseCompany.builder()
                            .id(f.getId())
//                            .reviews(f.getReviews())
                            .description(f.getDescription())
//                            .jobs(f.getJobs())
                            .averageRating(f.getAverageRating())
                            .name(f.getName())
                            .build()
                ).toList();
    }
    public void addCompany(RequestCompany requestCompany){

        repositoryCompany.save(Company.builder()
                        .description(requestCompany.getDescription())
//                        .jobs(requestCompany.getJobs())
//                .reviews(requestCompany.getReviews())
                        .averageRating(requestCompany.getAverageRating())
                .name(requestCompany.getName())
                .build());
    }
    public ResponseCompany getCompany(Long id) {

        var company = repositoryCompany.getReferenceById(id);
        return ResponseCompany.builder()
                .id(company.getId())
                .description(company.getDescription())
//                .jobs(company.getJobs())
//                .reviews(company.getReviews())
                .averageRating(company.getAverageRating())
                .name(company.getName())
                .build();
    }
    public void deleteCompany(RequestCompany requestCompany){
        repositoryCompany.delete(Company.builder()
                .id(requestCompany.getId())
                .description(requestCompany.getDescription())
//                .jobs(requestCompany.getJobs())
//                .reviews(requestCompany.getReviews())
                .averageRating(requestCompany.getAverageRating())
                .name(requestCompany.getName())
                .build());
    }
    public void updateCompany(RequestCompany requestCompany){
        repositoryCompany.save(Company.builder()
                .id(requestCompany.getId())
                .description(requestCompany.getDescription())
//                .jobs(requestCompany.getJobs())
                .name(requestCompany.getName())
                                .averageRating(requestCompany.getAverageRating())
//                        .reviews(requestCompany.getReviews())
                .build());

    }
    public void updateCompany(ReviewMessage reviewMessage){
        System.out.println(reviewMessage.getDescription());
      Company company = repositoryCompany.findById(reviewMessage.getCompanyId())
                .orElseThrow(()->new IllegalStateException("Could not"));
      Double average = companyClient.getAverageRating(company.getId());
      company.setAverageRating(average);
      repositoryCompany.save(company);

    }


}
