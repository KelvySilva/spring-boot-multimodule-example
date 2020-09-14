package com.sgtrainee.multimodule.model.doctor;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Doctor {

    @Id
    @GeneratedValue
    private Long id;
    private  String name;
    private String specialist;
}
