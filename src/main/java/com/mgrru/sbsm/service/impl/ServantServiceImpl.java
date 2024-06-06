package com.mgrru.sbsm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mgrru.sbsm.dao.IServant;
import com.mgrru.sbsm.entity.Servant;
import com.mgrru.sbsm.service.ServantService;

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class ServantServiceImpl implements ServantService {

    @Autowired
    private IServant iServant;

    @Override
    @Transactional(readOnly = true)
    public List<Servant> getAllServant() {
        return iServant.getAllServants();
    }

    @Override
    public boolean addServant(Servant servant) {
        return iServant.addServant(servant) > 0;
    }

    @Override
    public boolean updateServant(Servant servant) {
        return iServant.updateServant(servant) > 0;
    }

    @Override
    public boolean deleteServant(Integer id) {
        return iServant.deleteServant(id) > 0;
    }

}
