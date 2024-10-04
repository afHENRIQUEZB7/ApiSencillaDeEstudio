package com.ApiSpringBoot.model.dao;

import com.ApiSpringBoot.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente,Integer> {
}
