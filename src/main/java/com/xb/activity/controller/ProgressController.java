package com.xb.activity.controller;

import com.codahale.metrics.Timer;
import com.xb.activity.biz.ProcessBiz;
import com.xb.activity.dto.FlowOrder;
import com.xb.activity.param.ApplyFlow;
import com.xb.monitor.BMonitor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Random;

/**
 * 流程管理Ø
 *
 * @author 莫问
 * @date 2021-05-06
 */
@RestController
public class ProgressController {

    /**
     * 业务层: 审批流
     */
    @Resource
    private ProcessBiz processBiz;

    /**
     * 申请创建工作流 如果传了会拿businessCode 做幂等处理、否则按整个ApplyFlow做幂等 30s不允许重复生成订单 可以为空
     *
     * @param applyFlow
     *            工作流实例
     * @return 生成的订单
     */
    @PostMapping("apply")
    public FlowOrder apply(@Valid @RequestBody ApplyFlow applyFlow) {
        BMonitor.meter("xiao_test.error");

        Timer.Context xiao_test = BMonitor.timer("xiao_test");
        try {
            Random random = new Random();
            int num = random.nextInt(1000 - 50) + 50 + 1;
            Thread.sleep(Long.parseLong(String.valueOf(num)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        xiao_test.stop();
        return processBiz.apply(applyFlow);
    }
}
