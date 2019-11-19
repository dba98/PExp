package com.plataforma.explicacoes.controllers;

import com.plataforma.explicacoes.repositories.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepo professorRepo;

}
