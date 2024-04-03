package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import jakarta.validation.Valid;

public interface UserService {

    UserResponseDTO insert(@Valid UserDTO dto);

    UserResponseDTO update(UserDTO dto, Long id);

    UserResponseDTO updateNameImagem(Long id, String nameImagem) ;

    void delete(Long id);

    UserResponseDTO findById(Long id);

    List<UserResponseDTO> findByName(String name);

    UserResponseDTO findByLoginAndPassword(String login, String password);

    UserResponseDTO findByLogin(String login);

    List<UserResponseDTO> getAll(int page, int pageSize); 

    long count();
    
}
