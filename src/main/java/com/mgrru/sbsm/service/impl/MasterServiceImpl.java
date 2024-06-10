package com.mgrru.sbsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mgrru.sbsm.dao.IMaster;
import com.mgrru.sbsm.entity.JwtUtil;
import com.mgrru.sbsm.entity.Master;
import com.mgrru.sbsm.entity.Servant;
import com.mgrru.sbsm.entity.Shadow;
import com.mgrru.sbsm.service.MasterService;

@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class MasterServiceImpl implements MasterService {

    @Autowired
    private IMaster iMaster;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public boolean registerMaster(Master master) {
        return iMaster.addMaster(master) > 0;
    }

    /**
     * 登录功能
     * 
     * @return 返回token或者错误信息
     */
    @Override
    @Transactional(readOnly = true)
    public String login(Integer id, String password) {
        Master master = iMaster.getMasterById(id);
        if (master != null && master.getPassword().equals(password)) {
            return jwtUtil.generateToken(String.valueOf(id));
        } else {
            return "用户不存在或密码不正确!";
        }
    }

    @Override
    public String recharge(Master master, Integer rmb) {
        Integer sq = master.getSq();
        switch (rmb) {
            case 6:
                sq += 1;
                break;
            case 25:
                sq += 5;
                break;
            case 78:
                sq += 18;
                break;
            case 158:
                sq += 41;
                break;
            case 258:
                sq += 99;
                break;
            case 518:
                sq += 221;
                break;
            default:
                return "充值错误!!!";
        }
        master.setSq(sq);
        iMaster.updateMaster(master);
        return "充值成功!";
    }

    @Override
    @Transactional(readOnly = true)
    public Master getMaster(Integer id) {
        return iMaster.getMasterById(id);
    }

    @Override
    public boolean updateMaster(Master master) {
        Master m1 = iMaster.getMasterById(master.getId());
        if (master.getName() != null) {
            m1.setName(master.getName());
        }
        if (master.getPassword() != null) {
            m1.setPassword(master.getPassword());
        }
        return iMaster.updateMaster(m1) > 0;
    }

    @Override
    public boolean deleteMaster(Integer id) {
        return iMaster.deleteMaster(id) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servant> getServants(Integer mid) {
        List<Servant> servants = new ArrayList<>();
        for (Servant servant : iMaster.getServants(mid)) {
            servants.add(servant);
        }
        return servants;
    }

    @Override
    public List<Servant> summoingOneServant(Master master, List<Servant> servantPool) {
        // 获取原有圣晶石数量
        Integer sq = master.getSq();

        // 设定抽取消耗圣晶石数量
        final Integer price = 3;

        // 获取抽取记录
        List<Servant> summoningServants = new ArrayList<>();

        // 随机抽取从者
        int min = 0;
        int max = servantPool.size() - 1;
        int randomNumber = (int) (Math.random() * ((max - min) + 1)) + min;
        Servant servant = servantPool.get(randomNumber);

        // 抽取成功后扣除圣晶石数量
        sq -= price;
        master.setSq(sq);
        iMaster.updateMaster(master);

        // 添加抽取到的从者
        Shadow shadow = new Shadow(servant, null);
        iMaster.addServant(master, servant, shadow);

        // 添加成功后返回抽取结果
        summoningServants.add(shadow);
        return summoningServants;
    }

    @Override
    public List<Servant> summoingTenServant(Master master, List<Servant> servantPool) {
        List<Servant> summoningServants = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            summoningServants.add(summoingOneServant(master, servantPool).getFirst());
        }
        return summoningServants;
    }

    @Override
    public boolean sellServant(Master master, List<Integer> sidList) {
        Integer sq = master.getSq();
        if (sidList != null) {
            for (Integer sid : sidList) {
                Servant servant = iMaster.getServantBySid(sid);

                Integer price = 0;
                switch (servant.getStar()) {
                    case 5:
                        price = 2;
                        break;
                    case 4:
                        price = 1;
                        break;
                    default:
                        price = 0;
                        break;
                }

                if (iMaster.deleteServant(sid) > 0) {
                    sq += price;
                }
            }

            master.setSq(sq);
            iMaster.updateMaster(master);
            return true;
        }

        return false;
    }

}
