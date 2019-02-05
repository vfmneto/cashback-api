package br.com.vfmneto.cashbackapi.dto;

import br.com.vfmneto.cashbackapi.domain.Genero;

import java.io.Serializable;
import java.math.BigDecimal;

public class DiscoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeAlbum;
    private Genero genero;
    private BigDecimal preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
