package com.mgrru.sbsm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mgrru.sbsm.entity.Master;
import com.mgrru.sbsm.entity.Servant;
import com.mgrru.sbsm.entity.Shadow;


@Mapper
public interface IMaster {
    @Select("select * from master where id=#{id}")
    Master getMasterById(Integer id);

    @Insert("insert into master(name,password,sq) values(#{name},#{password},#{sq})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addMaster(Master master);

    @Delete("delete from master where id=#{id}")
    int deleteMaster(Integer id);

    @Update("update master set name=#{name}, password=#{password}, sq=#{sq} where id=#{id}")
    int updateMaster(Master master);

    /**
     * 查询影子
     * @param mid 御主的id
     * @return 返回影子的集合
     */
    @Select("select s.*,shadow.id as sid from servant s join shadow on s.id=shadow.sid where shadow.mid=#{mid}")
    List<Shadow> getServants(Integer mid);

    @Insert("insert into shadow(sid,mid) values(#{servant.id},#{master.id})")
    int addServant(Master master, Servant servant);

    /**
     * 删除影子
     * @param id 影子的sid
     */
    @Delete("delete from shadow where id=#{id}")
    int deleteServant(Integer id);
}
