package br.com.anapaulasantossf.f1_team_manager_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "funcionario")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "nomeFuncionario")
    private String name;

    @Column(name = "dataNascimento")
    private Date birthDate;
}
