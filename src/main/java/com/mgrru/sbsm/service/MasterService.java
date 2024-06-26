package com.mgrru.sbsm.service;

import java.util.List;

import com.mgrru.sbsm.entity.Master;
import com.mgrru.sbsm.entity.Servant;

public interface MasterService {
    /**
     * 注册御主
     */
    boolean registerMaster(Master master);

    String login(Integer id,String password);

    /**
     * 充值
     */
    String recharge(Master master,Integer rmb);

    /**
     * 查询御主信息
     */
    Master getMaster(Integer id);

    /**
     * 修改御主信息
     * 
     * @param master 修改后的对象
     */
    boolean updateMaster(Master master);

    /**
     * 删除御主
     */
    boolean deleteMaster(Integer id);

    /**
     * 查询御主的所有影子
     * 
     * @param mid 御主id
     */
    List<Servant> getServants(Integer mid);

    /**
     * 购买影子
     */
    List<Servant> summoingOneServant(Master master, List<Servant> servantPool);

    List<Servant> summoingTenServant(Master master, List<Servant> servantPool);

    /**
     * 出售影子
     * 
     * @param sid 影子的sid
     */
    boolean sellServant(Master master, List<Integer> sidList);

}
