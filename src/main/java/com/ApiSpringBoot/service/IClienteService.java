package com.ApiSpringBoot.service;

import com.ApiSpringBoot.model.dto.ClienteDto;
import com.ApiSpringBoot.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    Cliente save(ClienteDto clienteDto);

    List<ClienteDto> findAll();

    ClienteDto finById(Integer id);

    void delete(ClienteDto clienteDto);

    boolean existsById(Integer id);

}
