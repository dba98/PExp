package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.models.builders.UniversidadeBuilder;
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

        Idioma idioma1= new Idioma("Português");
        Qualificacao qualificacao1= new Qualificacao("Mestre",3);
        Qualificacao qualificacao2= new Qualificacao("Doutor",2);
        Universidade universidade1= new UniversidadeBuilder().setName("UFP").build();

        Faculdade faculdade1 = new Faculdade("Faculdade de Ciencias", universidade1);

        universidade1.addFaculdade(faculdade1);

        Curso curso1 = new Curso("Engenharia Informática", 1);
        Curso curso2 = new Curso("Ciencias da Comunicação", 2);

        faculdade1.addCurso(curso1);
        faculdade1.addCurso(curso2);

        Cadeira cadeira1 = new Cadeira("Engenharia Software", 1);
        Cadeira cadeira2 = new Cadeira("Gramatica da Comunicacao", 2);


        Professor professor1 = new Professor("Alessandro Moreira", 11111);
        Professor professor2 = new Professor("Rui Estrada", 11121);
        Professor professor3 = new Professor("Feliz Gouveia", 11145);

        professor1.associateQualificacao(qualificacao1);
        professor1.addIdioma(idioma1);
        professor2.associateQualificacao(qualificacao2);
        professor2.addIdioma(idioma1);
        professor3.associateQualificacao(qualificacao2);
        professor3.addIdioma(idioma1);

        Aluno aluno1 = new Aluno("Ricardo", 35249);
        Aluno aluno2 = new Aluno("Diogo", 35245);
        Aluno aluno3 = new Aluno("Catarina", 35987);

        aluno1.associateCurso(curso1);
        aluno2.associateCurso(curso1);
        aluno3.associateCurso(curso2);
        curso1.addAluno(aluno1);
        curso1.addAluno(aluno2);
        curso1.addCadeira(cadeira1);
        curso1.addCadeira(cadeira2);
        curso1.associateFaculdade(faculdade1);
        curso2.addAluno(aluno3);
        curso2.addCadeira(cadeira2);
        curso2.associateFaculdade(faculdade1);

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