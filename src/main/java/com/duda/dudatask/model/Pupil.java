package com.duda.dudatask.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Pupil implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("Lon")
    private Double lon;
    @JsonProperty("Lat")
    private Double lat;
    @OneToMany(targetEntity = Grade.class, cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonProperty("Grades")
    private List<Grade> grades;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Pupil> friends;

}
