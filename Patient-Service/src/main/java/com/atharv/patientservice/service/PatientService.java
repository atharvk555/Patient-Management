package com.atharv.patientservice.service;


import com.atharv.patientservice.dto.PatientRequestDTO;
import com.atharv.patientservice.dto.PatientResponseDTO;
import com.atharv.patientservice.exception.EmailAlreadyExistsException;
import com.atharv.patientservice.exception.PatientNotFoundException;
import com.atharv.patientservice.mapper.PatientMapper;
import com.atharv.patientservice.mapper.PatientPatchMapper;
import com.atharv.patientservice.model.Patient;
import com.atharv.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientPatchMapper patientPatchMapper;

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A Patient with this Email already exists" + patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExistsException("A Patient with this Email already exists" + patientRequestDTO.getEmail());
        }
        patientPatchMapper.updatePatientFromDTO(patientRequestDTO, patient);
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }


}
