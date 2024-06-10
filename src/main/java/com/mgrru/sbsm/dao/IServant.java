package com.mgrru.sbsm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mgrru.sbsm.entity.Servant;

@Mapper
public interface IServant {

    @Select("select * from servant")
    List<Servant> getAllServants();

    @Select("select * from servant where star=#{star}")
    List<Servant> getServantsByStar(Integer star);


    @Insert("insert into servant(star,name) values(#{star},#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addServant(Servant servant);

    @Delete("delete from servant where id=#{id}")
    int deleteServant(Integer id);

    @Update("update servant set star=#{star}, name=#{name} where id=#{id}")
    int updateServant(Servant servant);
}
