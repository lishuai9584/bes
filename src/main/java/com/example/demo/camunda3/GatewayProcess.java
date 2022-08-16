package com.example.demo.camunda3;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GatewayProcess implements ApplicationRunner {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //设置流程发起人
        identityService.setAuthenticatedUserId("initiator");
        map.put("userOne", "userOne");
        map.put("day", 4);
        map.put("minister", "userTwo");
        map.put("manager", "userThree");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_17vq3mq", "demo", map);
    }
}
