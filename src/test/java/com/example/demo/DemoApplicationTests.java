package com.example.demo;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        String s = null;
        s = "/nfs/dataset/11/ofrecord/train";
      //  String join = String.join("/", s.split("/")[0], s.split("/")[2]);
        if (s.contains("ofrecord")){
            int i = s.indexOf("ofrecord");
            s = s.substring(0, i);
        }

        System.out.println(s);
    }

}
