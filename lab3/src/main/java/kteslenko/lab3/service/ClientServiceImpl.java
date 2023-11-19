package kteslenko.lab3.service;

import kteslenko.lab3.model.Client;
import kteslenko.lab3.repository.ClientRepository;
import kteslenko.lab3.specifications.ClientSpecification;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Transactional
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Client with id %d was not found".formatted(id))
        );
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> getClientsBySpecification(ClientSpecification specification) {
        return clientRepository.findAll(specification.get());
    }

    public void insertClient(Client client) {
        throwIfExists(client);
        clientRepository.save(client);
    }

    public void updateClient(Long id, Client client) {
        client.setId(id);
        throwIfExists(client);
        clientRepository.save(client);
    }

    private void throwIfExists(Client client) {
        clientRepository.findByPhone(client.getPhone())
                .filter(existing -> !existing.getId().equals(client.getId()))
                .ifPresent(existing -> {
                    throw new DataIntegrityViolationException("Client with phone %s already exists"
                            .formatted(client.getPhone()));
                });

        clientRepository.findByEmail(client.getEmail())
                .filter(existing -> !existing.getId().equals(client.getId()))
                .ifPresent(existing -> {
                    throw new DataIntegrityViolationException("Client with email %s already exists"
                            .formatted(client.getEmail()));
                });
    }

    public void deleteClientById(Long id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }
}
