package com.polligonalApps.QEnem.domain.service;

import com.polligonalApps.QEnem.domain.dto.Banca;
import com.polligonalApps.QEnem.domain.exceptions.BusinessException;
import com.polligonalApps.QEnem.models.BancaModel;
import com.polligonalApps.QEnem.repository.BancaRepository;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BancaService {

    private BancaRepository repository;

    public BancaService(BancaRepository repository){
        this.repository = repository;
    }

    public Banca criar(Banca banca) throws BusinessException {
        if(banca.nome()!=null){
            BancaModel bancaModel = new BancaModel(banca);
            bancaModel = this.repository.save(bancaModel);
            return bancaModel.toRecord();
        }else{
            throw new BusinessException("O nome da banca não pode ser nulo.");
        }
    }

    public Page<Banca> listar(Pageable pagina){
        Page<BancaModel> bancas = repository.findAll(pagina);
        List<Banca> banca = new ArrayList<>();
        bancas.forEach(bancaModel -> banca.add(bancaModel.toRecord()));
        return new PageImpl<>(banca,pagina,bancas.getTotalElements());
    }

    public Banca deletar(Banca banca) throws BusinessException {
        if(banca==null || banca.id()==null)
            throw new BusinessException("Não é possível deletar uma banca nula ou sem identificador");
        Optional<BancaModel> model = repository.findById(banca.id());
        if(model.isPresent()){
            repository.delete(model.get());
            return banca;
        }else{
            throw new BusinessException("A banca não existe!");
        }
    }
}
