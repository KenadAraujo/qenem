package com.polligonalApps.QEnem.controller;

import com.polligonalApps.QEnem.domain.dto.Banca;
import com.polligonalApps.QEnem.domain.dto.Caderno;
import com.polligonalApps.QEnem.domain.exceptions.BusinessException;
import com.polligonalApps.QEnem.domain.service.CadernoService;
import com.polligonalApps.QEnem.models.BancaModel;
import com.polligonalApps.QEnem.models.CadernoModel;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "Caderno", description = "Operações e consultas ao Caderno de questões")
@Slf4j
@RestController
@RequestMapping(path = "/caderno")
public class CadernoController {

    @Autowired
    private CadernoService cadernoService;

    @Operation(summary = "Busca todos os cadernos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna uma lista paginada",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) }) })
    @GetMapping
    public ResponseEntity<Page<Caderno>> listar(@PageableDefault(page = 0, size = 10, sort = "ano", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Caderno> cadernos = cadernoService.listar(pageable);
        return ResponseEntity.ok(cadernos);
    }

    @Operation(summary = "Cria um novo caderno de questões")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna o caderno de questões cadastrada",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Caderno.class)) }) })
    @PostMapping
    public ResponseEntity<Caderno> criar(Caderno caderno) throws BusinessException {
        CadernoModel cadernoModel = new CadernoModel(caderno);
        caderno =  cadernoService.criar(caderno);
        if(caderno!=null){
            return ResponseEntity.ok(cadernoModel.toRecord());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @Operation(summary = "Deleta um caderno e todas as questões relacionadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna o caderno deletado",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Banca.class)) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Caderno> deletar(@PathVariable("id")Long id) throws BusinessException {
        Caderno caderno = new Caderno(id,0,null);
        caderno =  cadernoService.deletar(caderno);
        if(caderno!=null){
            return ResponseEntity.ok(caderno);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
