package com.duda.dudatask.dao;

import com.duda.dudatask.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDao extends JpaRepository<School, Long> {
}
