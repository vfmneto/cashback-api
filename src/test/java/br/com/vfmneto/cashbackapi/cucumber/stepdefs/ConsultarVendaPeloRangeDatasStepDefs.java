package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.dto.VendaDTO;
import com.jayway.jsonpath.JsonPath;
import cucumber.api.Format;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ConsultarVendaPeloRangeDatasStepDefs extends StepDefs {

    @When("^consultar venda entre as datas \"([^\"]*)\" e \"([^\"]*)\",  pagina (\\d+) e quantidade por página (\\d+)$")
    public void consultarVendaEntreAsDatasEPaginaEQuantidadePorPágina(String dataInicial,
                                                                      String dataFinal,
                                                                      String numeroPagina,
                                                                      String quantidadePorPagina) throws Throwable {
        resultActions = mockMvc.perform(get("/api/vendas")
                                            .param("dataInicial", dataInicial)
                                            .param("dataFinal", dataFinal)
                                            .param("numeroPagina", numeroPagina)
                                            .param("quantidadePorPagina", quantidadePorPagina));
    }

    @Then("^deveria retornar as vendas abaixo:$")
    public void deveriaRetornarAsVendasAbaixo(@Format("dd/MM/yyyy") List<VendaDTO> vendasEsperada) throws Throwable {

        List<VendaDTO> resultado = getResultado();

        for (int i = 0; i < vendasEsperada.size(); i++) {
            assertThat(resultado.get(i).getId(), IsEqual.equalTo(vendasEsperada.get(i).getId()));
            assertThat(resultado.get(i).getData(), IsEqual.equalTo(vendasEsperada.get(i).getData()));
            assertThat(resultado.get(i).getNomeCliente(), IsEqual.equalTo(vendasEsperada.get(i).getNomeCliente()));
            assertThat(resultado.get(i).getCashbackTotal(), IsEqual.equalTo(vendasEsperada.get(i).getCashbackTotal()));
        }
    }

    private List<VendaDTO> getResultado() throws java.io.IOException {
        return asList(objectMapper.readValue(
                                        JsonPath.read(resultActions.andReturn().getResponse().getContentAsString(), "$.content").toString(),
                                        VendaDTO[].class));
    }

}
