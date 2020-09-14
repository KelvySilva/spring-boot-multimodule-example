package com.sgtrainee.multimodule.controllers.doctor;

import com.sgtrainee.multimodule.model.doctor.Doctor;
import com.sgtrainee.multimodule.service.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class DoctorController {

    private DoctorService service;

    @Autowired
    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping(path = "/list")
    public ResponseEntity listAll() {
        return ResponseEntity.ok(this.service.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findOne(id));
    }

    @PostMapping
    public ResponseEntity saveOne(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(this.service.saveOne(doctor));
    }
}
