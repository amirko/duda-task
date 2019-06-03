package com.duda.dudatask.service;

import com.duda.dudatask.dao.SchoolDao;
import com.duda.dudatask.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolDao schoolDao;

    @Autowired
    public SchoolServiceImpl(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public Long createSchool(School school) {
        school = schoolDao.save(school);
        return school.getId();
    }

    @Override
    public List<School> getSchools() {
        return schoolDao.findAll();
    }
}
