package com.uca.capas.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Importancia;


public interface ImportanciaDAO {
	
	public List<Importancia> findAll() throws DataAccessException;
	
	public Importancia findOne(Integer codigo) throws DataAccessException;
	
	public void insert(Importancia importancia) throws DataAccessException;
	
	public void delete(Importancia importancia) throws DataAccessException;

}
