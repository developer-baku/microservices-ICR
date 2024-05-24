package org.services.jobservice.repository;

import org.services.jobservice.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryQ extends JpaRepository<Job, Long> {
     //Optional<Job> findById(Long id);
    List<Job> findAll();


    void deleteById(Long id);

}
