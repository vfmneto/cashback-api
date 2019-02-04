package br.com.vfmneto.cashbackapi.cucumber.stepdefs;

import br.com.vfmneto.cashbackapi.CashbackApiApplication;
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

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected ResultActions resultActions;

}
