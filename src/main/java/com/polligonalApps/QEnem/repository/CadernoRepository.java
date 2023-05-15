package com.polligonalApps.QEnem.repository;


import com.polligonalApps.QEnem.models.BancaModel;
import com.polligonalApps.QEnem.models.CadernoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadernoRepository extends CrudRepository<CadernoModel, Long> {

    Page<CadernoModel> findAll(Pageable pagina);
}
