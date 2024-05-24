package org.services.companyservice.messaging;

import org.services.companyservice.dto.ReviewMessage;
import org.services.companyservice.service.ServiceCompany;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReviewMessageConsumer {
   private final RabbitTemplate rabbitTemplate;
   private final ServiceCompany serviceCompany;
   @RabbitListener(queues = "companyRatingQueue")
   public void consumeMessage(ReviewMessage reviewMessage){
       serviceCompany.updateCompany(reviewMessage);
   }
//   @RabbitListener(queues = "companyRatingQueue")
//   public void consumeAverageRating(Double averageRating){
//      serviceCompany.updateCompany(averageRating);
//   }

}
