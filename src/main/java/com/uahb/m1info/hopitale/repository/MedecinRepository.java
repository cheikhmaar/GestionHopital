package com.uahb.m1info.hopitale.repository;

import com.uahb.m1info.hopitale.model.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //ne pas oublier
public interface MedecinRepository extends JpaRepository<Medecin, Long>  {
}
