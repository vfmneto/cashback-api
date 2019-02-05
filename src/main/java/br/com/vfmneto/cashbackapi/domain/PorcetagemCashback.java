package br.com.vfmneto.cashbackapi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "porcetagem_cashback")
public class PorcetagemCashback implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana")
    private DiaSemana diaSemana;

    @Column(name = "porcetagem", precision = 10, scale = 2)
    private BigDecimal porcetagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public BigDecimal getPorcetagem() {
        return porcetagem;
    }

    public void setPorcetagem(BigDecimal porcetagem) {
        this.porcetagem = porcetagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PorcetagemCashback disco = (PorcetagemCashback) o;
        return Objects.equals(id, disco.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
