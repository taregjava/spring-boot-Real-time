package com.halfacode.spring_real_time_learning.mapper;

import com.halfacode.spring_real_time_learning.dto.AppointmentDTO;
import com.halfacode.spring_real_time_learning.dto.PatientDTO;
import com.halfacode.spring_real_time_learning.entity.Appointment;
import com.halfacode.spring_real_time_learning.entity.Doctor;
import com.halfacode.spring_real_time_learning.entity.Patient;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "diagnosis", source = "medicalRecord.diagnosis") // Flatten
    @Mapping(target = "upcomingAppointments", source = "appointments") // Custom filter
    @Mapping(target = "doctorNames", source = "doctors") // Flatten Many-to-Many
    PatientDTO toDto(Patient patient, @Context LocalDate filterDate);

    @InheritInverseConfiguration
    @Mapping(target = "medicalRecord", ignore = true) // Ignore in reverse
    @Mapping(target = "appointments", ignore = true) // Avoid recursion
    @Mapping(target = "doctors", ignore = true) // Avoid recursion
    Patient toEntity(PatientDTO dto);

    // Helper mappings

    default List<AppointmentDTO> mapAppointments(List<Appointment> appointments, @Context LocalDate filterDate) {
        if (appointments == null) return null;
        return appointments.stream()
                .filter(a -> a.getDate().isAfter(filterDate)) // Conditional mapping
                .map(a -> {
                    AppointmentDTO dto = new AppointmentDTO();
                    dto.setId(a.getId());
                    dto.setDate(a.getDate());
                    dto.setDoctorName(a.getDoctor() != null ? a.getDoctor().getName() : null);
                    return dto;
                })
                .toList();
    }

    default Set<String> mapDoctors(Set<Doctor> doctors) {
        if (doctors == null) return null;
        return doctors.stream()
                .map(Doctor::getName)
                .collect(Collectors.toSet());
    }
}
