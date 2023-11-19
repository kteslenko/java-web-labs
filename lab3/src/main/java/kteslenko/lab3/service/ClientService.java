package kteslenko.lab3.service;

import kteslenko.lab3.model.Client;
import kteslenko.lab3.specifications.ClientSpecification;

import java.util.List;

public interface ClientService {
    Client getClientById(Long id);

    List<Client> getAllClients();

    List<Client> getClientsBySpecification(ClientSpecification specification);

    void insertClient(Client client);

    void updateClient(Long id, Client client);

    void deleteClientById(Long id);
}
