package org.example.provap1fullstack.controller;

import org.example.provap1fullstack.dto.CategoriaResponse;
import org.example.provap1fullstack.dto.ProdutoResponse;
import org.example.provap1fullstack.service.CategoriaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listar(@RequestParam(required = false) String nome) {
        List<CategoriaResponse> categorias = categoriaService.listar(nome);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}/produto")
    public ResponseEntity<List<ProdutoResponse>> listarProdutosPorCategoria(@PathVariable Long id) {
        List<ProdutoResponse> produtos = categoriaService.listarProdutosPorCategoria(id);
        return ResponseEntity.ok(produtos);
    }
}