package com.ApiSpringBoot.controller;

import com.ApiSpringBoot.model.dto.ClienteDto;
import com.ApiSpringBoot.model.entity.Cliente;
import com.ApiSpringBoot.model.payload.MensajeResponse;
import com.ApiSpringBoot.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private IClienteService clienteServicio;

    @PostMapping("/cliente")
    // Se le coloca @RequestBody para que resiva el JSON y lo convierta a tipo de objeto cliente
    public ResponseEntity<?> save(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try{
            clienteSave = clienteServicio.save(clienteDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Se creo con exito el nuevo cliente")
                    .object(ClienteDto.builder()
                            .idCliente(clienteSave.getIdCliente())
                            .nombre(clienteSave.getNombre())
                            .apellido(clienteSave.getApellido())
                            .fechaRegistro(clienteSave.getFechaRegistro())
                            .correo(clienteSave.getCorreo())
                            .build())
                    .build(), HttpStatus.CREATED);
        }catch (DataAccessException ex){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(ex.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @PutMapping("/cliente/{id}")
    // Se le coloca @RequestBody para que resiva el JSON y lo convierta a tipo de objeto cliente
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
        Cliente clienteUpdate = null;
        try{
            if (clienteServicio.existsById(id)) {
                clienteDto.setIdCliente(id);
                clienteUpdate = clienteServicio.save(clienteDto);
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Se actualizo el con exito el cliente")
                        .object(ClienteDto.builder()
                                .idCliente(clienteUpdate.getIdCliente())
                                .nombre(clienteUpdate.getNombre())
                                .apellido(clienteUpdate.getApellido())
                                .fechaRegistro(clienteUpdate.getFechaRegistro())
                                .correo(clienteUpdate.getCorreo())
                                .build())
                        .build(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentr registrado")
                        .object(null)
                        .build(), HttpStatus.BAD_REQUEST);
            }
        }catch (DataAccessException ex){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(ex.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/cliente/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT) // codigoo 204
    // Se le coloca PathVariable para saber que el trae un id por medio de la url
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            ClienteDto clienteDelete = clienteServicio.finById(id);
            clienteServicio.delete(ClienteDto.builder()
                    .idCliente(clienteDelete.getIdCliente())
                    .nombre(clienteDelete.getNombre())
                    .apellido(clienteDelete.getApellido())
                    .fechaRegistro(clienteDelete.getFechaRegistro())
                    .correo(clienteDelete.getCorreo())
                    .build());
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("mensaje,"+ "El id ingresado no se encuentra en la base de datos " + "(" + e.getMessage() + ")")
                            .object("cliente, "+  null)
                            .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @GetMapping("/cliente/{id}")
    // Se le coloca PathVariable para saber que el trae un id por medio de la url
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        ClienteDto cliente = clienteServicio.finById(id);

        if (cliente ==  null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El resgistro que intenta buscar no existe")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("El Usuario es:")
                .object(ClienteDto.builder()
                        .idCliente(cliente.getIdCliente())
                        .nombre(cliente.getNombre())
                        .apellido(cliente.getApellido())
                        .fechaRegistro(cliente.getFechaRegistro())
                        .correo(cliente.getCorreo())
                        .build())
                .build(),HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<?> findAll() {
        List<ClienteDto> clientes = clienteServicio.findAll();
        if (clientes == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("NO hay registros en la base de datos")
                    .object(null)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Los registros que existen en la base de datos son: ")
                .object(clientes)
                .build(), HttpStatus.OK);
    }


}
