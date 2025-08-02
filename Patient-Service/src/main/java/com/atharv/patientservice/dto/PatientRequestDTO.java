package com.atharv.patientservice.dto;

import com.atharv.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDTO {

    @NotBlank(message = "Name is required", groups = CreatePatientValidationGroup.class)
    @Size(max = 100, message = "Name cannot exceed 100 characters", groups = CreatePatientValidationGroup.class)
    private String name;

    @NotBlank(message = "Email is required",groups = CreatePatientValidationGroup.class)
    @Email(message = "Enter a valid Email", groups = CreatePatientValidationGroup.class)
    private String email;

    @NotBlank(message = "Address is required",groups = CreatePatientValidationGroup.class)
    private String address;

    @NotBlank(message = "DOB is required",groups = CreatePatientValidationGroup.class)
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class,message = "Registration Date is required")
    private String registeredDate;
}
