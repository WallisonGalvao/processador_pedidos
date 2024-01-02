package domain.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    private String produto;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;
    private Integer quantidade;

    private Double valor;

    public Item(Long idItem, String produto, Integer quantidade, Double valor) {
        this.idItem = idItem;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Long getIdItem() {
        return idItem;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
