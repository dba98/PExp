package com.plataforma.explicacoes;
import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.models.builders.AlunoBuilder;
import com.plataforma.explicacoes.models.builders.AtendimentoBuilder;
import com.plataforma.explicacoes.models.builders.CursoBuilder;
import com.plataforma.explicacoes.models.builders.ProfessorBuilder;
import com.plataforma.explicacoes.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UniversidadeRepo universidadeRepo;

    @Autowired
    private IdiomaRepo idiomaRepo;

    @Autowired
    private QualificacaoRepo qualificacaoRepo;


    @Autowired
    private AtendimentoRepo atendimentoRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        logger.info("Startup");

        Idioma idioma1 = new Idioma("Portugues");
        Qualificacao qualificacao1 = new Qualificacao("Mestre",3);
        Qualificacao qualificacao2 = new Qualificacao("Doutor",2);
        Qualificacao qualificacao3 = new Qualificacao("Licenciado",1);
        Universidade universidade = new Universidade();

        Faculdade faculdade1 = new Faculdade("Faculdade de Ciencias");
        universidade.addFaculdade(faculdade1);



        Curso curso1= new CursoBuilder().setNome("Engenharia Informatica").setCodigo(1).build();
        Curso curso2= new CursoBuilder().setNome("Ciencias da Comunicacao").setCodigo(2).build();
        Curso curso3= new CursoBuilder().setNome("CP").setCodigo(3).build();

        faculdade1.addCurso(curso1);
        faculdade1.addCurso(curso2);
        faculdade1.addCurso(curso3);

        Cadeira cadeira1 = new Cadeira("Engenharia Software", 1);
        Cadeira cadeira2 = new Cadeira("Gramatica da Comunicacao", 2);

        Professor professor1 = new ProfessorBuilder().setName("Alessandro Moreira").setNum(11111).
                setGrau(qualificacao1).addIdioma(idioma1).addCadeira(cadeira1).addHorario(new Horario(DayOfWeek.THURSDAY,LocalTime.of(10,0), LocalTime.of(12,0))).build();
        Professor professor2= new ProfessorBuilder().setName("Rui Estrada").addHorario(new Horario(DayOfWeek.MONDAY,LocalTime.of(10,0), LocalTime.of(12,0))).setNum(11121).
                setGrau(qualificacao2).addIdioma(idioma1).addCadeira(cadeira2).build();
        Professor professor3= new ProfessorBuilder().setName("Feliz Gouveia").setNum(11145).
                setGrau(qualificacao2).addHorario(new Horario(DayOfWeek.MONDAY,LocalTime.of(10,0), LocalTime.of(12,0))).addIdioma(idioma1).addCadeira(cadeira1).build();

        idioma1.addProfessor(professor1);
        idioma1.addProfessor(professor2);
        idioma1.addProfessor(professor3);
        qualificacao1.addProfessor(professor1);
        qualificacao2.addProfessor(professor2);
        qualificacao2.addProfessor(professor3);
        cadeira1.addProfessor(professor1);
        cadeira1.addProfessor(professor3);
        cadeira2.addProfessor(professor2);
        cadeira1.associateCurso(curso1);
        cadeira2.associateCurso(curso2);

        Aluno aluno1= new AlunoBuilder().setName("Ricardo").setNum(35249).setCurso(curso1).build();
        Aluno aluno2= new AlunoBuilder().setName("Diogo").setNum(35245).setCurso(curso1).build();
        Aluno aluno3= new AlunoBuilder().setName("Catarina").setNum(35367).setCurso(curso1).build();

        curso1.addAluno(aluno1);
        curso1.addAluno(aluno2);
        curso1.addCadeira(cadeira1);
        curso1.addCadeira(cadeira2);
        curso1.associateFaculdade(faculdade1);
        curso2.addAluno(aluno3);
        curso2.addCadeira(cadeira2);
        curso2.associateFaculdade(faculdade1);

        Atendimento atendimento1= new AtendimentoBuilder().setDate(LocalDate.now()).setDinicio(LocalTime.of(10,00)).
                setAluno(aluno1).setProfessor(professor1).
                setCadeira(cadeira1).build();
        Atendimento atendimento2= new AtendimentoBuilder().setDate(LocalDate.now()).setDinicio(LocalTime.of(12,00)).
                setAluno(aluno2).setProfessor(professor2).
                setCadeira(cadeira2).build();



        this.universidadeRepo.save(universidade);
        this.idiomaRepo.save(idioma1);
        this.qualificacaoRepo.save(qualificacao1);
        this.qualificacaoRepo.save(qualificacao2);
       /* this.professorRepo.save(professor1);
        this.professorRepo.save(professor2);
        this.professorRepo.save(professor3);*/
       this.atendimentoRepo.save(atendimento1);
       this.atendimentoRepo.save(atendimento2);

    }
}
