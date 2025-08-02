package com.atharv.patientservice.mapper;

import com.atharv.patientservice.dto.PatientRequestDTO;
import com.atharv.patientservice.model.Patient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface PatientPatchMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void updatePatientFromDTO(PatientRequestDTO patientrequestDTO, @MappingTarget Patient patient);

    default LocalDate mapStringToLocalDate(String date) {
        return (date == null || date.isBlank()) ? null : LocalDate.parse(date);
    }

    default String mapLocalDateToString(LocalDate date) {
        return date == null ? null : date.toString();
    }

}
