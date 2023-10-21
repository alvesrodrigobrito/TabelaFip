package com.example.TabelaFip.main;

import com.example.TabelaFip.model.DataType;
import com.example.TabelaFip.model.Models;
import com.example.TabelaFip.model.Vehicle;
import com.example.TabelaFip.service.ConsumerApi;
import com.example.TabelaFip.service.ConvertsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class MainApp {
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private Scanner scan = new Scanner(System.in);
    @Autowired
    ConsumerApi consumerApi;
    @Autowired
    ConvertsData convertsData;
    public void displayMenu()  {

        String menu = "*** OPCOES ***\n\ncarro\nmoto\ncaminhao\n\n" +
                "digite uma das opções para consultar:";
        System.out.println(menu);
        String opcao = scan.nextLine();

        String urlRota = null;
        if (opcao.toLowerCase().contains("mot")){
            urlRota = URL_BASE + "motos/marcas";
        } else if (opcao.toLowerCase().contains("car")){
            urlRota = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("cam")){
            urlRota = URL_BASE + "caminhoes/marcas";
        }
        System.out.println(urlRota);

        String json = consumerApi.getAddress(urlRota);
        System.out.println(json);

        var brands = convertsData.getListJson(json, DataType.class);
        brands.stream()
                .sorted(Comparator.comparing(DataType::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consultar");
        String codeBrands = scan.nextLine();

        System.out.println("\nModelos dessa marca");
        urlRota = urlRota + "/" + codeBrands + "/modelos";
        json = consumerApi.getAddress(urlRota);
        Models modelsList = convertsData.getData(json, Models.class);
        modelsList.models().stream()
                .sorted(Comparator.comparing(DataType::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho no nome do veiculo a ser buscado");
        String nameVehicle = scan.nextLine();

        List<DataType> filteredNames = modelsList.models().stream()
                .filter(m -> m.nome().toLowerCase().contains(nameVehicle.toLowerCase()))
                .collect(Collectors.toList()); //armazena em uma nova lista

        System.out.println("\nModels filtered");
        filteredNames.forEach(System.out::println);

        System.out.println("\nDigite o código do modelo para buscar os valores de avaliação");
        String codeModel = scan.nextLine();

        urlRota = urlRota + "/" + codeModel + "/anos";

        json = consumerApi.getAddress(urlRota);
        List<DataType> years = convertsData.getListJson(json, DataType.class);

        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            String endYears = urlRota + "/" + years.get(i).codigo();
            json = consumerApi.getAddress(endYears);
            Vehicle vehicle = convertsData.getData(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("\nTodos os veículos filtrado com avaliações por ano");
        vehicles.forEach(System.out::println);

    }
}
