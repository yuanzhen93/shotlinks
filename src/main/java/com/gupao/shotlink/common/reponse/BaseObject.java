package com.gupao.shotlink.common.reponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class BaseObject implements Serializable {


    private static final long uid = 1234567876543456765L;

    public static final BaseObject DUMMY = new BaseObject();

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String toString(){
        try{
            return objectMapper.writeValueAsString(this);
        }catch (Exception e){
//            log.error(e.getMessage());
        }
        return null;
    }


}
