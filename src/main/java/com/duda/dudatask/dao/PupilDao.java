package com.duda.dudatask.dao;

import com.duda.dudatask.model.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilDao extends JpaRepository<Pupil, Long> {
}
