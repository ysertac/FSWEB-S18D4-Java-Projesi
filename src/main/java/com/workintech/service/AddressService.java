package com.workintech.service;

import com.workintech.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address find(long id);

    Address save(Address address);

    Address delete(long id);

}
