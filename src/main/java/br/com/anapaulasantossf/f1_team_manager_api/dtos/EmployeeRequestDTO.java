package br.com.anapaulasantossf.f1_team_manager_api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeRequestDTO {
    private String name;
    private Date birthDate;
}
