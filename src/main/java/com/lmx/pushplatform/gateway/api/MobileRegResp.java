package com.lmx.pushplatform.gateway.api;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by limingxin on 2017/7/5.
 */
@Data
@Builder
public class MobileRegResp {

    private String appName,
            appKey,
            appSecret,
            developer;
}
