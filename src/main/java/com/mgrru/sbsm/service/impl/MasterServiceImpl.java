package com.mgrru.sbsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mgrru.sbsm.dao.IMaster;
import com.mgrru.sbsm.entity.Master;
import com.mgrru.sbsm.entity.Servant;
import com.mgrru.sbsm.service.MasterService;

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class MasterServiceImpl implements MasterService {

    @Autowired
    private IMaster iMaster;

    @Override
    public boolean registerMaster(Master master) {
        return iMaster.addMaster(master) > 0;
    }

    @Override
    @Transactional(readOnly = false)
    public Master getMaster(Integer id) {
        return iMaster.getMasterById(id);
    }

    @Override
    public boolean updateMaster(Master master) {
        return iMaster.updateMaster(master) > 0;
    }

    @Override
    public boolean deleteMaster(Integer id) {
        return iMaster.deleteMaster(id) > 0;
    }

    @Override
    @Transactional(readOnly = false)
    public List<Servant> getServants(Integer mid) {
        List<Servant> servants = new ArrayList<>();
        for (Servant servant : iMaster.getServants(mid)) {
            servants.add(servant);
        }
        return servants;
    }

    @Override
    public boolean addServant(Master master, Servant servant) {
        return iMaster.addServant(master, servant) > 0;
    }

    @Override
    public boolean sellServant(Integer sid) {
        return iMaster.deleteServant(sid) > 0;
    }

}
