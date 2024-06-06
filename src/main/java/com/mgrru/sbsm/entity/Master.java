package com.mgrru.sbsm.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 御主
 * @Param sq 圣晶石
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Master {
    Integer id;

    String name;

    @JSONField(serialize = false)
    String password;

    Integer sq;

    List<Servant> servants;
}
