package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.StateDTO;
import br.unitins.topicos2.dto.StateResponseDTO;
import br.unitins.topicos2.model.State;
import br.unitins.topicos2.repository.StateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class StateServiceImpl implements StateService {

    @Inject
    StateRepository stateRepository;

    @Inject
    Validator validator;

    @Override
    public List<StateResponseDTO> getAll(int page, int pageSize) {
        List<State> list = stateRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> StateResponseDTO.valueOf(e)).collect(Collectors.toList());
    }    

    @Override
    public StateResponseDTO findById(Long id) {
        State state = stateRepository.findById(id);
        if (state == null)
            throw new NotFoundException("State não encontrado.");
        return StateResponseDTO.valueOf(state);
    }

    @Override
    @Transactional
    public StateResponseDTO create(@Valid StateDTO stateDTO) throws ConstraintViolationException {
        //validar(stateDTO);

        State entity = new State();
        entity.setName(stateDTO.name());
        entity.setAcronym(stateDTO.acronym());

        stateRepository.persist(entity);

        return StateResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public StateResponseDTO update(Long id, StateDTO stateDTO) throws ConstraintViolationException{
        validar(stateDTO);
   
        State entity = stateRepository.findById(id);

        entity.setName(stateDTO.name());
        entity.setAcronym(stateDTO.acronym());

        return StateResponseDTO.valueOf(entity);
    }

    private void validar(StateDTO stateDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<StateDTO>> violations = validator.validate(stateDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        stateRepository.deleteById(id);
    }

    @Override
    public List<StateResponseDTO> findByName(String name) {
        List<State> list = stateRepository.findByName(name).list();
        return list.stream().map(e -> StateResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return stateRepository.count();
    }

}