package br.com.anapaulasantossf.f1_team_manager_api.controller;

import br.com.anapaulasantossf.f1_team_manager_api.dtos.EmployeeRequestDTO;
import br.com.anapaulasantossf.f1_team_manager_api.dtos.EmployeeResponseDTO;
import br.com.anapaulasantossf.f1_team_manager_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
        List<EmployeeResponseDTO> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable UUID id) {
        Optional<EmployeeResponseDTO> employee = employeeService.findById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> post(@RequestBody EmployeeRequestDTO employeeRequestDTO){
        EmployeeResponseDTO employeeResponseDTO = employeeService.create(employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable UUID id, @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO updatedEmployee = employeeService.update(id, employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean deleted = employeeService.deleteById(id);
        if (deleted) {// Retorna 204 No Content para sucesso sem corpo
            return ResponseEntity.noContent().build();
        } else {// Retorna 404 Not Found se o recurso não existia
            return ResponseEntity.notFound().build();
        }
    }
}
