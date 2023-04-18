package com.polligonalApps.QEnem.domain.service;

import com.polligonalApps.QEnem.domain.dto.Banca;
import com.polligonalApps.QEnem.domain.exceptions.BusinessException;
import com.polligonalApps.QEnem.models.BancaModel;
import com.polligonalApps.QEnem.repository.BancaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BancaService {

    private BancaRepository repository;

    public BancaService(BancaRepository repository){
        this.repository = repository;
    }

    public boolean criar(Banca banca) throws BusinessException {
        if(banca.nome()!=null){
            BancaModel bancaModel = new BancaModel(banca);
            this.repository.save(bancaModel);
            return true;
        }
        throw new BusinessException("O nome da banca não pode ser nulo.");
    }

    public Page<Banca> listar(){
        PageRequest request = PageRequest.of(0,10);
        Page<BancaModel> bancas = repository.findAll(request);
        List<Banca> banca = new ArrayList<>();
        bancas.forEach(bancaModel -> {
            banca.add(bancaModel.toRecord());
        });
        return new PageImpl<>(banca,request,bancas.getTotalElements());
    }
}
