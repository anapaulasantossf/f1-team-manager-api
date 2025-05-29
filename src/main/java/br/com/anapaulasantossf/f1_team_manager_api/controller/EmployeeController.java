package br.com.anapaulasantossf.f1_team_manager_api.controller;

import br.com.anapaulasantossf.f1_team_manager_api.dtos.EmployeeRequestDTO;
import br.com.anapaulasantossf.f1_team_manager_api.dtos.EmployeeResponseDTO;
import br.com.anapaulasantossf.f1_team_manager_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;



    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> post(@RequestBody EmployeeRequestDTO employeeRequestDTO){
        EmployeeResponseDTO employeeResponseDTO = employeeService.create(employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDTO);
    }
}
