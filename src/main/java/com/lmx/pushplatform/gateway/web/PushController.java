package com.lmx.pushplatform.gateway.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lmx.pushplatform.client.ClientDelegate;
import com.lmx.pushplatform.gateway.api.CommonResp;
import com.lmx.pushplatform.gateway.api.PushReq;
import com.lmx.pushplatform.gateway.dao.AppRep;
import com.lmx.pushplatform.gateway.entity.AppEntity;
import com.lmx.pushplatform.gateway.entity.UserEntity;
import com.lmx.pushplatform.proto.PushRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/push")
public class PushController {
    @Autowired
    private ClientDelegate clientDelegate;
    @Autowired
    private AppRep appRep;


    /**
     * app定向推送
     *
     * @param pushReq
     * @return
     */
    @PostMapping("/push")
    public CommonResp push(@RequestBody PushReq pushReq) {
        //找到app设备下的所有注册用户(或者是设备号)
        AppEntity appEntity = appRep.findByAppName(pushReq.getAppName());
        Set<UserEntity> userEntities = appEntity.getUserEntitySet();
        Set<String> sets = Sets.newHashSet();
        for (UserEntity u : userEntities) {
            sets.add(String.valueOf(u.getId()));
        }
        if (CollectionUtils.isEmpty(sets))
            return CommonResp.defaultSuccess();
        PushRequest pushRequest = new PushRequest();
        pushRequest.setMsgType(1);
        pushRequest.setMsgContent(pushReq.getMsgContent());
        pushRequest.setToId(Lists.newArrayList(sets));
        pushRequest.setPlatform(pushReq.getPlatform());
        clientDelegate.sendOnly(pushRequest);
        return CommonResp.defaultSuccess();
    }
}
