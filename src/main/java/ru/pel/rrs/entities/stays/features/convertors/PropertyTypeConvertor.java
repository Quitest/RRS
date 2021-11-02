package ru.pel.rrs.entities.stays.features.convertors;

import ru.pel.rrs.entities.stays.features.PropertyType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PropertyTypeConvertor implements AttributeConverter<PropertyType, String> {
    @Override
    public String convertToDatabaseColumn(PropertyType propertyType) {
        if (propertyType==null){
            return null;
        }
        return propertyType.getValue();
    }

    @Override
    public PropertyType convertToEntityAttribute(String s) {
        if (s==null){return null;}
        return Stream.of(PropertyType.values())
                .filter(p->p.getValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
