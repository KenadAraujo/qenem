package com.polligonalApps.QEnem.controller;

import com.polligonalApps.QEnem.domain.dto.Banca;
import com.polligonalApps.QEnem.domain.service.BancaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "NF-e", description = "Operações e consultas ao NF-e")
@RestController
@RequestMapping(name = "/banca")
@Slf4j
public class BancaController {

    @Autowired
    private BancaService bancaService;

    @Operation(summary = "Busca todas as bancas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna uma lista paginada",
                    content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) }) })
    @GetMapping
    public ResponseEntity<Page<Banca>> listar(Pageable pageable){
        Page<Banca> bancas = bancaService.listar(pageable);
        return ResponseEntity.ok(bancas);
    }
}
