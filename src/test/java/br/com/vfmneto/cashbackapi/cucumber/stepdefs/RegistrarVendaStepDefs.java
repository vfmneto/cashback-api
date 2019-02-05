package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.PorcetagemCashback;
import br.com.vfmneto.cashbackapi.dto.DiscoDTO;
import br.com.vfmneto.cashbackapi.dto.ItemVendaDTO;
import br.com.vfmneto.cashbackapi.dto.VendaDTO;
import br.com.vfmneto.cashbackapi.repository.DiscoRepository;
import br.com.vfmneto.cashbackapi.repository.PorcetagemCashbackRepository;
import br.com.vfmneto.cashbackapi.util.ObtedorDataUtilStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cucumber.api.Format;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.rmi.CORBA.ValueHandler;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrarVendaStepDefs extends StepDefs {

    private List<Disco> discosCadastrados;

    @Given("^que a data atual seja \"([^\"]*)\"$")
    public void queADataAtualSeja(@Format("dd/MM/yyyy") Date dataAtual) throws Throwable {
        obtedorDataUtil.setDataAtual(dataAtual);
    }

    @When("^registrar a venda com nome cliente \"([^\"]*)\" e com os discos selecionados:$")
    public void registrarAVendaComNomeClienteEComOsDiscosSelecionados(String nomeCliente, List<Disco> discos) throws Throwable {

        discosCadastrados = discoRepository.saveAll(discos);

        List<ItemVendaDTO> itensDTOS = discosCadastrados.stream().map(disco -> {
            DiscoDTO discoDTO = new DiscoDTO();
            discoDTO.setId(disco.getId());
            ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
            itemVendaDTO.setDisco(discoDTO);
            return itemVendaDTO;
        }).collect(Collectors.toList());

        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNomeCliente(nomeCliente);
        vendaDTO.setItensVenda(itensDTOS);

        resultActions = mockMvc.perform(post("/api/vendas")
                                                        .contentType(APPLICATION_JSON_UTF8)
                                                        .content(new ObjectMapper().writeValueAsBytes(vendaDTO)));
    }

    @Then("^deveria registrar venda com valor total de retorno (\\d+)$")
    public void deveriaRegistrarVendaComValorTotalDeRetorno(BigDecimal valorTotalRetorno) throws Throwable {
        resultActions.andExpect(jsonPath("$.cashbackTotal").value(valorTotalRetorno));
    }

    @And("^o valor de retorno de cada disco deveria:$")
    public void oValorDeRetornoDeCadaDiscoDeveria(Map<String, BigDecimal> itensVendaEsperado) throws Throwable {

        VendaDTO vendaDTO = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), VendaDTO.class);

        Map<String, BigDecimal> itensVendaAtual = new HashMap<>();
        vendaDTO.getItensVenda().forEach(itemVendaDTO -> {
            itensVendaAtual.put(itemVendaDTO.getDisco().getNomeAlbum(), itemVendaDTO.getCashback());
        });

        itensVendaEsperado.forEach((key, bigDecimal) -> {
            Assert.assertThat(itensVendaAtual.get(key).doubleValue(), IsEqual.equalTo(bigDecimal.doubleValue()));
        });
    }

    @Given("^a tabela de porcetagem de cashback abaixo:$")
    public void aTabelaDePorcetagemDeCashbackAbaixo(List<PorcetagemCashback> porcetagemCashbacks) throws Throwable {
        porcetagemCashbackRepository.deleteAll();
        porcetagemCashbackRepository.saveAll(porcetagemCashbacks);
    }
}
