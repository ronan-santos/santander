package com.br.santander.application.business;

import static com.br.santander.application.infraestruture.Message.CLIENT_NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.santander.application.business.exception.NotFoundException;
import com.br.santander.application.repository.ClientRepository;
import com.br.santander.application.repository.model.external.ClientDTO;
import com.br.santander.application.repository.model.internal.Client;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository repository;
	
	public Client getBy(Long id) {
		
		
		return repository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(CLIENT_NOT_FOUND));
	}
	
	public List<ClientDTO> findAll(){
		
		return repository
				.findAll()
				.stream()
					.map(ClientDTO::new)
				.toList();
	}
	
	public ClientDTO save(ClientDTO clientDto) {
		
		final Client client = repository.saveAndFlush(Client.toClient(clientDto));
		
		return new ClientDTO(client);
	}
}
