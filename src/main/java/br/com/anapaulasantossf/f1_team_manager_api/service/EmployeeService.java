package br.com.anapaulasantossf.f1_team_manager_api.service;

import br.com.anapaulasantossf.f1_team_manager_api.dtos.EmployeeRequestDTO;
import br.com.anapaulasantossf.f1_team_manager_api.dtos.EmployeeResponseDTO;
import br.com.anapaulasantossf.f1_team_manager_api.model.Employee;
import br.com.anapaulasantossf.f1_team_manager_api.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true) // Boa prática para operações de leitura
    public List<EmployeeResponseDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeResponseDTO> findById(UUID id) {
        return Optional.ofNullable(employeeRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado " + id)));
    }

    @Transactional
    public EmployeeResponseDTO update(UUID id, EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee existingEmployee = employeeOptional.get();
            modelMapper.map(employeeRequestDTO, existingEmployee);
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return toDTO(updatedEmployee);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deleteById(UUID id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Employee toEntity(EmployeeRequestDTO employeeRequestDTO) {
        return modelMapper.map(employeeRequestDTO, Employee.class);
    }

    private EmployeeResponseDTO toDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }

}
