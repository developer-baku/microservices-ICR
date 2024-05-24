package org.services.reviewservice.controller;

import org.services.reviewservice.dto.ReviewRequest;
import org.services.reviewservice.dto.ReviewResponse;
import org.services.reviewservice.messaging.ReviewMessageProducer;
import org.services.reviewservice.service.ServiceReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ControllerReview {
    @Autowired
    private ServiceReview serviceReview;
    @Autowired
    private ReviewMessageProducer producer;
    @PostMapping()
    public ResponseEntity<String> add(@RequestBody ReviewRequest reviewRequest, @RequestParam Long companyId
    ){
        System.out.println(reviewRequest);
        serviceReview.addReview(reviewRequest,companyId);
        reviewRequest.setCompanyId(companyId);
        producer.sendMessage(reviewRequest);
//        Double average= serviceReview.getAverageRating(companyId);
//        producer.getAverageRating(average);
        return new ResponseEntity<>("added",HttpStatus.OK);
    }
    @PutMapping("/upgrade")
    public ResponseEntity<String> update(@RequestBody ReviewRequest reviewRequest
//            ,@RequestParam Long companyId
    ){
        serviceReview.updateReview(reviewRequest);
        return new ResponseEntity<>("updated",HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody ReviewRequest reviewRequest
//            ,@RequestParam Long companyId
    ){
        serviceReview.deleteReview(reviewRequest);
        return new ResponseEntity<>("deleted",HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public  ResponseEntity<ReviewResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(serviceReview.getReview(id),HttpStatus.OK);
    }
    @GetMapping ()
    public ResponseEntity<List<ReviewResponse>> getAll(@RequestParam Long companyId ){
       return new ResponseEntity<>(serviceReview.getAllReviewByCompanyId(companyId), HttpStatus.OK);
    }
    @GetMapping ("/averageRating")
    public ResponseEntity<Double> getAverageRating(@RequestParam Long companyId ){
        return new ResponseEntity<>(serviceReview.getAverageRating(companyId), HttpStatus.OK);
    }

}
