package com.fatihmayuk.kartaca.backend.repository;

import com.fatihmayuk.kartaca.backend.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Long> {
}
