package br.unitins.topicos2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.PhoneDTO;
import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import br.unitins.topicos2.model.Profile;
import br.unitins.topicos2.model.Phone;
import br.unitins.topicos2.model.User;
import br.unitins.topicos2.repository.UserRepository;
import br.unitins.topicos2.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository repository;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UserResponseDTO insert(@Valid UserDTO dto) {

       if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }

        User novoUser = new User();
        novoUser.setName(dto.name());
        novoUser.setLogin(dto.login());

        novoUser.setPassword(hashService.getHashPassword(dto.password()));

        novoUser.setProfile(Profile.valueOf(dto.idProfile()));

        if (dto.listaPhone() != null && 
                    !dto.listaPhone().isEmpty()){
            novoUser.setListaPhone(new ArrayList<Phone>());
            for (PhoneDTO tel : dto.listaPhone()) {
                Phone phone = new Phone();
                phone.setAreaCode(tel.areaCode());
                phone.setNumber(tel.number());
                novoUser.getListaPhone().add(phone);
            }
        }

        repository.persist(novoUser);

        return UserResponseDTO.valueOf(novoUser);
    }

    @Override
    @Transactional
    public UserResponseDTO update(UserDTO dto, Long id) {
        User user = repository.findById(id);
        user.setLogin(dto.login());
        user.setName(dto.name());
        user.setPassword(dto.password());

        // Atualiza os telefones do usuário
    if (dto.listaPhone() != null && !dto.listaPhone().isEmpty()) {
        user.getListaPhone().clear(); // Limpa a lista de telefones existente

        for (PhoneDTO tel : dto.listaPhone()) {
            Phone phone = new Phone();
            phone.setAreaCode(tel.areaCode());
            phone.setNumber(tel.number());
            user.getListaPhone().add(phone);
        }
    }
        
        return UserResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public UserResponseDTO updateNameImagem(Long id, String nameImagem) {
        User user = repository.findById(id);
        user.setNameImagem(nameImagem);
        return UserResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
    }

    @Override
    public UserResponseDTO findById(Long id) {
        return UserResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UserResponseDTO> findByName(String name) {
             return null;
    }

    @Override
    public List<UserResponseDTO> getAll(int page, int pageSize) {
        List<User> list = repository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        if(repository.listAll().stream().map(e -> UserResponseDTO.valueOf(e)).toList().isEmpty()) {
            throw new NotFoundException("Publisher não encontrada");
            }
        return list.stream().map(e -> UserResponseDTO.valueOf(e)).collect(Collectors.toList());
    }  

    @Override
    public UserResponseDTO findByLoginAndPassword(String login, String password) {
        User user = repository.findByLoginAndPassword(login, password);
        if (user == null) 
            throw new ValidationException("login", "Login ou password inválido");
        
        return UserResponseDTO.valueOf(user);
    }

    @Override
    public UserResponseDTO findByLogin(String login) {
        User user = repository.findByLogin(login);
        if (user == null) 
            throw new ValidationException("login", "Login inválido");
        
        return UserResponseDTO.valueOf(user);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
