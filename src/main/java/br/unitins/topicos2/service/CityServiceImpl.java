package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.CityDTO;
import br.unitins.topicos2.dto.CityResponseDTO;
import br.unitins.topicos2.model.City;
import br.unitins.topicos2.repository.CityRepository;
import br.unitins.topicos2.repository.StateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CityServiceImpl implements CityService {

    @Inject
    CityRepository cityRepository;

    @Inject
    StateRepository stateRepository;

    @Inject
    Validator validator;

    @Override
    public List<CityResponseDTO> getAll(int page, int pageSize) {
        List<City> list = cityRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> CityResponseDTO.valueOf(e)).collect(Collectors.toList());
    }  

    @Override
    public CityResponseDTO findById(Long id) {
        City city = cityRepository.findById(id);
        if (city == null)
            throw new NotFoundException("City não encontrada.");
        return CityResponseDTO.valueOf(city);
    }

    @Override
    @Transactional
    public CityResponseDTO create(CityDTO cityDTO) throws ConstraintViolationException {
        validar(cityDTO);

        City entity = new City();
        entity.setName(cityDTO.name());
        entity.setState(stateRepository.findById(cityDTO.idState()));

        cityRepository.persist(entity);

        return CityResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CityResponseDTO update(Long id, CityDTO cityDTO) throws ConstraintViolationException {
        validar(cityDTO);

        City entity = cityRepository.findById(id);

        entity.setName(cityDTO.name());
        entity.setState(stateRepository.findById(cityDTO.idState()));

        return CityResponseDTO.valueOf(entity);
    }

    private void validar(CityDTO cityDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CityDTO>> violations = validator.validate(cityDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<CityResponseDTO> findByName(String name) {
        List<City> list = cityRepository.findByName(name).list();
        return list.stream().map(e -> CityResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cityRepository.count();
    }
}