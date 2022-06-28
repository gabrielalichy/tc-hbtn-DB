package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProdutoModel {

    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, p.getId());
            em.merge(produto);
            em.getTransaction().commit();
            System.out.println("Produto " + p.getId() + " atualizado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar o produto " + p.getId() + " !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, p.getId());
            em.remove(produto);
            em.getTransaction().commit();
            System.out.println("Produto " + produto.getId() + " deletado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar o produto " + p.getId() + " !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            Produto produto = em.find(Produto.class, p.getId());
            System.out.println("Produto " + produto.getId() + " encontrado com sucesso !!!");
            return produto;
        } catch (Exception e) {
            System.err.println("Erro ao buscar o produto " + p.getId() + " !!!" + e.getMessage());
            return null;
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public List<Produto> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            List<Produto> produtos = em.createNativeQuery("SELECT * FROM tb_produto", Produto.class).getResultList();
            System.out.println("Produtos encontrados com sucesso !!!");
            return produtos;
        } catch (Exception e) {
            System.err.println("Erro ao buscar produtos !!!" + e.getMessage());
            return null;
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

}