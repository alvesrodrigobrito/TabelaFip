package com.example.TabelaFip.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/**
 * Lembre-se de que nada na programação é permanente.
 * Você pode experimentar diferentes opções, ver o que
 * funciona para você, fazer alterações e reimplantar.
 * Sempre teste suas alterações completamente antes de
 * enviá-las para um ambiente de produção.
 *
 * Se você estiver tendo problemas com a conexão, pode ser
 * necessário adicionar o certificado raiz do site remoto ao truststore padrão do Java.
 *
 * */
@Component
public class ConsumerApi {
    public String getAddress(String address){

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }
}
