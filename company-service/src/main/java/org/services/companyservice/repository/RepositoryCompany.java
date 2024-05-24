package org.services.companyservice.repository;

import org.services.companyservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCompany extends JpaRepository<Company,Long> {
}
