package com.jrdutra.calculadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrdutra.calculadora.dto.IdadeDto;
import com.jrdutra.calculadora.service.CalculadoraService;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {
	
	@Autowired
	private CalculadoraService calculadoraService;
	
	@GetMapping(value= "/{dataNascimento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public IdadeDto calcularIdade(@PathVariable("dataNascimento") String strDataNascimento) {
		IdadeDto idadeDto = new IdadeDto();
		
		Integer idade = calculadoraService.calcularIdade(strDataNascimento);
		
		idadeDto.setIdade(idade);
		
		return idadeDto;
	}

}
