package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.repositories.AtendimentoRepo;
import com.plataforma.explicacoes.repositories.CadeiraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepo atendimentoRepo;

    @Autowired
    private CadeiraRepo cadeiraRepo;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;



    public Optional<Atendimento> createAtendimento(Atendimento jsonAtendimento) {

        Optional<Professor> optionalProfessor = professorService.findById(jsonAtendimento.getProfessor().getId());
        Optional<Aluno> optionalAluno = alunoService.findById(jsonAtendimento.getAluno().getId());
        Optional<Cadeira> optionalCadeira = cadeiraRepo.findById(jsonAtendimento.getCadeira().getId());
        LocalDate date= jsonAtendimento.getDate();
        LocalTime hinicio= jsonAtendimento.getDinicio();

        if(optionalAluno.isEmpty() || optionalProfessor.isEmpty() || optionalCadeira.isEmpty()){
            return Optional.empty();
        }

        if(!checkValidadeHorario(optionalProfessor.get(), DayOfWeek.from(date).getValue(), hinicio)){
            return Optional.empty();
        }

        if(!checkSobreposicaoAtendiento(optionalProfessor.get(), date, hinicio)){
            return Optional.empty();
        }

        Atendimento atendimento= new Atendimento(date, hinicio, optionalProfessor.get(), optionalAluno.get(), optionalCadeira.get());


        optionalAluno.get().addAtendimento(atendimento);


        this.atendimentoRepo.save(atendimento);

        return Optional.of(atendimento);


    }

    private boolean checkSobreposicaoAtendiento (Professor professor,LocalDate date, LocalTime hInicio){
        LocalTime hFim = hInicio.plusHours(1);
        for(Atendimento atendimento: professor.getAtendimentos()){
            if(atendimento.getDate().isEqual(date)){
                if (hInicio.isBefore(atendimento.getDinicio().plusHours(1)) && hFim.isAfter(atendimento.getDinicio())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkValidadeHorario (Professor professor,Integer dia, LocalTime hInicio){
        LocalTime hFim = hInicio.plusHours(1);
        for(Horario horario : professor.getHorarios()){
            if(horario.getDia().getValue() == dia) {
                if ((hInicio.equals(horario.getInicio()) || hInicio.isAfter(horario.getInicio()) && (hFim.equals(horario.getFim()) || hFim.isBefore(horario.getFim())))){
                    return true;
                }
            }
        }
        return false;
    }


}
