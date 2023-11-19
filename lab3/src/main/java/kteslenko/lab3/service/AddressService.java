package kteslenko.lab3.service;

import kteslenko.lab3.model.Address;
import kteslenko.lab3.specifications.AddressSpecification;

import java.util.List;

public interface AddressService {
    Address getAddressById(Long id);

    List<Address> getAddressByClientIdBySpecification(Long clientId, AddressSpecification specification);

    void insertAddress(Long clientId, Address address);

    void updateAddress(Long clientId, Long id, Address address);

    void deleteAddressById(Long id);
}
