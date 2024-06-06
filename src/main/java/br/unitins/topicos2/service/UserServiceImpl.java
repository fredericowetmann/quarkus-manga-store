package br.unitins.topicos2.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.UpdatePasswordDTO;
import br.unitins.topicos2.dto.UpdateUserDTO;
import br.unitins.topicos2.dto.UserBasicDTO;
import br.unitins.topicos2.dto.UserBasicResponseDTO;
import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import br.unitins.topicos2.model.Profile;
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

       if (repository.findByEmail(dto.email()) != null) {
            throw new ValidationException("email", "Email já existe.");
        }

        User novoUser = new User();
        novoUser.setName(dto.name());
        novoUser.setEmail(dto.email());
        novoUser.setCpf(dto.cpf());
        novoUser.setPassword(hashService.getHashPassword(dto.password()));
        novoUser.setProfile(Profile.valueOf(dto.idProfile()));

        repository.persist(novoUser);

        return UserResponseDTO.valueOf(novoUser);
    }

    @Override
    @Transactional
    public UserBasicResponseDTO insertBasicUser(UserBasicDTO dto) {
        if (dto.email() == null || dto.email().isEmpty()) {
            throw new ValidationException("email", "O email não pode estar em branco");
        } else if (dto.password() == null || dto.password().isEmpty()) {
            throw new ValidationException("senha", "A senha não pode estar em branco");
        } else if (dto.name() == null || dto.name().isEmpty()) {
            throw new ValidationException("nome", "O nome não pode estar em branco");
        }

        if (repository.existsByEmail(dto.email())) {
            throw new ValidationException("400", "O email já existe");
        }

        User newUser = new User();

        newUser.setName(dto.name());
        newUser.setEmail(dto.email());
        newUser.setPassword(hashService.getHashPassword(dto.password()));
        newUser.setProfile(Profile.valueOf(1));

        repository.persist(newUser);

        return UserBasicResponseDTO.valueOf(newUser);
    }

    @Override
    @Transactional
    public UserResponseDTO update(UserDTO dto, Long id) {
        User user = repository.findById(id);
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setPassword(dto.password());
        user.setCpf(dto.cpf());

        return UserResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UpdateUserDTO updateUserDTO) {
        User user = repository.findById(userId);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setName(updateUserDTO.name());
        user.setEmail(updateUserDTO.email());
        user.setCpf(updateUserDTO.cpf());

        repository.persist(user);
    }

    @Override
    @Transactional
    public void updatePassword(Long userId, UpdatePasswordDTO updatePasswordDTO) {
        User user = repository.findById(userId);

        if (user == null) {
            throw new RuntimeException("Usuario não foi encontrado");
        }

        String currentHashedPassword = hashService.getHashPassword(updatePasswordDTO.oldPassword());

        if (!currentHashedPassword.equals(user.getPassword())) {
            throw new RuntimeException("Senha antiga está incorreta!");
        }

        String newHashedPassword = hashService.getHashPassword(updatePasswordDTO.newPassword());
        user.setPassword(newHashedPassword);

        repository.persist(user);
    }

    @Override
    @Transactional
    public UserResponseDTO updateImageName(Long id, String imageName) {
        User user = repository.findById(id);
        user.setImageName(imageName);
        return UserResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Usuario não encontrado");
        }
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
    public UserResponseDTO findByEmailAndPassword(String email, String password) {
        User user = repository.findByEmailAndPassword(email, password);
        if (user == null) 
            throw new ValidationException("email", "Email ou password inválido");
        
        return UserResponseDTO.valueOf(user);
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        User user = repository.findByEmail(email);
        if (user == null) 
            throw new ValidationException("email", "Email inválido");
        
        return UserResponseDTO.valueOf(user);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
