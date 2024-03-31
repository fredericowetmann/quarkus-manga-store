package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.AddressDTO;
import br.unitins.topicos2.dto.AddressResponseDTO;
import br.unitins.topicos2.model.Address;
import br.unitins.topicos2.repository.AddressRepository;
import br.unitins.topicos2.repository.CityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import br.unitins.topicos2.validation.ValidationException;

@ApplicationScoped
public class AddressServiceImpl implements AddressService{

    @Inject
    AddressRepository repository;

    @Inject
    CityRepository cityRepository;

    @Override
    @Transactional
    public AddressResponseDTO insert(AddressDTO dto) {

        if(dto.name().isEmpty()){
            throw new ValidationException("400", "O endereço deve possuir um nome");
        } else if(dto.postalCode().isEmpty() || dto.address().isEmpty()){
            throw new ValidationException("400", "Os valores de CEP e Endereço devem ser informados");
        }

        Address address = new Address();

        address.setName(dto.name());
        address.setPostalCode(dto.postalCode());
        address.setAddress(dto.address());
        address.setComplement(dto.complement());

        if(dto.city() == 0 || dto.city() == null){
            throw new ValidationException("400", "A cidade deve ser informada");
        }
        
        address.setCity(cityRepository.findById(dto.city()));

        repository.persist(address);

        return AddressResponseDTO.valueOf(address);

    }

    @Override
    @Transactional
    public AddressResponseDTO update(Long idAddress, AddressDTO dto) {
        if(repository.findById(idAddress) == null) {
            throw new NotFoundException("Endereço não encontrado");
        }

        if(dto.name().isEmpty()){
            throw new ValidationException("400", "O endereço deve possuir um nome");
        } else if(dto.postalCode().isEmpty() || dto.address().isEmpty()){
            throw new ValidationException("400", "Os valores de CEP e Endereço devem ser informados");
        }

        if(dto.city() == 0 || dto.city() == null){
            throw new ValidationException("400", "A cidade deve ser informada");
        }

        Address address = repository.findById(idAddress);

            address.setName(dto.name());
            address.setPostalCode(dto.postalCode());
            address.setAddress(dto.address());
            address.setComplement(dto.complement());
            address.setCity(cityRepository.findById(dto.city()));

        return AddressResponseDTO.valueOf(address);
    }

    @Override
    @Transactional
    public void delete(Long idAddress) {
        if(!repository.deleteById(idAddress)){
            throw new NotFoundException("Endereço não encontrado");
        } 
    }

    @Override
    public List<AddressResponseDTO> findAll() {
        if(repository.listAll().stream().map(e -> AddressResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Não há endereços");
        }
        return repository.listAll().stream().map(e -> AddressResponseDTO.valueOf(e)).toList();
    }

    @Override
    public AddressResponseDTO findById(long id) {
        if(repository.findById(id) == null){
            throw new NotFoundException("Endereço não encontrado");
        }
        return AddressResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<AddressResponseDTO> findByCity(long id) {
        if(repository.findByCity(id).stream().map(e -> AddressResponseDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("Nenhum produto encontrado");
        }

        return repository.findByCity(id).stream().map(e -> AddressResponseDTO.valueOf(e)).toList();
    }
}
