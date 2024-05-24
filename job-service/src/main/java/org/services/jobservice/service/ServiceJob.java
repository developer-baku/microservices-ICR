package org.services.jobservice.service;

import org.services.jobservice.JobServiceApplication;
import org.services.jobservice.dto.*;
import org.services.jobservice.model.Job;
import org.services.jobservice.repository.RepositoryQ;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;


@RestController
@AutoConfiguration
@Service
public class ServiceJob {
    int attempt= 0;
    @Autowired
    private RepositoryQ repository;
    @Autowired
    private  RestClient.Builder restClientBuilder ;
   @Autowired
   private JobServiceApplication.CompanyI companyI;
    @Autowired
      private JobServiceApplication.ReviewI reviewI;

    public JobResponse findById(Long id){
        Job job = repository.findById(id).get();
       return   JobResponse.builder()
               .id(job.getId())
               .companyId(job.getCompanyId())
               .title(job.getTitle())
               .description(job.getDescription())
               .maxSalary(job.getMaxSalary())
               .minSalary(job.getMinSalary())
               .location(job.getLocation())
               .build();

    }

    public List<JobResponse> findAll(){
        return repository.findAll().stream().map(f->JobResponse.builder()
                .description(f.getDescription())
                .id(f.getId())
                            .companyId(f.getCompanyId())

                .title(f.getTitle())
                .maxSalary(f.getMaxSalary())
                .minSalary(f.getMinSalary())
                .location(f.getLocation())
                .build()).toList();
    }
    public List<String> fallback(Exception e){
        var v =new ArrayList<String>();
        v.add("error");
        return v;
    }
    @CircuitBreaker(name = "companyBreaker",fallbackMethod = "fallback")
    public List<JobWithCompanyResponse> findAllW(){
        System.out.println("circuit"+ ++attempt);

                List<JobResponse> jobResponses = repository.findAll().stream()
                .map(f->JobResponse.builder()
                .description(f.getDescription())
                .id(f.getId())
                .companyId(f.getCompanyId())
                .title(f.getTitle())
                .maxSalary(f.getMaxSalary())
                .minSalary(f.getMinSalary())
                .location(f.getLocation())
                .build()).toList();

        return    jobResponses.stream().map(f->JobWithCompanyResponse.builder()
                .description(f.getDescription())
                .id(f.getId())
                //restClient.get(). ..............
                .responseCompany(companyI.getName(f.getCompanyId()))
                        //restClientBuilder.build().get().uri("http://COMPANY:8083/company/{f.getCompanyId()}",f.getCompanyId()).retrieve().body(ResponseCompany.class))
                .location(f.getLocation())
                        .maxSalary(f.getMaxSalary())
                        .minSalary(f.getMinSalary())
                        .title(f.getTitle())
                        .build()).toList();

    }
    @RateLimiter(name = "companyBreaker",fallbackMethod = "fallback")
    public JobWithCompanyResponse findJobWithCompany(Long jobId){
     Job job = repository.findById(jobId).get();
        //RestClient restClient = RestClient.create();
   //    ResponseCompany responseEntity= restClientBuilder.build().get().uri("http://COMPANY:8083/company/{job.getCompanyId()}",job.getCompanyId()).retrieve().body(ResponseCompany.class);
       ResponseCompany responseCompany = companyI.getName(job.getCompanyId());
      //  System.out.println(responseCompany.getClass().getName() + "name of calss");
       return JobWithCompanyResponse.builder()
               .description(job.getDescription())
               .id(jobId)
               .responseCompany(responseCompany)
               .location(job.getLocation())
               .maxSalary(job.getMaxSalary())
               .minSalary(job.getMinSalary())
               .title(job.getTitle())
               .build();
    }
    public void save(JobRequest jobRequest){
        System.out.println(jobRequest.getTitle()+"save");
        repository.save(Job.builder()
                        .companyId(jobRequest.getCompanyId())
                        .description(jobRequest.getDescription())
                        .id(jobRequest.getId())
                        .title(jobRequest.getTitle())
                        .maxSalary(jobRequest.getMaxSalary())
                        .minSalary(jobRequest.getMinSalary())
                        .location(jobRequest.getLocation())
                .build());
    }


    public JobCompanyReviews findJobWithCompanyAndReviews(Long jobId){
        Job job = repository.findById(jobId).get();
        List<ReviewResponse> reviewResponses = reviewI.getR(job.getCompanyId());
       List<ResponseReview> reviews = reviewResponses.stream().map(f -> ResponseReview.builder()
               .description(f.getDescription())
               .id(f.getId())
               .rating(f.getRating())
               .title(f.getTitle())
               .build()).toList();
        return JobCompanyReviews.builder()
                .description(job.getDescription())
                .id(jobId)
                .reviews(reviews)
                .Company(companyI.getName(job.getCompanyId()))
                .location(job.getLocation())
                .maxSalary(job.getMaxSalary())
                .minSalary(job.getMinSalary())
                .title(job.getTitle())
                .build();
    }
    @Retry(name = "companyBreaker",fallbackMethod = "fallback")
    public List<JobCompanyReviews> findAllCR(){
        System.out.println("retry"+ ++attempt);
        List<JobResponse> jobResponses = repository.findAll().stream()
                .map(f->JobResponse.builder()
                        .description(f.getDescription())
                        .id(f.getId())
                        .companyId(f.getCompanyId())
                        .title(f.getTitle())
                        .maxSalary(f.getMaxSalary())
                        .minSalary(f.getMinSalary())
                        .location(f.getLocation())
                        .build()).toList();

        return    jobResponses.stream().map(f->JobCompanyReviews.builder()
                .description(f.getDescription())
                .id(f.getId())
                .Company(companyI.getName(f.getCompanyId()))
                .reviews(reviewI.getR(f.getCompanyId()).stream().map(ff->ResponseReview.builder()
                                .description(ff.getDescription())
                                .id(ff.getId())
                                .rating(ff.getRating())
                                .title(ff.getTitle())
                                .build()).toList()
                )
                .location(f.getLocation())
                .maxSalary(f.getMaxSalary())
                .minSalary(f.getMinSalary())
                .title(f.getTitle())
                .build()).toList();
    }










   public void updateI(JobRequest jobRequest){

       repository.save(Job.builder()
                       .companyId(jobRequest.getCompanyId())
               .description(jobRequest.getDescription())
               .id(jobRequest.getId())
               .title(jobRequest.getTitle())
               .maxSalary(jobRequest.getMaxSalary())
               .minSalary(jobRequest.getMinSalary())
               .location(jobRequest.getLocation())
               .build());
    }
    public void delete(JobRequest jobRequest){
       repository.delete(
               Job.builder()
                       .companyId(jobRequest.getCompanyId())
                       .description(jobRequest.getDescription())
                       .id(jobRequest.getId())
                       .title(jobRequest.getTitle())
                       .maxSalary(jobRequest.getMaxSalary())
                       .minSalary(jobRequest.getMinSalary())
                       .location(jobRequest.getLocation())
                       .build());
    }
    public void deleteById(Long id){
       repository.deleteById(id);

    }


}
