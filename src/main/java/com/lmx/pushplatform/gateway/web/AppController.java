package com.lmx.pushplatform.gateway.web;

import com.lmx.pushplatform.client.ClientDelegate;
import com.lmx.pushplatform.gateway.api.CommonResp;
import com.lmx.pushplatform.gateway.api.MobileRegReq;
import com.lmx.pushplatform.gateway.api.MobileRegResp;
import com.lmx.pushplatform.gateway.dao.AppRep;
import com.lmx.pushplatform.gateway.entity.AppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppRep appRep;
    @Autowired
    private ClientDelegate clientDelegate;

    /**
     * 注册app
     *
     * @param mobileRegReq
     * @return
     */
    @PostMapping("/reg")
    public CommonResp reg(@RequestBody MobileRegReq mobileRegReq) {
        AppEntity appEntity = AppEntity.builder()
                .developerId(mobileRegReq.getDeveloper())
                .appName(mobileRegReq.getAppName())
                .appKey(UUID.randomUUID().toString().replaceAll("-", ""))
                .appSecret(UUID.randomUUID().toString().replaceAll("-", ""))
                .build();
        appRep.save(appEntity);
        return CommonResp.defaultSuccess(MobileRegResp.builder()
                .appId(appEntity.getId())
                .appKey(appEntity.getAppKey())
                .appName(appEntity.getAppName())
                .appSecret(appEntity.getAppSecret())
                .build());
    }
}
