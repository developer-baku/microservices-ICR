package org.services.reviewservice.repository;

import org.services.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryReview extends JpaRepository<Review,Long> {

    List<Review> findByCompanyId(Long company_id);
}
