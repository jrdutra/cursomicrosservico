package com.jrdutra.agenda.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrdutra.agenda.dto.ContatoDto;
import com.jrdutra.agenda.dto.ExceptionDto;
import com.jrdutra.agenda.entity.ContatoEntity;
import com.jrdutra.agenda.exception.ContatoNaoEncontradoException;
import com.jrdutra.agenda.mapper.ContatoMapper;
import com.jrdutra.agenda.service.CalculaIdadeService;
import com.jrdutra.agenda.service.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatosController {
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private CalculaIdadeService calculaIdadeService;
	
	private static final Logger LOGGER = LogManager.getLogger(ContatosController.class);
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> buscaContatoPorId(@PathVariable("id") Long id) {		
		LOGGER.info("Buscando contato pelo id {}", id);
		
		ContatoEntity contatoEntityBuscado;
		try {
			contatoEntityBuscado = contatoService.buscaCotatoPorId(id);
			Date dataNascimento = contatoEntityBuscado.getDataNascimento();
			Integer idade = calculaIdadeService.calculaIdade(dataNascimento);
			
			ContatoDto contatoDto = ContatoMapper.toContatoDto(contatoEntityBuscado, idade);
			
			LOGGER.info("Retornando contato {}", contatoDto);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(contatoDto);
		} catch (ContatoNaoEncontradoException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> salvarContato(@RequestBody ContatoEntity contatoEntity) {
		LOGGER.info("Incluindo contato {}", contatoEntity);
		try {
			ContatoEntity contatoEntitySalvo = contatoService.salvarContato(contatoEntity);
			
			LOGGER.info("Contato incluso {}", contatoEntitySalvo);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(contatoEntitySalvo);
		}catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> alterarContato(@PathVariable Long id, @RequestBody ContatoEntity contatoEntity) {
		LOGGER.info("Alterando contato: {}", contatoEntity);
		ContatoEntity contatoEntityAlterado;
		try {
			contatoEntityAlterado = contatoService.alterarContato(contatoEntity, id);
			
			LOGGER.info("Contato alterado: {}", contatoEntityAlterado);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(contatoEntityAlterado);
		} catch (ContatoNaoEncontradoException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
		}catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Object> deletarContato(@PathVariable Long id) {
		try {
			contatoService.deletarContato(id);
			return ResponseEntity
					.status(HttpStatus.OK)
					.build();
		}catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
	}
}
