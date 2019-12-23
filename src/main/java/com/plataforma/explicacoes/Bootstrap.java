package com.plataforma.explicacoes;

import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.models.builders.AtendimentoBuilder;
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
    private ProfessorRepo professorRepo;

    @Autowired
    private AtendimentoRepo atendimentoRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        logger.info("Startup");

        Idioma idioma1 = new Idioma("Português");
        Qualificacao qualificacao1 = new Qualificacao("Mestre",3);
        Qualificacao qualificacao2 = new Qualificacao("Doutor",2);
        Qualificacao qualificacao3 = new Qualificacao("Licenciado",1);
        Universidade universidade1 = new Universidade("UFP");

        Faculdade faculdade1 = new Faculdade("Faculdade de Ciencias", universidade1);

        universidade1.addFaculdade(faculdade1);

        Curso curso1 = new Curso("Engenharia Informática", 1);
        Curso curso2 = new Curso("Ciencias da Comunicação", 2);

        faculdade1.addCurso(curso1);
        faculdade1.addCurso(curso2);

        Cadeira cadeira1 = new Cadeira("Engenharia Software", 1);
        Cadeira cadeira2 = new Cadeira("Gramatica da Comunicacao", 2);


        //Professor professor1 = new Professor("Alessandro Moreira", 11111);
        //Professor professor2 = new Professor("Rui Estrada", 11121);
        //Professor professor3 = new Professor("Feliz Gouveia", 11145);

        Professor professor1 = new ProfessorBuilder().setName("Alessandro Moreira").setNum(11111).
                setGrau(qualificacao1).addIdioma(idioma1).addCadeira(cadeira1).build();
        Professor professor2= new ProfessorBuilder().setName("Rui Estrada").setNum(11121).
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

        //professor1.associateQualificacao(qualificacao1);
        //professor1.addCadeira(cadeira1);
        //professor1.addIdioma(idioma1);
        //professor2.associateQualificacao(qualificacao2);
        //professor2.addCadeira(cadeira2);
        //professor2.addIdioma(idioma1);
        //professor3.associateQualificacao(qualificacao2);
        //professor3.addCadeira(cadeira1);
        //professor3.addIdioma(idioma1);

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

        Atendimento atendimento1= new AtendimentoBuilder().setDate(LocalDate.now()).setDinicio(LocalTime.of(10,00)).
                setAluno(aluno1).setProfessor(professor1).
                setCadeira(cadeira1).build();
        Atendimento atendimento2= new AtendimentoBuilder().setDate(LocalDate.now()).setDinicio(LocalTime.of(12,00)).
                setAluno(aluno2).setProfessor(professor2).
                setCadeira(cadeira2).build();



        this.universidadeRepo.save(universidade1);
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
