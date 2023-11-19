package kteslenko.lab3.service;

import kteslenko.lab3.model.Address;
import kteslenko.lab3.model.Client;
import kteslenko.lab3.repository.AddressRepository;
import kteslenko.lab3.specifications.AddressSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Transactional
@Service
public class AddressServiceImpl implements AddressService {
    private final ClientService clientService;
    private final AddressRepository addressRepository;

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Address with id %d was not found".formatted(id))
        );
    }

    public List<Address> getAddressByClientIdBySpecification(Long clientId, AddressSpecification specification) {
        specification.setClientId(clientId);
        return addressRepository.findAll(specification.get());
    }

    public void insertAddress(Long clientId, Address address) {
        Client client = clientService.getClientById(clientId);

        address.setClient(client);
        addressRepository.save(address);
    }

    public void updateAddress(Long clientId, Long id, Address address) {
        Client client = clientService.getClientById(clientId);

        address.setId(id);
        address.setClient(client);
        addressRepository.save(address);
    }

    public void deleteAddressById(Long id) {
        Address address = getAddressById(id);
        addressRepository.delete(address);
    }
}
