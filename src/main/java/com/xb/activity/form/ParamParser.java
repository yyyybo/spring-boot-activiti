package com.xb.activity.form;

import java.util.List;

/**
 * ParamParser
 *
 * @author yibo
 * @date 2021-05-06
 */
public interface ParamParser {

    /**
     * 根据 flowOrderId 将表单数据转换为 clazz 对应的实例，其中 clazz 中的字段可代表多个节点表单
     * 一般用于在回调接口中将表单转为业务 bean 进行一些处理
     *
     * @param clazz 转换结果类型，clazz 中可包含了多个 userTask 节点的多个组数据
     * @param flowOrderId 待转换表单的 flowOrderId
     * @param <T> 类型
     * @return 转换结果
     */
    <T> T parseForm(Class<T> clazz, long flowOrderId);

    /**
     * 将 bean 转为表单数据，一般用于使用 api 发起流程时提交主表单数据。
     *
     * @param params 待转换业务数据，此处表示同一个表单组的多条数据
     * @param <T> 数据类型
     * @return 表单数据
     */
    <T> List<FieldVariableGroup> parseBean(List<T> params);

    <T> List<FieldVariableGroupResp> parseBeanToResp(List<T> params);
}
