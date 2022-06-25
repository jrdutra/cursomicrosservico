package com.jrdutra.agenda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jrdutra.agenda.entity.ContatoEntity;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long>{
	
	@Query("SELECT c FROM ContatoEntity c WHERE c.id = :pId")
	public Optional<ContatoEntity> buscarPorId(@Param("pId") Long id);
	
}
