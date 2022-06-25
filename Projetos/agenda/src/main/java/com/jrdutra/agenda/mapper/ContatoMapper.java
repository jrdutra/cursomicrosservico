package com.jrdutra.agenda.mapper;

import com.jrdutra.agenda.dto.ContatoDto;
import com.jrdutra.agenda.dto.IdadeDto;
import com.jrdutra.agenda.entity.ContatoEntity;

public class ContatoMapper {
	
	public static ContatoDto contatoEntityToContatoDto(ContatoEntity contatoEntity) {
		ContatoDto contatoDto = new ContatoDto();

		contatoDto.setEmail(contatoEntity.getEmail());
		contatoDto.setId(contatoEntity.getId());
		contatoDto.setNome(contatoEntity.getNome());
		contatoDto.setTelefone(contatoEntity.getTelefone());
		
		return contatoDto;
	}
	
	public static ContatoDto contatoEntityToContatoDto(ContatoEntity contatoEntity, IdadeDto idadeDto) {
		ContatoDto contatoDto = contatoEntityToContatoDto(contatoEntity);
		
		contatoDto.setIdade(idadeDto.getIdade());
		
		return contatoDto;
	}

}
