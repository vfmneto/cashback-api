package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConsultarCatalogoDiscosStepDefs extends StepDefs {

    @When("^consultar catalogo de discos pelo gênero \"([^\"]*)\" ordenado pelo nome de forma crescente$")
    public void consultarCatalogoDeDiscosPeloGêneroOrdenadoPeloNomeDeFormaCrescente(Genero genero) throws Throwable {

    }

    @Then("^deveria retornar os discos abaixo:$")
    public void deveriaRetornarOsDiscosAbaixo(List<Disco> discosEsperados) throws Throwable {
        for (int i = 0; i < discosEsperados.size(); i++) {
            resultActions.andExpect(jsonPath("$.content[" + i + "].nomeAlbum").value(discosEsperados.get(i).getNomeAlbum()));
            resultActions.andExpect(jsonPath("$.content[" + i + "].genero").value(discosEsperados.get(i).getGenero().name()));
        }
    }

    @When("^consultar catálogo de discos pelo gênero \"([^\"]*)\", pagina (\\d+) e quantidade por página (\\d+) ordenado pelo nome de forma crescente$")
    public void consultarCatalogoDeDiscosPeloGêneroPaginaEQuantidadePorPáginaOrdenadoPeloNomeDeFormaCrescente(String genero, String numeroPagina, String quantidadePorPagina) throws Throwable {
        resultActions = mockMvc.perform(get("/api/discos/")
                                            .param("genero", genero)
                                            .param("numeroPagina", numeroPagina)
                                            .param("quantidadePorPagina", quantidadePorPagina))
                                        .andExpect(status().isOk());
    }
}
