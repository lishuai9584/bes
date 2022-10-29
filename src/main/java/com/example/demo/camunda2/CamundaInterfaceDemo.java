package com.example.demo.camunda2;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//import static com.fasterxml.jackson.databind.type.LogicalType.Map;

public class CamundaInterfaceDemo {

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

    /**部署流程定义*/
    public void Demo1(){
        //这种就是加载项目resources目录下的one.bpmn文件
        Deployment deploy = repositoryService.createDeployment()
                .name("demo1")
                .addClasspathResource("one.bpmn")
                .deploy();
        //这种就可以使用自己拼接的xml字符串
        Deployment deploy1 = repositoryService.createDeployment()
                .name("demo2")
                .addString("demo"+".bpmn","resource")
                .deploy();
    }

    /**开启流程实例
     可以通过流程定义ID或者流程定义Key，第二个参数是业务ID,第三个参数是流程变量，都可以不给。*/
    public void Demo2(){
        //通过Key
        runtimeService.startProcessInstanceByKey("processDefinitionKey", "businessKey", "map" );

        //通过ID
        runtimeService.startProcessInstanceById( "processDefinitionId", "businessKey", "map");
    }


    /**查询我的待办*/
    public void Demo3(){
        List<Task> taskList = taskService.createTaskQuery().taskAssignee("userId").list();
    }

}
