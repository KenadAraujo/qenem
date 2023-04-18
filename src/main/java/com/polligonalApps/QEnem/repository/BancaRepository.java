package com.polligonalApps.QEnem.repository;

import com.polligonalApps.QEnem.models.BancaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancaRepository extends CrudRepository<BancaModel, Long> {

    Page<BancaModel> findAll(PageRequest request);
}
