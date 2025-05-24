package br.com.anapaulasantossf.f1_team_manager_api.repository;

import br.com.anapaulasantossf.f1_team_manager_api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    //
}
