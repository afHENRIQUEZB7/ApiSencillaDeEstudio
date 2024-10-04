package com.ApiSpringBoot.model.dto;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

// Utillizacion de la dependencia de lombok
@Data// crea los setter y getter
@ToString // Me genera el metodo toString
@Builder


public class ClienteDto implements Serializable {


    private int idCliente;


    private String nombre;


    private String apellido;


    private String correo;


    private Date fechaRegistro;

}
