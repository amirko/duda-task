package com.duda.dudatask.service;

import com.duda.dudatask.model.School;

import java.util.List;

public interface SchoolService {

    Long createSchool(School school);
    List<School> getSchools();
}
