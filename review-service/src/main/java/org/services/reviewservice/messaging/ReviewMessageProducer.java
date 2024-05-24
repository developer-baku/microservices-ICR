package org.services.reviewservice.messaging;

import org.services.reviewservice.dto.ReviewMessage;
import org.services.reviewservice.dto.ReviewRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class ReviewMessageProducer {
   private final RabbitTemplate rabbitTemplate;
   public void sendMessage(ReviewRequest reviewRequest){
       ReviewMessage message = ReviewMessage.builder()
               .id(reviewRequest.getId())
               .title(reviewRequest.getTitle())
               .description(reviewRequest.getDescription())
               .companyId(reviewRequest.getCompanyId())
               .rating(reviewRequest.getRating())
               .build();
        rabbitTemplate.convertAndSend("companyRatingQueue",message);
   }
   public void getAverageRating(Double averageRating){
      rabbitTemplate.convertAndSend("companyRatingQueue",averageRating);
   }
}
