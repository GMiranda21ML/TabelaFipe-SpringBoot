package br.com.projeto.tabelaFipe.main;

import br.com.projeto.tabelaFipe.model.*;
import br.com.projeto.tabelaFipe.service.ConsumoApi;
import br.com.projeto.tabelaFipe.service.ConverteDados;

import javax.xml.transform.Source;
import java.util.*;

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
            endereco = URL_FIPE + "carros/marcas";
        } else if (op == 2) {
            endereco = URL_FIPE + "caminhoes/marcas";
        } else if (op == 3) {
            endereco = URL_FIPE + "motos/marcas";
        } else {
            System.out.println("Opção invalida");
            System.exit(0);
        }


        ConsumoApi consumoApi = new ConsumoApi();
        String json = consumoApi.obterDadosApi(endereco);

        ConverteDados converteDados = new ConverteDados();
        List<DadosMarcas> dadosMarcasCarro = converteDados.obterDadosLista(json, DadosMarcas.class);
        dadosMarcasCarro.stream()
                .sorted(Comparator.comparing(DadosMarcas::codigo))
                .forEach(c -> System.out.println(String.format("Código: %s  " +
                        "Marca: %s", c.codigo(), c.marcaDoCarro())));

        System.out.print("Informe o código da marca para consultar os modelos dos veículos: ");
        String codigo = scanner.nextLine();

        endereco = endereco + "/" + codigo + "/modelos";
        json = consumoApi.obterDadosApi(endereco);
        Modelos listaDeModelos = converteDados.obterDados(json, Modelos.class);
        try {
            System.out.println("\nLista de Modelos: ");
            listaDeModelos.modelos().stream()
                    .sorted(Comparator.comparing(DadosModelos::codigo))
                    .forEach(m -> System.out.println(String.format("Código: %s  " +
                            "Marca: %s", m.codigo(), m.modelo())));
        } catch (NullPointerException e) {
            System.out.println("Código incorreto: " + e.getMessage());
        }

        System.out.println("Digite um trecho do nome do veículo para consulta: ");
        String trechoVeiculo = scanner.nextLine().toUpperCase();

        System.out.println("\nLista de Modelos filtrada: ");
        listaDeModelos.modelos().stream()
                .filter(v -> v.modelo().toUpperCase().contains(trechoVeiculo))
                .forEach(v -> System.out.println(String.format("Código: %s  " +
                        "Marca: %s", v.codigo(), v.modelo())));

        System.out.print("Digite o código do modelo para consultar valores do veículo: ");
        String codigoVeiculo = scanner.nextLine();

        endereco = endereco + "/" + codigoVeiculo + "/anos";
        json = consumoApi.obterDadosApi(endereco);
        List<DadosAnos> listaAnos = null;
        try {
            listaAnos = converteDados.obterDadosLista(json, DadosAnos.class);
        } catch (NullPointerException e) {
            System.out.println("Código incorreto: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Código incorreto: " + e.getMessage());
        }

        List<Veiculo> listaDeVeiculos = new ArrayList<>();
        for (int i = 0; i < listaAnos.size(); i++) {
            String enderecoFinal = endereco + "/" + listaAnos.get(i).codigo();
            json = consumoApi.obterDadosApi(enderecoFinal);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            listaDeVeiculos.add(veiculo);
        }

        System.out.println("\nLista de todos os veículos filtrados com avaliacões por ano: ");
        listaDeVeiculos.forEach(System.out::println);


    }

}
