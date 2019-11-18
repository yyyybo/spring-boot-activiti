/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xb.activity.controller.model;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.micrometer.core.instrument.util.JsonUtils;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建模板
 *
 * @author 莫问
 */
@RestController
@RequestMapping("model")
public class ModelCreateController implements ModelDataJsonConstants {

  protected static final Logger LOGGER = LoggerFactory.getLogger(ModelCreateController.class);

    @Resource
    private ModelService modelService;

    @Resource
    private RepositoryService repositoryService;

    /**
     * 创建模型
     * test:先输入http://localhost:8080/ActivitiWorkFlowDemo/model/create
     * 再输入：http://localhost:8080/ActivitiWorkFlowDemo/modeler.html?modelId=获取到的modelId
     */
    @RequestMapping("/create")
    public String create(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            Model modelData = this.modelService.createModel();
            System.out.println("create projectPath:"+request.getContextPath());
            resultMap.put("resultCode", "success");
            resultMap.put("resultUrl", "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            LOGGER.info("创建模型失败：");
            resultMap.put("resultCode", "error");
        }
        String resultJson = JSONUtils.toJSONString(resultMap);
        return resultJson;
    }

    /**
     * 创建模型
     * test:先输入http://localhost:8080/ActivitiWorkFlowDemo/model/create
     * 再输入：http://localhost:8080/ActivitiWorkFlowDemo/modeler.html?modelId=获取到的modelId
     */
    @RequestMapping("/export")
    public String create(HttpServletResponse response, String modelId) {
        BufferedOutputStream bos = null;
        try {

            try {
                Model modelData = repositoryService.getModel(modelId);

                byte[] bpmnBytes = repositoryService.getModelEditorSource(modelData.getId());

                // 封装输出流
                bos = new BufferedOutputStream(response.getOutputStream());
                bos.write(bpmnBytes);// 写入流

                String filename = modelData.getId() + ".bpmn20.xml";
                response.setContentType("application/x-msdownload;");
                response.setHeader("Content-Disposition", "attachment; filename=" + filename);
                response.flushBuffer();

            } finally {
                bos.flush();
                bos.close();
            }

        } catch (Exception e) {
            System.out.println("流程下载失败");
            e.printStackTrace();
        }
        return "成功";
    }

}
