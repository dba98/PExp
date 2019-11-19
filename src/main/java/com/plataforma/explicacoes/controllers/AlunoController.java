package com.plataforma.explicacoes.controllers;

import com.plataforma.explicacoes.repositories.AlunoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepo alunoRepo;

    
}
