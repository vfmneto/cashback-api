package br.com.vfmneto.cashbackapi.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

public class PaginaDTO {

    private Integer numeroPagina;
    private Integer quantidadePorPagina;

    public PaginaDTO(int numeroPagina, int quantidadePorPagina) {
        this.numeroPagina = numeroPagina;
        this.quantidadePorPagina = quantidadePorPagina;
    }

    public Integer getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(Integer numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public Integer getQuantidadePorPagina() {
        return quantidadePorPagina;
    }

    public void setQuantidadePorPagina(Integer quantidadePorPagina) {
        this.quantidadePorPagina = quantidadePorPagina;
    }

    public Pageable toPageable() {
        return new PageRequest(numeroPagina, quantidadePorPagina);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginaDTO paginaDTO = (PaginaDTO) o;
        return Objects.equals(numeroPagina, paginaDTO.numeroPagina) &&
                Objects.equals(quantidadePorPagina, paginaDTO.quantidadePorPagina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPagina, quantidadePorPagina);
    }
}
