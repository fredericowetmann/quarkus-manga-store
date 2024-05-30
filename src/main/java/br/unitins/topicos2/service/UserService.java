package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.UserBasicDTO;
import br.unitins.topicos2.dto.UserBasicResponseDTO;
import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import jakarta.validation.Valid;

public interface UserService {

    UserResponseDTO insert(@Valid UserDTO dto);

    UserBasicResponseDTO insertBasicUser(UserBasicDTO dto);

    UserResponseDTO update(UserDTO dto, Long id);

    UserResponseDTO updateImageName(Long id, String imageName);

    void delete(Long id);

    UserResponseDTO findById(Long id);

    List<UserResponseDTO> findByName(String name);

    UserResponseDTO findByEmailAndPassword(String email, String password);

    UserResponseDTO findByEmail(String email);

    List<UserResponseDTO> getAll(int page, int pageSize); 

    long count();
    
}
