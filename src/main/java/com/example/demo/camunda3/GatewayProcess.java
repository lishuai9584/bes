package com.example.demo.camunda3;

import cn.hutool.core.util.XmlUtil;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
@Component
public class GatewayProcess implements ApplicationRunner {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

/*
        Deployment deploy = repositoryService.createDeployment().name("网关流程").addClasspathResource("gateway.bpmn").deploy();
        System.out.println("流程id:" + deploy.getId());
        System.out.println("流程name:" + deploy.getName());

        Map<String, Object> map = new HashMap<>();
        //设置流程发起人
        identityService.setAuthenticatedUserId("initiator");
        map.put("userOne", "userOne");
        map.put("day", 4);
        map.put("minister", "userTwo");
        map.put("manager", "userThree");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_17vq3mq", "demo", map);


*/

        //建立DocumentBuilderFactor，用于获得DocumentBuilder对象：
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //建立DocumentBuidler;
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //import org.w3c.dom.Document;
        Document document = documentBuilder.parse(new File("D:\\ziliuchengid.bpmn"));
        NodeList node = document.getElementsByTagName("bpmn:serviceTask");
       /* for (int i = 0; i < node.getLength(); i++) {
            Element e = (Element)node.item(i);
            String name = e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            String email = e.getElementsByTagName("email").item(0).getFirstChild().getNodeValue();
            System.out.println(name);
            System.out.println(email);*/

            Document document1 = XmlUtil.readXML(new File("D:\\ziliuchengid.bpmn"));
            NodeList nodeListByXPath = XmlUtil.getNodeListByXPath("//*[name()='bpmn:serviceTask']", document1);
            //NodeList nodeListByXPath = XmlUtil.getNodeListByXPath("bpmn:serviceTask", document1);
        for (int i = 0 ; i < nodeListByXPath.getLength() ; i ++){
            Node name = nodeListByXPath.item(i).getAttributes().getNamedItem("name");
            System.out.println(nodeListByXPath.item(i).getNodeName()+"---"+nodeListByXPath.item(i).getAttributes().getNamedItem("id")+"---"+ name);
            String nodeValue = name.getNodeValue();
        }

            System.out.println(nodeListByXPath);
        }

}
