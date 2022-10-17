package com.example.demo;


import com.example.demo.utils.RequestData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class DemoApplicationTests {


    @Test
    void genConsumerId(){
        String productName = "OpsLink Aiops";
        String name = "bes";
        String separator = "#/#/#";

        String id = UUID.randomUUID().toString()+"_"+productName+separator+name;
        System.out.println(id);
    }

    @Test
    void parseName(){
        String separator = "#/#/#";
        String consumerId = "1fb27ea1-9706-4e13-a459-a69a0fa3f800_OpsLink Aiops#/#/#bes";
        int indexOf = consumerId.indexOf(separator);
        String substring = consumerId.substring(indexOf + separator.length());
        System.out.println(substring);
    }



    @Test
    void contextLoads() {
        UUID uuid = UUID.randomUUID();
        String s2 = uuid.toString();
        String s = null;
        s = "/nfs/dataset/11/ofrecord/train";
      //  String join = String.join("/", s.split("/")[0], s.split("/")[2]);
        if (s.contains("ofrecord")){
            int i = s.indexOf("ofrecord");
            s = s.substring(0, i);
        }
        String s1 = s.replaceFirst("/nfs/", "");
        System.out.println(s1);
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


        /*String jsonArrStr = "[{\"name\":\"张三\",\"age\":20},{\"name\":\"李四\",\"age\":25}]";
        JSONArray jsonArray = JSONObject.parseArray(jsonArrStr);
        System.out.println(jsonArray);*/



    }

    @Test
    void test(){
        Map<String, Object> map = new HashMap<>();
        map.put("123.jpg",123);
        map.put("123",123);
        map.entrySet().stream().filter(o -> o.getKey().endsWith(".jpg"));
    }

    /**
     * 大小写字母
     */
    public final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    /**
     * 数字 1-9
     */
    public final static String[] num = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };
    /**
     * 随机生成 8-12 位包含 字母、数字 的密码
     *
     * @return
     */
    @Test
    public void  randomPassword() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(new Date().getTime());
        boolean flag = false;
        //输出几位密码长度  这里是有可能8 ，9 ，10 位
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            } else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag = !flag;
        }
        System.out.println(stringBuffer.toString());
        //return stringBuffer.toString();
    }

    String url = "http://127.0.0.1:8080/test";
    @Test
    public void testGet(){
        RequestData requestData = new RequestData();
        requestData.setMethod("GET");
        requestData.setUrl(url);

    }





}
