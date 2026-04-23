package com.sistema.loginEcadastro.controller;

import com.sistema.loginEcadastro.entity.Usuario;
import com.sistema.loginEcadastro.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @GetMapping
    public List<Usuario> listar(){
        return adminService.listar();
    }

    @GetMapping("/dashboard")
    public String adminOnly(){
        return "Acesso permitido apenas para admin";
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        adminService.deletarPorId(id);
    }

}
