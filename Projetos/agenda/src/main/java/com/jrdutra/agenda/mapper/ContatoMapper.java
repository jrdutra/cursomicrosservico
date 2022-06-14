package com.jrdutra.agenda.mapper;

import com.jrdutra.agenda.dto.ContatoDto;
import com.jrdutra.agenda.entity.ContatoEntity;

public class ContatoMapper {
	
	public static ContatoDto toContatoDto(ContatoEntity contatoEntity, Integer idade) {
		ContatoDto dto = new ContatoDto();
		
		dto.setEmail(contatoEntity.getEmail());
		dto.setNome(contatoEntity.getNome());
		dto.setTelefone(contatoEntity.getTelefone());
		dto.setId(contatoEntity.getId());
		dto.setDataNascimento(contatoEntity.getDataNascimento());
		dto.setIdade(idade);
		
		return dto;
	}

}
