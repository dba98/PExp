package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.exceptions.ProfessorDoesNotExistException;
import com.plataforma.explicacoes.models.Curso;
import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.repositories.ProfessorRepo;
import com.plataforma.explicacoes.services.filters.professor.FilterProfessorObject;
import com.plataforma.explicacoes.services.filters.professor.FilterProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepo professorRepo;
    @Autowired
    private FilterProfessorService filterService;
    @Autowired
    private CursoService cursoService;

    public ProfessorService(ProfessorRepo professorRepo, FilterProfessorService filterProfessorService) {
        this.professorRepo = professorRepo;
        this.filterService = filterProfessorService;
    }

    public Set<Professor> findAll() {
        Set<Professor> professores = new HashSet<>();
        for (Professor p1 : this.professorRepo.findAll()) {
            professores.add(p1);
        }
        return professores;
    }

    public Optional<Professor> findById(Long id) {
        return this.professorRepo.findById(id);
    }

    public Optional<Professor> createProfessor(Professor professor) {
        Optional<Professor> optionalProfessor = this.professorRepo.findByNome(professor.getNome());
        if (optionalProfessor.isEmpty()) {
            return Optional.of(this.professorRepo.save(professor));
        }
        return Optional.empty();
    }

    public Optional<Professor> findByNome(String name) {
        return this.professorRepo.findByNome(name);
    }

    public Optional<Professor> createHorario(Professor professor) throws ProfessorDoesNotExistException {
        Optional<Professor> optionalProfessor = professorRepo.findByNum(professor.getNum());
        if (optionalProfessor.isEmpty()) {
            throw new ProfessorDoesNotExistException("Professor inexistente");
        }
        //Professor auxprofessor = optionalProfessor.get();
        //optionalProfessor.get().setHorarios(professor.getHorarios());
        checkSobreposicao(optionalProfessor.get(), professor);
        professorRepo.save(optionalProfessor.get());
        return optionalProfessor;
    }

    public Set<Professor> filterProfessors(Map<String, String> searchParams) {
        FilterProfessorObject filterProfessorObject = new FilterProfessorObject(searchParams);
        Set<Professor> professors = this.findAll();
        System.out.println(professors + "\n\n" + filterProfessorObject);
        Set<Professor> finalfilter = filterService.filter(professors, filterProfessorObject);

        return finalfilter;
    }

    public Optional<Professor> associateCurso(Professor professor, String curso) {
        Optional<Professor> optionalProfessor = professorRepo.findByNome(professor.getNome());
        Optional<Curso> optionalCurso = cursoService.findByNome(curso);

        if (optionalCurso.isEmpty() || optionalProfessor.isEmpty()) {
            return Optional.empty();
        }

        optionalProfessor.get().associateCurso(optionalCurso.get());
        professorRepo.save(optionalProfessor.get());
        return optionalProfessor;

    }

    /*public boolean checkSobreposicao (Professor professor, Professor auxprofessor){

    }

*/
    public boolean checkSobreposicao(Professor professor, Professor newHorariosProfessor) {
        ArrayList<Horario> remove = new ArrayList<>();
        ArrayList<Horario> add = new ArrayList<>();
        if(professor.getHorarios().isEmpty()){
            professor.setHorarios(newHorariosProfessor.getHorarios());
            return true;
        }
        for (Horario horario : professor.getHorarios()) {
            for (Horario newHorario : newHorariosProfessor.getHorarios()) {
                if (horario.getDia() == newHorario.getDia()) {
                    if (newHorario.getInicio().isBefore(horario.getFim()) && newHorario.getFim().isAfter(horario.getInicio())) {
                        remove.add(horario);
                        add.add(new Horario(professor, newHorario.getDia(), newHorario.getInicio(), newHorario.getFim()));
                        continue;
                    }
                }
                add.add(new Horario(professor, newHorario.getDia(), newHorario.getInicio(), newHorario.getFim()));
            }
        }
        for (Horario horario : remove) professor.getHorarios().remove(horario);
        for (Horario horario : add) professor.getHorarios().add(horario);
        return true;
    }
}
