package br.unitins.topicos2.model.converterjpa;

import br.unitins.topicos2.model.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return(gender == null ? null: gender.getId());
    }

    @Override
    public Gender convertToEntityAttribute(Integer id) {
        return Gender.valueOf(id);
    }
    
}
