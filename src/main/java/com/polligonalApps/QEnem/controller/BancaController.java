package com.polligonalApps.QEnem.controller;

import com.polligonalApps.QEnem.domain.dto.Banca;
import com.polligonalApps.QEnem.domain.exceptions.BusinessException;
import com.polligonalApps.QEnem.domain.service.BancaService;
import com.polligonalApps.QEnem.models.BancaModel;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Banca", description = "Operações e consultas a Banca de questões")
@Slf4j
@RestController
@RequestMapping(path = "/banca")
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
    public ResponseEntity<Page<Banca>> listar(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Banca> bancas = bancaService.listar(pageable);
        return ResponseEntity.ok(bancas);
    }
    @Operation(summary = "Cadastra uma nova banca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna a banca cadastrada",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Banca.class)) }) })
    @PostMapping
    public ResponseEntity<Banca> criar(Banca banca) throws BusinessException {
        BancaModel bancaModel = new BancaModel(banca);
        banca =  bancaService.criar(banca);
        if(banca!=null){
            return ResponseEntity.ok(bancaModel.toRecord());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
