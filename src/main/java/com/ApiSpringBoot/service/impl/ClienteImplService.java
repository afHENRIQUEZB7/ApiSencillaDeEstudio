package com.ApiSpringBoot.service.impl;

import com.ApiSpringBoot.model.dao.ClienteDao;
import com.ApiSpringBoot.model.dto.ClienteDto;
import com.ApiSpringBoot.model.entity.Cliente;
import com.ApiSpringBoot.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteImplService implements IClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .correo(clienteDto.getCorreo())
                .build();
        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteDto> findAll() {
        List<Cliente> clientes = (List<Cliente>) clienteDao.findAll();
        List<ClienteDto> clientesDtos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            ClienteDto clienteDto = ClienteDto.builder()
                    .idCliente(cliente.getIdCliente())
                    .nombre(cliente.getNombre())
                    .apellido(cliente.getApellido())
                    .fechaRegistro(cliente.getFechaRegistro())
                    .correo(cliente.getCorreo())
                    .build();

            clientesDtos.add(clienteDto);
        }
        return clientesDtos;
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteDto finById(Integer id) {
        Cliente cliente = clienteDao.findById(id).orElse(null);
        return ClienteDto.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();
    }

    //@Transactional
    @Override
    public void delete(ClienteDto clienteDto) {

        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .build();

        clienteDao.delete(cliente);
    }

    @Override
    public boolean existsById(Integer id) {
        return clienteDao.existsById(id);
    }
}
