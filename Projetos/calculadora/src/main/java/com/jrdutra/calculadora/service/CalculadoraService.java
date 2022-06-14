package com.jrdutra.calculadora.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.jrdutra.calculadora.entity.IdadeEntity;

@Service
public class CalculadoraService {
	
	private static final Logger LOGGER = LogManager.getLogger(CalculadoraService.class);
	
	public IdadeEntity calculaIdade(String strDataNascimento) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
		IdadeEntity idadeEntity = new IdadeEntity();
		try {
			LOGGER.info("Calculando idade para a dasta de nascimento {}", strDataNascimento);
			
			Date dataNascimento = formatter.parse(strDataNascimento);
			
			Calendar calendarDataNascimento = new GregorianCalendar();
	        calendarDataNascimento.setTime(dataNascimento);
	        
	        Calendar today = Calendar.getInstance();

	        Integer idade = today.get(Calendar.YEAR) - calendarDataNascimento.get(Calendar.YEAR);
	        calendarDataNascimento.add(Calendar.YEAR, idade);

	        if(today.before(calendarDataNascimento)) {
	            idade--;
	        }
	        
			idadeEntity.setIdade(idade);
			LOGGER.info("Idade calculada: {}", idade);
			
		} catch (ParseException e) {
			LOGGER.error("Houve um problema ao calcular a idade para a data informada: {}", strDataNascimento);
			
			idadeEntity.setIdade(0);
			return idadeEntity;
		}
		
		return idadeEntity;
	}
}
