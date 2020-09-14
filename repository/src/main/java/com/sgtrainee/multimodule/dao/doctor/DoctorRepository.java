package com.sgtrainee.multimodule.dao.doctor;

import com.sgtrainee.multimodule.model.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
