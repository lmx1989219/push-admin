package com.lmx.pushplatform.gateway.dao;

import com.lmx.pushplatform.gateway.entity.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by limingxin on 2017/11/16.
 */
public interface AppRep extends JpaRepository<AppEntity, Long> {

    public AppEntity findAppEntityByAppNameAndAppKeyAndAppSecret(String a, String b, String c);
}