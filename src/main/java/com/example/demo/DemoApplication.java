package com.example.demo;

        import lombok.val;
        import org.camunda.bpm.engine.RuntimeService;
        import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
        import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private RuntimeService runtimeService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
