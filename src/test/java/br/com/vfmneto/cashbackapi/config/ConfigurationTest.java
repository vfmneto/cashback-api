package br.com.vfmneto.cashbackapi.config;

import br.com.vfmneto.cashbackapi.util.ObtedorDataUtil;
import br.com.vfmneto.cashbackapi.util.ObtedorDataUtilStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigurationTest {

    @Bean
    @Primary
    public ObtedorDataUtil obtedorDataUtil() {
        return new ObtedorDataUtilStub();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
