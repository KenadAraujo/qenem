package com.polligonalApps.QEnem.controller;

import com.polligonalApps.QEnem.domain.dto.Banca;
import com.polligonalApps.QEnem.domain.dto.Caderno;
import com.polligonalApps.QEnem.domain.service.CadernoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Caderno", description = "Operações e consultas ao Caderno de questões")
@Slf4j
@RestController
@RequestMapping(path = "/caderno")
public class CadernoController {

    private CadernoService cadernoService;

    @Operation(summary = "Busca todos os cadernos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna uma lista paginada",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) }) })
    @GetMapping
    public ResponseEntity<Page<Caderno>> listar(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Caderno> cadernos = cadernoService.listar(pageable);
        return ResponseEntity.ok(cadernos);
    }
}
