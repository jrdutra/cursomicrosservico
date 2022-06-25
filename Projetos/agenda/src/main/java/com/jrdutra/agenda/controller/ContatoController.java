package com.jrdutra.agenda.controller;

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
import com.jrdutra.agenda.dto.IdadeDto;
import com.jrdutra.agenda.entity.ContatoEntity;
import com.jrdutra.agenda.exception.ConexaoCalculadoraException;
import com.jrdutra.agenda.exception.ContatoNaoEncontradoException;
import com.jrdutra.agenda.mapper.ContatoMapper;
import com.jrdutra.agenda.service.CalculadoraService;
import com.jrdutra.agenda.service.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	private ContatoService contatoService;
	
	@Autowired 
	private CalculadoraService calculadoraService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> buscaContatoPorId(@PathVariable Long id) {
		ContatoEntity contatoEntity;
		try {
			contatoEntity = contatoService.buscaContatoPorId(id);
			
			IdadeDto idadeDto = calculadoraService.calculaIdade(contatoEntity.getDataNascimento());
			
			ContatoDto contatoDto = ContatoMapper.contatoEntityToContatoDto(contatoEntity, idadeDto);
			
			ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).body(contatoDto);
			
			return response;
		} catch (ContatoNaoEncontradoException e) {
			ResponseEntity<Object> response = ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
			return response;
		} catch (ConexaoCalculadoraException e) {
			ResponseEntity<Object> response = ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ExceptionDto(HttpStatus.EXPECTATION_FAILED, e.getMessage()));
			return response;
		} catch (Exception e) {
			ResponseEntity<Object> response = ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
			return response;
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> salvarContato(@RequestBody ContatoEntity contatoEntity){
		try {
			ContatoEntity contatoEntitySalvo = contatoService.salvarContato(contatoEntity);
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
	public ResponseEntity<Object> alterarContato(@PathVariable Long id, @RequestBody ContatoEntity contatoEntity){
		try {
			ContatoEntity contatoEntityAlterado = contatoService.alterarContato(contatoEntity, id);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(contatoEntityAlterado);
		} catch (ContatoNaoEncontradoException e) {
			ResponseEntity<Object> response = ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
			return response;
		} catch (Exception e) {
			ResponseEntity<Object> response = ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
			return response;
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletarContato(@PathVariable Long id){
		try {
			contatoService.deletarContato(id);
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.build();
		}catch(Exception e) {
			return  ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
	}
}