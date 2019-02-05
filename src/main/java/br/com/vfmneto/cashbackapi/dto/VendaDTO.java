package br.com.vfmneto.cashbackapi.dto;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class VendaDTO {

    private Long id;
    private LocalDate data;
    private String nomeCliente;
    private BigDecimal cashbackTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public BigDecimal getCashbackTotal() {
        return cashbackTotal;
    }

    public void setCashbackTotal(BigDecimal cashbackTotal) {
        this.cashbackTotal = cashbackTotal;
    }
}
