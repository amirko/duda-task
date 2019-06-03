package com.duda.dudatask.service;

import com.duda.dudatask.model.Pupil;

import java.util.List;

public interface PupilService {

    Long createPupil(Pupil pupil);
    void setFriends(Long pupilId1, Long pupilId2);
    void enrollToSchool(Long pupilId);
    List<Pupil> getAllPupils();
}
