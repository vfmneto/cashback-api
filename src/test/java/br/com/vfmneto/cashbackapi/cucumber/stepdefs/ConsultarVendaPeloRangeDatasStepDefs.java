package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.repository.VendaRepository;
import cucumber.api.Format;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConsultarVendaPeloRangeDatasStepDefs extends StepDefs {

    @When("^consultar venda entre \"([^\"]*)\" e \"([^\"]*)\"$")
    public void consultarVendaEntreE(String dataInicial, String dataFinal) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^deveria retornar as vendas abaixo:$")
    public void deveriaRetornarAsVendasAbaixo(@Format("dd/MM/yyyy") List<Venda> vendasEsperada) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
