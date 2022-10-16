package com.github.quarkus;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @GET
    public List<Produto> buscarTodosProdutos() {
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void cadastrarProduto(ProdutoDto produtoDto) {
        Produto produto = ProdutoDto.produtoDtoToProduto(produtoDto);
        produto.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizaProduto(@PathParam("id") Long id, ProdutoDto produtoDto) {
        Optional<Produto> produtoOptional = Produto.findByIdOptional(id);
        produtoOptional.ifPresentOrElse(produto -> {
            produto.setNome(produtoDto.getNome());
            produto.setValor(produtoDto.getValor());
            produto.persist();
        }, () -> {
            throw new NotFoundException("Produto não exite!");
        });
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletaProduto(@PathParam("id") Long id) {
        Produto.findByIdOptional(id).ifPresentOrElse(PanacheEntityBase::delete, () ->{
            throw new NotFoundException("Produto não exite!");
        });
    }
}
