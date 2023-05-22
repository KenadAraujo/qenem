package com.polligonalApps.QEnem.domain.service;

import com.polligonalApps.QEnem.domain.dto.Caderno;
import com.polligonalApps.QEnem.domain.exceptions.BusinessException;
import com.polligonalApps.QEnem.models.CadernoModel;
import com.polligonalApps.QEnem.repository.QuestaoRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestaoService {

    private QuestaoRepository repository;

    public QuestaoService(QuestaoRepository repository){
        this.repository = repository;
    }

    public void deletar(Caderno caderno) throws BusinessException {
        if(caderno.id()!=null){
            this.repository.deleteByCadernos(new CadernoModel(caderno));
        }else{
            throw new BusinessException("O id do caderno não pode ser nulo");
        }
    }
}
