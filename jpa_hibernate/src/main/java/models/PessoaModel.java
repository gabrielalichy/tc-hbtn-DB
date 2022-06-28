package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar o pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Pessoa pessoa = em.find(Pessoa.class, p.getId());
            em.merge(pessoa);
            em.getTransaction().commit();
            System.out.println("Pessoa " + p.getId() + " atualizada com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar o pessoa " + p.getId() + " !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Pessoa pessoa = em.find(Pessoa.class, p.getId());
            em.remove(pessoa);
            em.getTransaction().commit();
            System.out.println("Pessoa " + pessoa.getId() + " deletada com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar a pessoa " + p.getId() + " !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            Pessoa pessoa = em.find(Pessoa.class, p.getId());
            System.out.println("Pessoa " + pessoa.getId() + " encontrada com sucesso !!!");
            return pessoa;
        } catch (Exception e) {
            System.err.println("Erro ao buscar a pessoa " + p.getId() + " !!!" + e.getMessage());
            return null;
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            List<Pessoa> pessoas = em.createNativeQuery("SELECT * FROM tb_pessoa", Pessoa.class).getResultList();
            System.out.println("Pessoas encontradas com sucesso !!!");
            return pessoas;
        } catch (Exception e) {
            System.err.println("Erro ao buscar pessoas !!!" + e.getMessage());
            return null;
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

}