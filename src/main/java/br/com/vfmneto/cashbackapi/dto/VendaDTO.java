package br.com.vfmneto.cashbackapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class VendaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date data;
    private String nomeCliente;
    private BigDecimal cashbackTotal;
    private List<ItemVendaDTO> itensVenda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

    public List<ItemVendaDTO> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVendaDTO> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
