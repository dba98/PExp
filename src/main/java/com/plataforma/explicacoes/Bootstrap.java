package com.plataforma.explicacoes;

import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.repositories.IdiomaRepo;
import com.plataforma.explicacoes.repositories.QualificacaoRepo;
import com.plataforma.explicacoes.repositories.UniversidadeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class Bootstrap implements ApplicationListener <ContextRefreshedEvent> {

    @Autowired
    private UniversidadeRepo universidadeRepo;

    @Autowired
    private IdiomaRepo idiomaRepo;

    @Autowired
    private QualificacaoRepo qualificacaoRepo;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent (ContextRefreshedEvent contextRefreshedEvent){

        logger.info("Startup");

        Universidade universidade1= new Universidade( "UFP");

        Faculdade faculdade1 = new Faculdade("Faculdade de Ciencias", universidade1);

        Curso curso1 = new Curso("Engenharia Informática", 1, faculdade1);
        Curso curso2 = new Curso("Ciencias da Comunicação", 2, faculdade1);

        Cadeira cadeira1 = new Cadeira("Engenharia Software", 1, curso1);

        Aluno aluno1 = new Aluno("Ricardo", 35249 , curso1);

        Idioma idioma1= new Idioma("Português");

        Qualificacao qualificacao1= new Qualificacao(" Mestre");

        Professor professor1 = new Professor("Alessandro", 11111, cadeira1, idioma1, qualificacao1);



        this.universidadeRepo.save(universidade1);
        this.idiomaRepo.save(idioma1);
        this.qualificacaoRepo.save(qualificacao1);




    }
}
