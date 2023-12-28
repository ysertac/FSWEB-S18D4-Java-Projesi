package com.workintech.service;

import com.workintech.entity.Address;
import com.workintech.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address find(long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            return optionalAddress.get();
        }
        return null;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address delete(long id) {
        Address address = find(id);
        if (address != null) {
            addressRepository.delete(address);
            return address;
        }
        return null;
    }
}
