package com.uahb.m1info.hopitale.repository;

import com.uahb.m1info.hopitale.model.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
}
