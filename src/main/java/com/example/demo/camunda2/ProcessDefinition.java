package com.example.demo.camunda2;

import lombok.val;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作流程定义
 */
@Component
public class ProcessDefinition implements ApplicationRunner {

    /**
     * 操作流程定义接口
     */
    @Autowired
    private RepositoryService repositoryService;

    /**操作流程实例接口*/
    @Autowired
    private RuntimeService runtimeService;

    /**操作操作用户或者组接口*/
    @Autowired
    private IdentityService identityService;

    /**操作任务接口*/
    @Autowired
    private TaskService taskService;

    /**查询历史表相关数据接口*/
    @Autowired
    private HistoryService historyService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*操作流程部署逻辑*/
        Deployment deploy = repositoryService.createDeployment().name("审批流程").addClasspathResource("shenpi.bpmn").deploy();
        System.out.println("流程id:" + deploy.getId());
        System.out.println("流程name:" + deploy.getName());


        Map<String, Object> map = new HashMap<>();
        map.put("userOne", "demo");
        map.put("userTwo", "demo");
        //设置流程发起人
        //identityService.setAuthenticatedUserId("userOne");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_15vn21w","demo", map);

        //查看发起人待办
        List<Task> taskList = taskService.createTaskQuery().processDefinitionKey("Process_15vn21w").taskAssignee("demo").list();

        for (Task task : taskList) {
            taskService.complete(task.getId());
            System.out.println("审批通过");
        }
        //查看经理待办
        List<Task> taskList1 = taskService.createTaskQuery().processDefinitionKey("Process_15vn21w").taskAssignee("demo").list();
        for (Task task : taskList1) {
            taskService.complete(task.getId());
            System.out.println("经理审批通过");
        }

        //查看用户已办
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().taskAssignee("demo").finished().list();
    }
}
