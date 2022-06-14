package com.jrdutra.agenda.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrdutra.agenda.entity.ContatoEntity;
import com.jrdutra.agenda.exception.ContatoNaoEncontradoException;
import com.jrdutra.agenda.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(ContatoService.class);
	
	public ContatoEntity buscaCotatoPorId(Long id) throws ContatoNaoEncontradoException {
		
		ContatoEntity contatoEntityRetorno = new ContatoEntity();
		
		Optional<ContatoEntity> resultadoBusca = contatoRepository.buscarPorId(id);
		
		if(resultadoBusca.isEmpty()){
			LOGGER.info("Contato não encontrado para o id {}", id);
			throw new ContatoNaoEncontradoException("Contato não encontrado para o id " + id.toString());
		}
		
		contatoEntityRetorno = resultadoBusca.get();
		LOGGER.info("Contato encontrado.");
			
		return contatoEntityRetorno;
	}

	public ContatoEntity salvarContato(ContatoEntity contatoEntity) {
		LOGGER.info("Salvando contato...");
		ContatoEntity contatoEntitySalvo = contatoRepository.save(contatoEntity);
		return contatoEntitySalvo;
	}
	
	public ContatoEntity alterarContato(ContatoEntity contatoEntityAlterar, Long id) throws ContatoNaoEncontradoException {
		LOGGER.info("Alterando contato de id {} para {}", id, contatoEntityAlterar);
		
		ContatoEntity contatoEntityBuscado = this.buscaCotatoPorId(id);
		
		contatoEntityBuscado.setDataNascimento(contatoEntityAlterar.getDataNascimento());
		contatoEntityBuscado.setEmail(contatoEntityAlterar.getEmail());
		contatoEntityAlterar.setNome(contatoEntityAlterar.getNome());
		contatoEntityAlterar.setTelefone(contatoEntityAlterar.getTelefone());
		contatoEntityAlterar.setId(id);
		
		ContatoEntity contatoEntityAlterado = contatoRepository.save(contatoEntityAlterar);
		
		return contatoEntityAlterado;
	}
	
	public void deletarContato(Long id) {
		contatoRepository.deleteById(id);
	}
}
