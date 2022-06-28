package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();
        PessoaModel pessoaModel = new PessoaModel();

        Produto produto1 = new Produto();
        produto1.setNome("TV");
        produto1.setPreco(BigDecimal.valueOf(300.0));
        produto1.setQuantidade(100);
        produto1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(produto1);

        // 2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        // 3) - Atualizando um produto
        produto1.setNome("Playstation 5");
        produto1.setPreco(BigDecimal.valueOf(4000.0));
        produto1.setQuantidade(20);
        produto1.setStatus(false);
        produtoModel.update(produto1);

        // 4) Buscando produto por Id
        produtoModel.findById(produto1);

        // 5) - Deletando um produto
        produtoModel.delete(produto1);

        produtoModel.findById(produto1);

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Nathan Marcelo Ribeiro");
        pessoa1.setEmail("nathan_ribeiro@gerador.com.br");
        pessoa1.setIdade(27);
        pessoa1.setCpf("713.813.261-10");
        pessoa1.setDataDeNascimento(LocalDate.now());

        // 1) Criando uma pessoa
        pessoaModel.create(pessoa1);

        // 2) Buscando todos as pessoas na base de dados
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas : " + pessoas.size());

        // 3) - Atualizando uma pessoa
        pessoa1.setNome("Eloá Bárbara Luiza Moura");
        pessoa1.setEmail("eloa_barbara_moura@gerador.com.br");
        pessoa1.setIdade(27);
        pessoa1.setCpf("542.900.812-58");
        pessoa1.setDataDeNascimento(LocalDate.now());
        pessoaModel.update(pessoa1);

        // 4) Buscando pessoa por Id
        pessoaModel.findById(pessoa1);

        // 5) - Deletando um produto
        pessoaModel.delete(pessoa1);

        pessoaModel.findById(pessoa1);
    }
}

