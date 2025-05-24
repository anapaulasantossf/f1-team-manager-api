package br.com.anapaulasantossf.f1_team_manager_api.service;

import br.com.anapaulasantossf.f1_team_manager_api.dtos.EmployeeRequestDTO;
import br.com.anapaulasantossf.f1_team_manager_api.dtos.EmployeeResponseDTO;
import br.com.anapaulasantossf.f1_team_manager_api.model.Employee;
import br.com.anapaulasantossf.f1_team_manager_api.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    public EmployeeResponseDTO create(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.save(toEntity(employeeRequestDTO));
        return toDTO(employee);
    }

    private Employee toEntity(EmployeeRequestDTO employeeRequestDTO) {
        return modelMapper.map(employeeRequestDTO, Employee.class);
    }

    private EmployeeResponseDTO toDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }

}
