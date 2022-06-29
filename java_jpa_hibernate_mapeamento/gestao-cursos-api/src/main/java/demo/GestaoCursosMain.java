package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GestaoCursosMain {

    public static void main(String[] args) {
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        Endereco endereco1 = new Endereco();
        endereco1.setLogradouro("Rua");
        endereco1.setEndereco("Rua Alferes Domingos");
        endereco1.setNumero("80");
        endereco1.setBairro("Centro");
        endereco1.setCidade("Campinas");
        endereco1.setEstado("SP");
        endereco1.setCep(removeCaracteresEspeciais("13015-031"));

        Endereco endereco2 = new Endereco();
        endereco2.setLogradouro("Rua");
        endereco2.setEndereco("Rua Abade Bernon");
        endereco2.setNumero("1022");
        endereco2.setBairro("Jardim do Lago");
        endereco2.setCidade("São Paulo");
        endereco2.setEstado("SP");
        endereco2.setCep(removeCaracteresEspeciais("05397-120"));

        Telefone telefone1 = new Telefone();
        telefone1.setDdd(removeCaracteresEspeciais("11"));
        telefone1.setNumero(removeCaracteresEspeciais("99358-6527"));

        Telefone telefone2 = new Telefone();
        telefone2.setDdd(removeCaracteresEspeciais("11"));
        telefone2.setNumero(removeCaracteresEspeciais("99387-0896"));

        Aluno aluno1 = new Aluno();
        aluno1.setNomeCompleto("Raquel Teixeira");
        aluno1.setMatricula("373373450175");
        aluno1.setNascimento(parseDate("28/05/2000"));
        aluno1.setEmail("raquel.teixeira@geradornv.com.br");
        aluno1.setEnderecos(List.of(endereco1));
        aluno1.setTelefones(List.of(telefone1));

        Aluno aluno2 = new Aluno();
        aluno2.setNomeCompleto("André Silva Soriano");
        aluno2.setMatricula("034215280108");
        aluno2.setNascimento(parseDate("15/02/1998"));
        aluno2.setEmail("andre.s@geradornv.com.br");
        aluno2.setEnderecos(List.of(endereco2));
        aluno2.setTelefones(List.of(telefone2));

        // 1) Criando aluno
        alunoModel.create(aluno1);
        alunoModel.create(aluno2);

        // 2) Buscando todos os alunos na base de dados
        List<Aluno> alunos = alunoModel.findAll();
        System.out.println("Qtde de alunos encontrados : " + alunos.size());

        // 3) - Atualizando aluno
        aluno1.setNomeCompleto("Bruna Carvalho Galego");
        alunoModel.update(aluno1);

        // 4) Buscando aluno por Id
        alunoModel.findById(1L);

        // 5) - Deletando aluno
        alunoModel.delete(aluno1);

        Professor professor1 = new Professor();
        professor1.setNomeCompleto("Vitória das Dores");
        professor1.setMatricula("664162790175");
        professor1.setEmail("vitoria.dores@geradornv.com.br");

        MaterialCurso materialCurso1 = new MaterialCurso();
        materialCurso1.setUrl("https://www.youtube.com/watch?v=grEKMHGYyns");

        Curso curso1 = new Curso();
        curso1.setNome("Bootcamp Java");
        curso1.setSigla("BootJava");
        curso1.setProfessor(professor1);
        curso1.setMaterialCurso(materialCurso1);
        curso1.setAlunos(List.of(aluno1));

        Professor professor2 = new Professor();
        professor2.setNomeCompleto("Vinicius Tomberg Atila");
        professor2.setMatricula("608865360124");
        professor2.setEmail("vini.tomberg@geradornv.com.br");

        MaterialCurso materialCurso2 = new MaterialCurso();
        materialCurso2.setUrl("https://www.youtube.com/watch?v=F9UC9DY-vIU");

        Curso curso2 = new Curso();
        curso2.setNome("Bootcamp Kotlin");
        curso2.setSigla("BootKotlin");
        curso2.setProfessor(professor2);
        curso2.setMaterialCurso(materialCurso2);
        curso2.setAlunos(List.of(aluno2));

        // 1) Criando curso
        cursoModel.create(curso1);
        cursoModel.create(curso2);

        // 2) Buscando todos os cursos na base de dados
        List<Curso> cursos = cursoModel.findAll();
        System.out.println("Qtde de cursos encontrados : " + cursos.size());

        // 3) - Atualizando curso
        curso1.setNome("Bootcamp Java Completo");
        cursoModel.update(curso1);

        // 4) Buscando curso por Id
        cursoModel.findById(1L);

        // 5) - Deletando curso
        cursoModel.delete(curso1);
    }

    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String removeCaracteresEspeciais(String doc) {
        if (doc.contains("\\D")) {
            doc = doc.replace("\\D", "");
        }
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains(",")) {
            doc = doc.replace(",", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        if (doc.contains("(")) {
            doc = doc.replace("(", "");
        }
        if (doc.contains(")")) {
            doc = doc.replace(")", "");
        }
        if (doc.contains(" ")) {
            doc = doc.replace(" ", "");
        }
        return doc;
    }
}