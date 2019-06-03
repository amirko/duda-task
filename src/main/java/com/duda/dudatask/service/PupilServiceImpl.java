package com.duda.dudatask.service;

import com.duda.dudatask.dao.PupilDao;
import com.duda.dudatask.dao.SchoolDao;
import com.duda.dudatask.model.Grade;
import com.duda.dudatask.model.Pupil;
import com.duda.dudatask.model.School;
import com.duda.dudatask.utils.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PupilServiceImpl implements PupilService {

    private PupilDao pupilDao;
    private SchoolDao schoolDao;
    private DistanceCalculator distanceCalculator;

    @Autowired
    public PupilServiceImpl(PupilDao pupilDao, SchoolDao schoolDao, DistanceCalculator distanceCalculator) {
        this.pupilDao = pupilDao;
        this.schoolDao = schoolDao;
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public Long createPupil(Pupil pupil) {
        pupil = pupilDao.save(pupil);
        return pupil.getId();
    }

    @Override
    public void setFriends(Long pupilId1, Long pupilId2) {
        Pupil pupil1 = pupilDao.getOne(pupilId1);
        Pupil pupil2 = pupilDao.getOne(pupilId2);
        pupil1.getFriends().add(pupil2);
        pupil2.getFriends().add(pupil1);
        pupilDao.saveAll(Arrays.asList(pupil1, pupil2));
    }

    @Override
    public void enrollToSchool(Long pupilId) {
        Pupil pupil = pupilDao.getOne(pupilId);
        double gpa = calcGPA(pupil);
        List<School> schools = schoolDao.findAll();
        School school = schools.stream().filter(sc -> sc.getMinimumGpa() <= gpa).collect(Collectors.toMap((s-> calcFormula(pupil, s)), s -> s))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getKey)).get().getValue();
        school.getPupils().add(pupil);
        schoolDao.save(school);
    }

    private double calcGPA(Pupil pupil) {
        return pupil.getGrades().stream().mapToInt(Grade::getGrade).average().getAsDouble();
    }

    @Override
    public List<Pupil> getAllPupils() {
        return pupilDao.findAll();
    }

    private double calcFormula(Pupil pupil, School school) {
        return calcNumOfFriends(pupil, school) / calcDistance(pupil, school);
    }

    private int calcNumOfFriends(Pupil pupil, School school) {
        Set<Pupil> modifiableSet = new HashSet<>(school.getPupils());
        modifiableSet.retainAll(pupil.getFriends());
        return modifiableSet.size();
    }

    private double calcDistance(Pupil pupil, School school) {
        return distanceCalculator.calcDistance(pupil.getLon(), pupil.getLat(), school.getLon(), school.getLat());
    }


}
