package com.lmx.pushplatform.gateway;

import com.lmx.pushplatform.gateway.api.CommonResp;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/11/24.
 */
@Component
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public CommonResp doException(Exception e) {
        return CommonResp.defaultError(e.getMessage());
    }
}
