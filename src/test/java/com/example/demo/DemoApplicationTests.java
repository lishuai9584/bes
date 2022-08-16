package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Test
    void jsonParse() {

       /* String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "张三");
        jsonObject.put("dateformat",sdf.format(new Date()));
        jsonObject.put("date",new Date());
        jsonObject.put("sex", "男");
        jsonObject.put("age", 25);
        jsonObject.put("phone",222222);
        System.out.println("默认序列化无序打印：" +JSONObject.toJSONString(jsonObject));

        JSONObject jsonObjectOrdered = new JSONObject(true);
        jsonObjectOrdered.put("name", "张三");
        jsonObjectOrdered.put("date",sdf.format(new Date()));
        jsonObjectOrdered.put("sex", "男");
        jsonObjectOrdered.put("age", 25);
        jsonObjectOrdered.put("phone",22222222);
        System.out.println("默认序列化有序打印：" +JSONObject.toJSONString(jsonObjectOrdered));
*/

    /*    String jsonStr = "{\"name\":\"张三\",\"age\":20}";
        JSONObject jsonObject1 = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject1);
        System.out.println(jsonObject1.get("name"));

        String jsonContainArrStr = "{\"name\":\"张三\",\"age\":20,\"subject\":[\"语文\",\"数学\"]}";
        JSONObject jsonObject2 = JSONObject.parseObject(jsonContainArrStr);
        JSONArray subject = jsonObject2.getJSONArray("subject");
        System.out.println(subject);*/


        String jsonArrStr = "[{\"name\":\"张三\",\"age\":20},{\"name\":\"李四\",\"age\":25}]";
        JSONArray jsonArray = JSONObject.parseArray(jsonArrStr);
        System.out.println(jsonArray);

    }

}
