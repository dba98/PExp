package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.repositories.AtendimentoRepo;
import com.plataforma.explicacoes.repositories.CadeiraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
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



    public Optional<Atendimento> createAtendimento(Map<String, String> jsonAtendimento) {

        Optional<Professor> optionalProfessor = professorService.findById(Long.parseLong(jsonAtendimento.get("idProfessor")));
        Optional<Aluno> optionalAluno = alunoService.findById(Long.parseLong(jsonAtendimento.get("idAluno")));
        Optional<Cadeira> optionalCadeira = cadeiraRepo.findById(Long.parseLong(jsonAtendimento.get("idCadeira")));
        LocalDate date= LocalDate.parse(jsonAtendimento.get("date"));
        LocalTime hinicio= LocalTime.parse(jsonAtendimento.get("hinicio"));
        LocalTime hfim= LocalTime.parse(jsonAtendimento.get("hfim"));

        if(optionalAluno.isEmpty() || optionalProfessor.isEmpty() || optionalCadeira.isEmpty()){
            return Optional.empty();
        }

        if(!checkValidadeHorario(optionalProfessor.get(), DayOfWeek.from(date).getValue(), hinicio, hfim)){
            return Optional.empty();
        }

        if(!checkSobreposicaoAtendiento(optionalProfessor.get(), date, hinicio, hfim)){
            return Optional.empty();
        }

        Atendimento atendimento= new Atendimento(date, hinicio, hfim, optionalProfessor.get(), optionalAluno.get(), optionalCadeira.get());


        optionalAluno.get().addAtendimento(atendimento);


        this.atendimentoRepo.save(atendimento);

        return Optional.of(atendimento);


    }

    public boolean checkSobreposicaoAtendiento (Professor professor,LocalDate date, LocalTime hInicio, LocalTime hFim){
        for(Atendimento atendimento: professor.getAtendimentos()){
            if(atendimento.getDate().isEqual(date)){
                if (hInicio.isBefore(atendimento.getDfim()) && hFim.isAfter(atendimento.getDinicio())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkValidadeHorario (Professor professor,Integer dia, LocalTime hInicio, LocalTime hFim){
        for(Horario horario : professor.getHorarios()){
            if(horario.getDia().getValue() == dia) {
                if ((hInicio.equals(horario.getHInicio()) || hInicio.isAfter(horario.getHInicio()) && (hFim.equals(horario.getHFim()) || hFim.isBefore(horario.getHFim())))){
                    return true;
                }
            }
        }
        return false;
    }


}
