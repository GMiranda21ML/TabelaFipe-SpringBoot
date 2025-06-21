package br.com.projeto.tabelaFipe.main;

import br.com.projeto.tabelaFipe.model.DadosMarcasCarro;
import br.com.projeto.tabelaFipe.model.Menu;
import br.com.projeto.tabelaFipe.service.ConsumoApi;
import br.com.projeto.tabelaFipe.service.ConverteDados;

import java.util.Comparator;
import java.util.List;

public class Main {
    private final String ENDERECO_CARRO = "https://parallelum.com.br/fipe/api/v1/carros/marcas";

    public void iniciaProjeto() {
        Menu.titulo();

        ConsumoApi consumoApi = new ConsumoApi();
        String json = consumoApi.obterDadosApi(ENDERECO_CARRO);
        System.out.println(json);

        ConverteDados converteDados = new ConverteDados();
        List<DadosMarcasCarro> dadosMarcasCarro = converteDados.obterDadosLista(json, DadosMarcasCarro.class);
        dadosMarcasCarro.stream()
                .sorted(Comparator.comparing(DadosMarcasCarro::codigo))
                .forEach(c -> System.out.println(String.format("Código: %s  " +
                        "Marca: %s", c.codigo(), c.marcaDoCarro())));




    }
}
