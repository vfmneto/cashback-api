package br.com.vfmneto.cashbackapi.util.impl;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ObtedorDataUtilImplTest {

    private ObtedorDataUtilImpl obtedorDataUtil;

    @Before
    public void inicializar() {
        obtedorDataUtil = new ObtedorDataUtilImpl();
    }

    @Test
    public void deveriaRetornarDataAtual() {
        assertThat(obtedorDataUtil.obterDataAtual(), equalTo(now()));
    }

}