package org.services.jobservice.controller;

import org.services.jobservice.dto.JobCompanyReviews;
import org.services.jobservice.dto.JobRequest;
import org.services.jobservice.dto.JobResponse;
import org.services.jobservice.dto.JobWithCompanyResponse;
import org.services.jobservice.service.ServiceJob;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class Controller {
    @Autowired
    private ServiceJob service;
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getAllJobs(@PathVariable Long id) {
        //System.out.println(service.findById(id).getDescription()+"    ");
        return new  ResponseEntity<JobResponse>(service.findById(id), HttpStatus.FOUND);
    }
    @GetMapping
    public List<JobResponse> getAllJobs() {
    return service.findAll();

    }@GetMapping("/jobWiths")
    public ResponseEntity<List<JobWithCompanyResponse>> getAllJobsC() {
        return new ResponseEntity<>(service.findAllW(),HttpStatus.OK);

    }
    @GetMapping("/jobCRs")
    public ResponseEntity<List<JobCompanyReviews>> getAllJobsCR() {
        return new ResponseEntity<>(service.findAllCR(),HttpStatus.OK);

    } @GetMapping("/jobCR/{id}")
    public ResponseEntity<JobCompanyReviews> idd(@PathVariable Long id) {
        return new ResponseEntity<>(service.findJobWithCompanyAndReviews(id), HttpStatus.OK);
    }

    @GetMapping("/jobWith/{id}")
    public ResponseEntity<JobWithCompanyResponse> id(@PathVariable Long id) {
        return new ResponseEntity<>(service.findJobWithCompany(id), HttpStatus.OK);
    }
    @PostMapping
    public void put(@RequestBody JobRequest jobRequest){
          service.save(jobRequest);
    }
    @PutMapping("/update")
    public void updateI(@RequestBody JobRequest jobRequest){
       service.updateI(jobRequest);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody JobRequest jobRequest){
        service.delete(jobRequest);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
