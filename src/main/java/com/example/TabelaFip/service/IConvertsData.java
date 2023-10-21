package com.example.TabelaFip.service;

import java.util.List;

public interface IConvertsData {
    <T> T getData(String json, Class<T> tClass);
    <T> List<T> getListJson(String json, Class<T> tClass);
}
