package br.com.vfmneto.cashbackapi.util;

import java.time.LocalDate;
import java.util.Date;

public class ObtedorDataUtilStub implements ObtedorDataUtil {

    private Date dataAtual;

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    @Override
    public Date obterDataAtual() {
        return dataAtual;
    }
}
