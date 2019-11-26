package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class UniversidadeRepoTest {

    @Autowired
    private UniversidadeRepo universidadeRepo;

    @Autowired
    private IdiomaRepo idiomaRepo;

    @Autowired
    private CadeiraRepo cadeiraRepo;

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private ProfessorRepo professorRepo;

    @Autowired
    private AlunoRepo alunoRepo;

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    @Autowired
    private QualificacaoRepo qualificacaoRepo;

    @Test
    public void test() {

        Universidade universidade1 = new Universidade("UFP");

        Faculdade faculdade1 = new Faculdade("Faculdade de Ciencias", universidade1);

        Curso curso1=new Curso("Engenharia Informática");
        faculdade1.addCurso(curso1);

        Curso curso2=new Curso("Ciências da Comunicação");
        faculdade1.addCurso(curso2);

        //  Curso curso1 = new Curso("Engenharia Informática", 1, faculdade1);
//        Curso curso2 = new Curso("Ciências da Comunicação", 2, faculdade1);

        Cadeira cadeira1 = new Cadeira("Engenharia Software", 1, curso1);

        Aluno aluno1 = new Aluno("Ricardo", 35249, curso1);

        Idioma idioma1 = new Idioma("Português");

        Qualificacao qualificacao1 = new Qualificacao("Mestre");

        Professor professor1 = new Professor("Alessandro", 11111, cadeira1, idioma1, qualificacao1);

        this.universidadeRepo.save(universidade1);
        this.idiomaRepo.save(idioma1);
        this.qualificacaoRepo.save(qualificacao1);

        assertEquals(1, this.universidadeRepo.count());
        assertEquals(1, this.faculdadeRepo.count());
        assertEquals(2, this.cursoRepo.count());
        assertEquals(1, this.cadeiraRepo.count());
        assertEquals(1, this.alunoRepo.count());
        assertEquals(1, this.idiomaRepo.count());
        assertEquals(1, this.qualificacaoRepo.count());
        assertEquals(1, this.professorRepo.count());
    }
}