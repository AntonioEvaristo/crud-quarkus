package com.github.quarkus;

import java.math.BigDecimal;

public class ProdutoDto {
    public String nome;
    public BigDecimal valor;

    public static Produto produtoDtoToProduto(ProdutoDto produtoDto){
        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setValor(produtoDto.getValor());
        return produto;
    }

    public String getNome() {
        return nome;
    }
    public BigDecimal getValor() {
        return valor;
    }
}
