package com.xb.activity.param;

import lombok.Data;

/**
 * ApplyFlow
 *
 * @author yibo
 * @date 2021-05-06
 */
@Data
public class ApplyFlow {

    /**
     * 业务方唯一实体Code、用来做幂等或跟流程关联
     * 如果传了会拿businessCode 做幂等处理、否则按整个ApplyFlow做幂等
     * 30s不允许重复生成订单
     */
    private String businessCode;
}
