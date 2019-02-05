package br.com.vfmneto.cashbackapi.util.impl;

import br.com.vfmneto.cashbackapi.util.ObtedorDataUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class ObtedorDataUtilImpl implements ObtedorDataUtil {

    @Override
    public Date obterDataAtual() {
        return new Date();
    }
}
