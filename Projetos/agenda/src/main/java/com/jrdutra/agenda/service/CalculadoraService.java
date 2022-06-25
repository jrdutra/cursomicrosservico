package com.jrdutra.agenda.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jrdutra.agenda.dto.IdadeDto;
import com.jrdutra.agenda.exception.ConexaoCalculadoraException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CalculadoraService {
	
	public IdadeDto calculaIdade(Date dataNascimneto) throws ConexaoCalculadoraException {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String strDataNascimento = formatter.format(dataNascimneto);
		//http://localhost:8803/calculadora
		String urlBase = "http://localhost:8803/calculadora";
		//http://localhost:8803/calculadora/26-09-1989
		String urlRequest = urlBase + "/" + strDataNascimento;
		
		OkHttpClient client= new OkHttpClient();
		
		Request request  = new Request.Builder().url(urlRequest).build();
		
		try {
			Response response = client.newCall(request).execute();
			
			String jsonResposta = response.body().string();
			
			System.out.println("JsonResposta: " + jsonResposta);
			
			Gson gson = new Gson();
			
			IdadeDto idadeDto = gson.fromJson(jsonResposta, IdadeDto.class);
			
			return idadeDto;
		} catch (IOException e) {
			throw new ConexaoCalculadoraException("Não foi possível conectar no microsserviço calculadora.");
		}
	}

}
