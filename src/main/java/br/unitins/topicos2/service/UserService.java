package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.UserDTO;
import br.unitins.topicos2.dto.UserResponseDTO;
import jakarta.validation.Valid;

public interface UserService {

    public UserResponseDTO insert(@Valid UserDTO dto);

    public UserResponseDTO update(UserDTO dto, Long id);

    public UserResponseDTO updateNameImagem(Long id, String nameImagem) ;

    public void delete(Long id);

    public UserResponseDTO findById(Long id);

    public List<UserResponseDTO> findByName(String name);

    public UserResponseDTO findByLoginAndPassword(String login, String password);

    public UserResponseDTO findByLogin(String login);

    public List<UserResponseDTO> findByAll(); 
    
}
