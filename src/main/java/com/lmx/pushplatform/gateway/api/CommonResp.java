package com.lmx.pushplatform.gateway.api;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by limingxin on 2017/7/5.
 */
@Data
@Builder
public class CommonResp<T> {
    String code = "0000";
    String msg = "成功";
    T data;
}
