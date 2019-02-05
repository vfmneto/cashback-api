package br.com.vfmneto.cashbackapi.util.impl;

import br.com.vfmneto.cashbackapi.util.ObtedorDataUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ObtedorDataUtilImpl implements ObtedorDataUtil {

    @Override
    public LocalDate obterDataAtual() {
        return LocalDate.now();
    }
}
