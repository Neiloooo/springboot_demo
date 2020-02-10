package com.springbootdemo.controller.redis;

import com.springbootdemo.controller.redis.pojo.Admin;
import com.springbootdemo.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "redis的测试类")
@RestController
@RequestMapping("/api/v1/redis")
public class RedisTestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisClientTools redisClientTools;

    @ApiOperation(value = "redis存入一个key-value键值对", notes = "使用StringRedisTemplate存入键值对")
    @GetMapping(value = "add")
    public Object add(){
        //存入key-value对象
        redisTemplate.opsForValue().set("name","xdclass2010");
        return Result.success("redis存入success");
    }

    @ApiOperation(value = "redis中取出键值对", notes = "使用redis根据key值取出键值对")
    @GetMapping(value = "get")
    public Object get(){
        String value = redisTemplate.opsForValue().get("name");
        return Result.success("取出成功",value);
    }

    @ApiOperation(value = "redis存入key,value,和时效(字符串)", notes = "redis存入key,value,和时效")
    @GetMapping(value = "test-set-string2")
    public String testSetString2(String key,String value){
            redisClientTools.set(key,value,1000L);
            return "seccess set string2";
    }

    @ApiOperation(value = "redis根据key值取出vlaue(字符串)", notes = "redis根据key值取出vlaue")
    @GetMapping(value = "test-get-String2")
    public Object testGetString2(String key){
        return redisClientTools.get(key);
    }

    @ApiOperation(value = "redis存入对象", notes = "redis存入对象,并且根据key值取出对象返回")
    @GetMapping(value = "test_set&get_obj")
    public Object testSetObj(Admin admin){
        boolean flage = redisClientTools.set(admin.getUsername(), admin);
        if (flage){
            return redisClientTools.get(admin.getUsername());
        }{
            return Result.error415("存入redis失败","真的失败了");
        }
    }
}
