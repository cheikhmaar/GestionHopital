package com.uahb.m1info.hopitale.repository;

import com.uahb.m1info.hopitale.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
