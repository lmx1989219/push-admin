package com.lmx.pushplatform.gateway.web;

import com.lmx.pushplatform.client.ClientDelegate;
import com.lmx.pushplatform.gateway.api.CommonResp;
import com.lmx.pushplatform.gateway.api.MobileRegReq;
import com.lmx.pushplatform.gateway.api.MobileRegResp;
import com.lmx.pushplatform.gateway.api.PushReq;
import com.lmx.pushplatform.gateway.dao.AppRep;
import com.lmx.pushplatform.gateway.entity.AppEntity;
import com.lmx.pushplatform.proto.PushRequest;
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

    @PostMapping("/reg")
    public CommonResp reg(@RequestBody MobileRegReq mobileRegReq) {
        AppEntity appEntity = AppEntity.builder()
                .developer(mobileRegReq.getDeveloper())
                .appName(mobileRegReq.getAppName())
                .appKey(UUID.randomUUID().toString().replaceAll("-", ""))
                .appSecret(UUID.randomUUID().toString().replaceAll("-", ""))
                .build();
        appRep.save(appEntity);
        return CommonResp.builder().data(MobileRegResp.builder()
                .appKey(appEntity.getAppKey())
                .appName(appEntity.getAppName())
                .appSecret(appEntity.getAppSecret())
                .build()).build();
    }


    @PostMapping("/auth")
    public CommonResp auth(@RequestBody MobileRegReq mobileRegReq) {
        AppEntity appEntity = appRep.findAppEntityByAppNameAndAppKeyAndAppSecret(
                mobileRegReq.getAppName(), mobileRegReq.getAppKey(), mobileRegReq.getAppSecret());
        return CommonResp.builder().code(appEntity == null ? "9999" : "0000").build();
    }

    /**
     * 平台接口，用于以设备纬度推送
     *
     * @param pushReq
     * @return
     */
    @PostMapping("/push")
    public CommonResp push(@RequestBody PushReq pushReq) {
        PushRequest pushRequest = new PushRequest();
        pushRequest.setMsgContent(pushReq.getMsgContent());
        pushRequest.setPlatform(pushReq.getPlatform());
        clientDelegate.sendOnly(pushRequest);
        return CommonResp.builder().build();
    }
}
