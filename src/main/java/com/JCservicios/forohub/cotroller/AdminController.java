package com.JCservicios.forohub.cotroller;

import com.JCservicios.forohub.domain.usuario.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/usuarios/{id}/roles/admin")
    public ResponseEntity asignarAdmin(@PathVariable Long id) {
        adminService.asignarRolAdmin(id);
        return ResponseEntity.ok().build();
    }
}
