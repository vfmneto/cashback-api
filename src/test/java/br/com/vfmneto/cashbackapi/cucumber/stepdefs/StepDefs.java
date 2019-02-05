package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.CashbackApiApplication;
import br.com.vfmneto.cashbackapi.repository.DiscoRepository;
import br.com.vfmneto.cashbackapi.repository.PorcetagemCashbackRepository;
import br.com.vfmneto.cashbackapi.util.ObtedorDataUtilStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@ContextConfiguration(classes = CashbackApiApplication.class)
public abstract class StepDefs {

    @Autowired protected MockMvc mockMvc;
    @Autowired protected JdbcTemplate jdbcTemplate;
    @Autowired protected DiscoRepository discoRepository;
    @Autowired protected ObtedorDataUtilStub obtedorDataUtil;
    @Autowired protected PorcetagemCashbackRepository porcetagemCashbackRepository;
    @Autowired protected ObjectMapper objectMapper;

    protected ResultActions resultActions;

}
