package com.lmx.pushplatform.gateway.web;

import com.lmx.pushplatform.gateway.api.CommonResp;
import com.lmx.pushplatform.gateway.api.DeveloperRegReq;
import com.lmx.pushplatform.gateway.dao.DeveloperRep;
import com.lmx.pushplatform.gateway.entity.DeveloperEntity;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/developer")
public class DeveloperController {
    @Autowired
    private DeveloperRep developerRep;


    @PostMapping("/reg")
    public CommonResp reg(@RequestBody DeveloperRegReq developerReq) {
        developerRep.save(DeveloperEntity.builder()
                .developer(developerReq.getUserName())
                .password(developerReq.getPassword())
                .build());
        return CommonResp.defaultSuccess(developerRep.findByDeveloperAndPassword(
                developerReq.getUserName(), developerReq.getPassword()));
    }

    @PostMapping("/login")
    public CommonResp login(@RequestBody DeveloperRegReq developerReq, HttpServletRequest httpServletRequest) {
        DeveloperEntity developerEntity = developerRep.findByDeveloperAndPassword(
                developerReq.getUserName(), developerReq.getPassword());
        if (developerEntity == null)
            return CommonResp.defaultError("9993", "用户名或密码错误");
        else {
            return CommonResp.defaultSuccess(developerEntity.getId());
        }
    }
}
