package com.jrdutra.calculadora.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

	public Integer calcularIdade(String strDataNascimento) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Integer idade;
		try {
			Date dataNascimento = formatter.parse(strDataNascimento);
			
			Calendar calendarDataNascimento = new GregorianCalendar();
			calendarDataNascimento.setTime(dataNascimento);
			
			Calendar today = Calendar.getInstance();
			
			idade = today.get(Calendar.YEAR) - calendarDataNascimento.get(Calendar.YEAR);
			calendarDataNascimento.add(Calendar.YEAR, idade);
			
			if(today.before(calendarDataNascimento)) {
				idade--;
			}
			
		} catch (ParseException e) {
			idade = 0;
		}
		
		return idade;
	}

}
