package br.com.vfmneto.cashbackapi.util;

import java.time.LocalDate;

public class ObtedorDataUtilStub implements ObtedorDataUtil {

    private LocalDate dataAtual;

    public void setDataAtual(LocalDate dataAtual) {
        this.dataAtual = dataAtual;
    }

    @Override
    public LocalDate obterDataAtual() {
        return dataAtual;
    }
}
