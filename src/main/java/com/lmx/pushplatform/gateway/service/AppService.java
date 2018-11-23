package com.lmx.pushplatform.gateway.service;

import com.lmx.pushplatform.gateway.dao.AppRep;
import com.lmx.pushplatform.gateway.entity.AppEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by limingxin on 2017/11/15.
 */
@Service
@Slf4j
public class AppService {
    @Autowired
    AppRep appRep;

    @Transactional
    public AppEntity getById(long id) {
        return appRep.getOne(id);
    }

    @Transactional
    public void saveUser(AppEntity userEntity) {
        appRep.save(userEntity);
    }

}
