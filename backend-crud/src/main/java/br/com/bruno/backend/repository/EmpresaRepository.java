package br.com.bruno.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bruno.backend.model.Empresa;


@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
