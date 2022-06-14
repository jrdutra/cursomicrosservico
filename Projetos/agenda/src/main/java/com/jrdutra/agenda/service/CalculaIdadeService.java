package com.jrdutra.agenda.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jrdutra.agenda.entity.IdadeEntity;
import com.jrdutra.agenda.exception.ConexaoCalculadoraException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CalculaIdadeService {
	
	private static final Logger LOGGER = LogManager.getLogger(CalculaIdadeService.class);
	
	public Integer calculaIdade(Date dataNascimento) throws ConexaoCalculadoraException {		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDataNascimento = dateFormat.format(dataNascimento);
		String urlBase = "http://localhost:8083/calculadora";
		String urlRequest = urlBase + "/" + strDataNascimento;

		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder().url(urlRequest).build();

		Response response;
		try {
			LOGGER.info("Chamando microsserviço calculadora na URL: {}", urlRequest);
			
			response = client.newCall(request).execute();
			String jsonResposta = response.body().string();
			
			LOGGER.info("O microsserviço calculadora respondeu: {}", jsonResposta);
			
			Gson gson = new Gson(); 
			IdadeEntity idadeEntity = gson.fromJson(jsonResposta, IdadeEntity.class);
			
			return idadeEntity.getIdade();
		} catch (IOException e) {
			LOGGER.info("O microsserviço calculadora não respondeu...");
			throw new ConexaoCalculadoraException("Não foi possível conectar com o microsserviço de calculadora");
		}
	}
	
}
