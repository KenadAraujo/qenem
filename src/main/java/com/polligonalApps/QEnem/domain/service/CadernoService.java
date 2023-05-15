package com.polligonalApps.QEnem.domain.service;

import com.polligonalApps.QEnem.domain.dto.Banca;
import com.polligonalApps.QEnem.domain.dto.Caderno;
import com.polligonalApps.QEnem.models.BancaModel;
import com.polligonalApps.QEnem.models.CadernoModel;
import com.polligonalApps.QEnem.repository.CadernoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CadernoService {


    private CadernoRepository repository;

    public CadernoService(CadernoRepository cadernoRepository){
        this.repository = cadernoRepository;
    }

    public Page<Caderno> listar(Pageable pagina) {
        Page<CadernoModel> cadernos = repository.findAll(pagina);
        List<Caderno> caderno = new ArrayList<>();
        cadernos.forEach(cadernoModel -> caderno.add(cadernoModel.toRecord()));
        return new PageImpl<>(caderno,pagina,cadernos.getTotalElements());
    }
}
