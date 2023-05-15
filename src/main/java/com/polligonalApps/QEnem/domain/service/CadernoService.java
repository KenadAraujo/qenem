package com.polligonalApps.QEnem.domain.service;

import com.polligonalApps.QEnem.domain.dto.Banca;
import com.polligonalApps.QEnem.domain.dto.Caderno;
import com.polligonalApps.QEnem.domain.exceptions.BusinessException;
import com.polligonalApps.QEnem.models.BancaModel;
import com.polligonalApps.QEnem.models.CadernoModel;
import com.polligonalApps.QEnem.repository.CadernoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CadernoService {

    @Autowired
    private BancaService bancaService;

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

    public Caderno criar(Caderno caderno) throws BusinessException {
        if(caderno.banca()!=null){
            Banca banca = this.bancaService.buscar(caderno.banca());
            if(banca==null){
                throw new BusinessException("A banca ainda não está cadastrada!");
            }
            CadernoModel cadernoModel = new CadernoModel(caderno);
            cadernoModel.setBanca(new BancaModel(banca));
            cadernoModel = this.repository.save(cadernoModel);
            return cadernoModel.toRecord();
        }else{
            throw new BusinessException("O nome da banca não pode ser nulo.");
        }
    }
}
