package com.duda.dudatask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Grade implements Serializable {

    private String name;
    private Integer grade;
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
}
