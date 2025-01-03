package com.uydev.mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@RequiredArgsConstructor
public class MapperUtil {
    private final ModelMapper modelMapper;


    public <T> T convert(Object objectToBeConverted, T convertedObject) {
        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
    }
}