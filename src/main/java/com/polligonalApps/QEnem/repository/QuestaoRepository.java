package com.polligonalApps.QEnem.repository;

import com.polligonalApps.QEnem.models.CadernoModel;
import com.polligonalApps.QEnem.models.QuestaoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestaoRepository extends CrudRepository<QuestaoModel,Long> {

    public void deleteByCadernos(CadernoModel cadernoModel);
}
