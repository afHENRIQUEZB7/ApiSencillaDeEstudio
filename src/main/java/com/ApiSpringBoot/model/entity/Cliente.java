package com.ApiSpringBoot.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

// Utillizacion de la dependencia de lombok
@Data// crea los setter y getter
@AllArgsConstructor // crea un constructor con todos los parametros
@NoArgsConstructor // crea un constructor basio
@ToString // Me genera el metodo toString
@Builder

// Utilizacion de la dependencia de JPA
@Entity //hacer referencia que la clase va la tabla clientes de nuestra base de datos
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

}
