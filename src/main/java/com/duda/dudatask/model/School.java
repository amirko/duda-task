package com.duda.dudatask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class School implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("Lon")
    private Double lon;
    @JsonProperty("Lat")
    private Double lat;
    private Integer minimumGpa;
    private int maxNumberOfPupils;
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = Pupil.class)
    private Set<Pupil> pupils;
    private int numOfPupils;
}
