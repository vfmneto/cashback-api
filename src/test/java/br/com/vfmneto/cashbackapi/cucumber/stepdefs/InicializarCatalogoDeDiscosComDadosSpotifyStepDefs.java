package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.domain.Genero;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;

import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InicializarCatalogoDeDiscosComDadosSpotifyStepDefs extends StepDefs {

    @Given("^que a aplicação esteja inicializada$")
    public void queAAplicacaoEstejaInicializada() throws Throwable {
        mockMvc.perform(get("/management/health/"))
                .andExpect(status().isOk())
                .andExpect(content().string(CoreMatchers.containsString("UP")));
    }

    @Then("^deveria conter as quantidades discos cadastrados por gênero:$")
    public void deveriaConterAsQuantidadesDiscosCadastradosPorGenero(Map<Genero, Integer> generos) throws Throwable {
        generos.keySet().forEach(genero -> {
            Integer quantidade = jdbcTemplate.queryForObject("select count(d.id) from disco d where d.genero = ?", Integer.class, genero.name());
            assertThat(quantidade, IsEqual.equalTo(generos.get(genero)));
        });
    }

}
