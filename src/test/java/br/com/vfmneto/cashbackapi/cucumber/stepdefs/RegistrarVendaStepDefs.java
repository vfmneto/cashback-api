package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.PorcetagemCashback;
import br.com.vfmneto.cashbackapi.dto.DiscoDTO;
import br.com.vfmneto.cashbackapi.dto.ItemVendaDTO;
import br.com.vfmneto.cashbackapi.dto.VendaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.Format;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.core.IsEqual;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RegistrarVendaStepDefs extends StepDefs {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT_DD_MM_YYYY = new SimpleDateFormat(DD_MM_YYYY);

    private List<Disco> discosCadastrados;

    @Given("^que a data atual seja \"([^\"]*)\"$")
    public void queADataAtualSeja(@Format("dd/MM/yyyy") Date dataAtual) {
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

    @Then("^deveria registrar venda com nome cliente \"([^\"]*)\", data \"([^\"]*)\" e com valor total de cashback (\\d+)$")
    public void deveriaRegistrarVendaComNomeClienteDataEComValorTotalDeCashback(String nomeCliente, @Format(DD_MM_YYYY) Date dataVenda, BigDecimal valorTotalCashback) throws Throwable {

        VendaDTO vendaDTO = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), VendaDTO.class);

        assertThat(vendaDTO.getNomeCliente(), IsEqual.equalTo(nomeCliente));
        assertThat(SIMPLE_DATE_FORMAT_DD_MM_YYYY.format(vendaDTO.getData()), IsEqual.equalTo(SIMPLE_DATE_FORMAT_DD_MM_YYYY.format(dataVenda)));
        assertThat(vendaDTO.getCashbackTotal().doubleValue(), IsEqual.equalTo(valorTotalCashback.doubleValue()));
    }

    @And("^o valor de retorno de cada disco deveria:$")
    public void oValorDeRetornoDeCadaDiscoDeveria(Map<String, BigDecimal> itensVendaEsperado) throws Throwable {

        VendaDTO vendaDTO = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), VendaDTO.class);

        Map<String, BigDecimal> itensVendaAtual = new HashMap<>();
        vendaDTO.getItensVenda().forEach(itemVendaDTO -> {
            itensVendaAtual.put(itemVendaDTO.getDisco().getNomeAlbum(), itemVendaDTO.getCashback());
        });

        itensVendaEsperado.forEach((key, bigDecimal) -> {
            assertThat(itensVendaAtual.get(key).doubleValue(), IsEqual.equalTo(bigDecimal.doubleValue()));
        });
    }

    @Given("^a tabela de porcetagem de cashback abaixo:$")
    public void aTabelaDePorcetagemDeCashbackAbaixo(List<PorcetagemCashback> porcetagemCashbacks) {
        porcetagemCashbackRepository.deleteAll();
        porcetagemCashbackRepository.saveAll(porcetagemCashbacks);
    }

}
