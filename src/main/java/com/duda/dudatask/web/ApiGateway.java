package com.duda.dudatask.web;

import com.duda.dudatask.model.Pupil;
import com.duda.dudatask.model.School;
import com.duda.dudatask.service.PupilService;
import com.duda.dudatask.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiGateway {

    private PupilService pupilService;
    private SchoolService schoolService;

    @Autowired
    public ApiGateway(PupilService pupilService, SchoolService schoolService) {
        this.pupilService = pupilService;
        this.schoolService = schoolService;
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello!";
    }

    @PostMapping("pupil")
    public Long createPupil(@RequestBody Pupil pupil) {
        return pupilService.createPupil(pupil);
    }

    @PostMapping("setFriendShip/{firstPupilId}/{secondPupilId}")
    public void setFriendship(@PathVariable("firstPupilId") Long pupilId1, @PathVariable("secondPupilId") Long pupilId2) {
        pupilService.setFriends(pupilId1, pupilId2);
    }

    @RequestMapping("pupils")
    public List<Pupil> getPupils() {
        List<Pupil> pupils = pupilService.getAllPupils();
        return pupils;
    }

    @PostMapping("enroll/{pupilId}")
    public void enroll(@PathVariable Long pupilId) {
        pupilService.enrollToSchool(pupilId);
    }


    @PostMapping("school")
    public Long createSchool(@RequestBody School school) {
        return schoolService.createSchool(school);
    }

    @GetMapping("schools")
    public List<School> getSchools() {
        return schoolService.getSchools();
    }

}
