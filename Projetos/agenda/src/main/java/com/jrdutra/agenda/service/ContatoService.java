package com.jrdutra.agenda.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrdutra.agenda.entity.ContatoEntity;
import com.jrdutra.agenda.exception.ContatoNaoEncontradoException;
import com.jrdutra.agenda.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository contatoRepository;
	
	public ContatoEntity buscaContatoPorId(Long id) throws Exception {
		
		Optional<ContatoEntity> optionalContatoEntity = contatoRepository.buscarPorId(id);
		
		ContatoEntity contatoEntity;
		
		if(optionalContatoEntity.isEmpty()) {
			throw new ContatoNaoEncontradoException("Contato não encontrado para o id " + id.toString());
		}else {
			contatoEntity = optionalContatoEntity.get();
		}
		
		return contatoEntity;
	}
	
	public ContatoEntity salvarContato(ContatoEntity contatoEntity) {
		ContatoEntity contatoEntitySalvo = contatoRepository.save(contatoEntity);
		return contatoEntitySalvo;
	}
	
	public ContatoEntity alterarContato(ContatoEntity contatoEntityAlterar, Long id) throws Exception {
		
		ContatoEntity contatoEntityBuscado = buscaContatoPorId(id);
		
		contatoEntityBuscado.setDataNascimento(contatoEntityAlterar.getDataNascimento());
		contatoEntityBuscado.setEmail(contatoEntityAlterar.getEmail());
		contatoEntityBuscado.setId(id);
		contatoEntityBuscado.setNome(contatoEntityAlterar.getNome());
		contatoEntityBuscado.setTelefone(contatoEntityAlterar.getTelefone());
		
		
		ContatoEntity contatoEntitySalvo = salvarContato(contatoEntityBuscado);
		
		return contatoEntitySalvo;		
	}
	
	public void deletarContato(Long id) {
		contatoRepository.deleteById(id);
	}
}
