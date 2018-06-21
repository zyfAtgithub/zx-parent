package org.yf.zx.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring.xml"
})
public class RedisClientTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    /**
     * string操作
     */
    @Test
    public void testSetString() {
        redisTemplate.opsForValue().set("msg", "hello,world!");
    }

    @Test
    public void testGetString() {
        String msg = redisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    @Test
    public void testGetAndSet() {
        String msg = redisTemplate.opsForValue().getAndSet("msg", "hello,world2!");
        System.out.println(msg);
    }

    @Test
    public void testMultiSetString() {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("multi1", "multi1_value");
        maps.put("multi2", "multi2_value");
        maps.put("multi3", "multi3_value");
        redisTemplate.opsForValue().multiSet(maps);
        List<String> keys = new ArrayList<String>();
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");
        System.out.println(redisTemplate.opsForValue().multiGet(keys));
    }

    @Test
    public void testStringOp() {
        //1.Increment 增
        long val = redisTemplate.opsForValue().increment("count", 1);
        System.out.println(val);
        //2.Decrement 减
        val = redisTemplate.opsForValue().increment("count", -1);
        System.out.println(val);
        //3.append 字符串最后追加
        redisTemplate.opsForValue().set("time", "nowTime:");
        redisTemplate.opsForValue().append("time", new Date().toString());

        //4.get 截取指定位置的字符串
        redisTemplate.opsForValue().set("Content", "Hello world!");
        String subStr = redisTemplate.opsForValue().get("Content", 0, 0);
        System.out.println(subStr);

        //5.size 获取字符串长度
        long size = redisTemplate.opsForValue().size("Content");
        System.out.println("content size:" + size);

        //6.bit 位操作
        redisTemplate.opsForValue().set("bitTest", "a");
        // 'a' 的ASCII码是 97。转换为二进制是：01100001
        // 'b' 的ASCII码是 98  转换为二进制是：01100010
        // 'c' 的ASCII码是 99  转换为二进制是：01100011
        //因为二进制只有0和1，在setbit中true为1，false为0，因此我要变为'b'的话第六位设置为1，第七位设置为0
        redisTemplate.opsForValue().setBit("bitTest", 6, true);
        redisTemplate.opsForValue().setBit("bitTest", 7, false);
        System.out.println(redisTemplate.opsForValue().get("bitTest"));
        System.out.println(redisTemplate.opsForValue().getBit("bitTest", 7));
    }

    /**
     * list操作
     */
    @Test
    public void testList() {
        //trim 重置list
        long size = redisTemplate.opsForList().size("province");
        redisTemplate.opsForList().trim("province", size, 0);

        //left push  <--> lpush
        redisTemplate.opsForList().leftPush("province", "JiangSu");//返回的结果为推送操作后的列表的长度
        System.out.println("left push:\n" + redisTemplate.opsForList().range("province", 0, -1));

        //left pushall <--> lpush
        redisTemplate.opsForList().leftPushAll("province", "AnHui", "ZheJiang");
        System.out.println("left pushall:\n" + redisTemplate.opsForList().range("province", 0, -1));

        //right push <--> rpush
        redisTemplate.opsForList().rightPush("province", "SiChuan");
        System.out.println("right push:\n" + redisTemplate.opsForList().range("province", 0, -1));

        //left pushall <--> lpush
        redisTemplate.opsForList().rightPushAll("province", "YunNan", "GuiZhou");

        System.out.println("right pushall:\n" + redisTemplate.opsForList().range("province", 0, -1));

        //set 在列表中index的位置设置value值
        redisTemplate.opsForList().set("province", 0, "FuJian");
        System.out.println("set:\n" + redisTemplate.opsForList().range("province", 0, -1));

        //leftpop <--> lpop
        redisTemplate.opsForList().leftPop("province");
        System.out.println("leftpop:\n" + redisTemplate.opsForList().range("province", 0, -1));

        //rightpop <--> rpop
        redisTemplate.opsForList().rightPop("province");
        System.out.println("rightpop:\n" + redisTemplate.opsForList().range("province", 0, -1));
    }


    /**
     * Hash 操作
     */
    @Test
    public void testHash() {
        //put
        redisTemplate.opsForHash().put("student", "name", "小明");
        redisTemplate.opsForHash().put("student", "age", "27");
        redisTemplate.opsForHash().put("student", "phone", "176454512398");
        redisTemplate.opsForHash().put("teacher", "name", "王明");
        redisTemplate.opsForHash().put("teacher", "age", "45");
        redisTemplate.opsForHash().put("class", "name", "RedisClient");
        redisTemplate.opsForHash().put("class", "package", "com.redis.client");

        //entries 返回指定key 对应的 hash数据 map
        Map<Object, Object> stuInfo = redisTemplate.opsForHash().entries("student");
        System.out.println(stuInfo);

        System.out.println(redisTemplate.opsForHash().hasKey("teacher", "age"));

        //get
        System.out.println(redisTemplate.opsForHash().get("class", "name"));

        //increment
        System.out.println(redisTemplate.opsForHash().increment("student", "age", 1));

        //keys 获取key所对应的散列表的key
        Set<Object> props = redisTemplate.opsForHash().keys("student");
        System.out.println(props);

        //values 获取整个哈希存储的值
        List<Object> values = redisTemplate.opsForHash().values("student");
        System.out.println(values);

        //size
        System.out.println(redisTemplate.opsForHash().size("student"));


        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan("teacher", ScanOptions.NONE);
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    /**
     * set操作
     */
    @Test
    public void testSet() {

        //add 添加一个或多个数据
        long size = redisTemplate.opsForSet().add("course", "Java", "C", "C++", "Python", "PHP", "Land Rover", ".NET", "GO");
        redisTemplate.expire("course", 5, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForSet().members("course"));

        redisTemplate.opsForSet().add("Car", "Ford", "Jaguar", "Volvo");
        System.out.println(redisTemplate.opsForSet().members("Car"));
        //remove
        String[] courseRemove = {"C++", "Python", "PHP"};
        size = redisTemplate.opsForSet().remove("course", courseRemove);
        redisTemplate.expire("course", 5, TimeUnit.SECONDS);


        //pop 随机移除一个元素
        redisTemplate.opsForSet().pop("Car");

        //move 将 member 元素从 source 集合移动到 destination 集合
        boolean success = redisTemplate.opsForSet().move("course", "Land Rover", "Car");
        System.out.println("move success?" + success);

        //判断 member 元素是否是集合 key 的成员
        boolean isMember = redisTemplate.opsForSet().isMember("course", "Java");
        System.out.println("isMember:" + isMember);

        redisTemplate.opsForSet().add("set1", "a", "b", "c", "m");
        redisTemplate.opsForSet().add("set2", "b", "d", "e", "c");

        //intersect 交集
        System.out.println(redisTemplate.opsForSet().intersect("set1", "set2"));
        System.out.println(redisTemplate.opsForSet().intersectAndStore("set1", "set2", "set3"));

        //union 并集
        System.out.println(redisTemplate.opsForSet().union("set1", "set2"));
        System.out.println(redisTemplate.opsForSet().unionAndStore("set1", "set2", "set4"));

        //difference 差集
        System.out.println(redisTemplate.opsForSet().difference("set1", "set2"));
        System.out.println(redisTemplate.opsForSet().differenceAndStore("set1", "set2", "set5"));

        //distinct 合集（去重）
        System.out.println(redisTemplate.opsForSet().differenceAndStore("set1", "set2", "set6"));
    }


    //zset


    //并发问题
    public void incr(String key, long increment) {
        redisTemplate.opsForValue().increment(key, increment);
    }

    @Test
    public void testConcurrentOp() {
        redisTemplate.opsForValue().set("ConcurrentVal", "0");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName());
                    String oldVal = redisTemplate.opsForValue().get("ConcurrentVal");
                    String newVal = (Long.valueOf(oldVal) + 1) + "";
                    System.out.println(Thread.currentThread().getName() + "--->" + newVal);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    redisTemplate.opsForValue().set("ConcurrentVal", newVal);
                }
            });
        }
//        executorService.shutdown();
    }
}
