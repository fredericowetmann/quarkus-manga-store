package br.unitins.topicos2.service;

import br.unitins.topicos2.dto.UserResponseDTO;

public interface JwtService {

    public String generateJwt(UserResponseDTO dto);
    
}
