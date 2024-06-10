package com.mgrru.sbsm.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Component
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Shadow extends Servant {
    private Integer sid;
    public Shadow(Servant servant,Integer sid){
        super(servant.id,servant.star,servant.name);
        this.sid = sid;
    }
}
