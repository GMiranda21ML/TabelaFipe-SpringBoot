package br.com.projeto.tabelaFipe.main;

import br.com.projeto.tabelaFipe.model.DadosMarcas;
import br.com.projeto.tabelaFipe.model.Menu;
import br.com.projeto.tabelaFipe.service.ConsumoApi;
import br.com.projeto.tabelaFipe.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final String URL_FIPE = "https://parallelum.com.br/fipe/api/v1/";

    public void iniciaProjeto() {
        Scanner scanner = new Scanner(System.in);
        Menu.titulo();
        Menu.menu();
        int op = scanner.nextInt();
        scanner.nextLine();
        System.out.println();


        String endereco = null;
        if (op == 1) {
            endereco = "carros/marcas";
        } else if (op == 2) {
            endereco = "caminhoes/marcas";
        } else if (op == 3) {
            endereco = "motos/marcas";
        } else {
            System.out.println("Opção invalida");
            System.exit(0);
        }


        ConsumoApi consumoApi = new ConsumoApi();
        String json = consumoApi.obterDadosApi(URL_FIPE + endereco);

        ConverteDados converteDados = new ConverteDados();
        List<DadosMarcas> dadosMarcasCarro = converteDados.obterDadosLista(json, DadosMarcas.class);
        dadosMarcasCarro.stream()
                .sorted(Comparator.comparing(DadosMarcas::codigo))
                .forEach(c -> System.out.println(String.format("Código: %s  " +
                        "Marca: %s", c.codigo(), c.marcaDoCarro())));




    }
}
