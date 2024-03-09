package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.State;

public record StateResponseDTO(
    Long id,
    String name,
    String abbreviation
) {
    public StateResponseDTO valueOf(State state){
        return new StateResponseDTO(state.getId(), state.getname(), state.getabbreviation());
    }
    
}
