package br.com.projeto.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarcas(@JsonAlias("codigo") String codigo,
                          @JsonAlias("nome") String marcaDoCarro) {
}
