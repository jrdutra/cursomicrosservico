package com.jrdutra.calculadora.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jrdutra.calculadora.entity.IdadeEntity;
import com.jrdutra.calculadora.service.CalculadoraService;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {
	
	@Autowired
	private CalculadoraService calculadoraService;
	
	private static final Logger LOGGER = LogManager.getLogger(CalculadoraController.class);
	
	@GetMapping(value = "/{dataNascimento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public IdadeEntity calculaIdade(@PathVariable("dataNascimento") String strDataNascimento) {	
		LOGGER.info("Data de nascimento recebida: {}", strDataNascimento);
		return calculadoraService.calculaIdade(strDataNascimento);
	}

}
