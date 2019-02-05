package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.repository.VendaRepository;
import cucumber.api.Format;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConsultarVendaPorIdentificadorStepDefs extends StepDefs {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Given("^que as vendas abaixo estejam cadastradas:$")
    public void queAsVendasAbaixoEstejamCadastradas(@Format("dd/MM/yyyy") List<Venda> vendas) throws Throwable {
        vendaRepository.saveAll(vendas);
    }

    @When("^consultar venda pelo identificador (\\d+)$")
    public void consultarVendaPeloIdentificador(int identificador) throws Throwable {
        resultActions = mockMvc.perform(get("/api/vendas/{id}", identificador));
    }

    @Then("^deveria retornar a venda com nome cliente \"([^\"]*)\" e cashback total (\\d+)$")
    public void deveriaRetornarAVendaComNomeClienteECashbackTotal(String nomeCliente, BigDecimal cashbackTotal) throws Throwable {
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.nomeCliente").value(nomeCliente));
        resultActions.andExpect(jsonPath("$.cashbackTotal").value(cashbackTotal));
    }

    @Then("^deveria retornar venda não encontrada$")
    public void deveriaRetornarVendaNãoEncontrada() throws Throwable {
        resultActions.andExpect(status().isNotFound());
    }
}
