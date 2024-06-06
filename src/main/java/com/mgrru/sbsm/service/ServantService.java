package com.mgrru.sbsm.service;

import java.util.List;

import com.mgrru.sbsm.entity.Servant;

public interface ServantService {

    List<Servant> getAllServant();

    boolean addServant(Servant servant);

    boolean updateServant(Servant servant);

    boolean deleteServant(Integer id);
}
