package com.example.TabelaFip.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertsData implements IConvertsData{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T getData(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //Modelando as classes da aplicação
    @Override
    public <T> List<T> getListJson(String json, Class<T> tClass) {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, tClass);
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
