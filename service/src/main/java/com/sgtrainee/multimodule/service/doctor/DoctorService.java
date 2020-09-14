package com.sgtrainee.multimodule.service.doctor;

import com.sgtrainee.multimodule.dao.doctor.DoctorRepository;
import com.sgtrainee.multimodule.model.doctor.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DoctorService {

    private DoctorRepository repository;

    @Autowired
    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Doctor> listAll() {
        return this.repository.findAll();
    }

    @Transactional
    public Doctor saveOne(Doctor doctor) {
        return this.repository.save(doctor);
    }

    @Transactional
    public Doctor findOne(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

}
