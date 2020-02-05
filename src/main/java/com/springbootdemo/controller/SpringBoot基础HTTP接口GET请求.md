# SpringBoot基础HTTP接口GET请求

#### 	讲解springboot接口，http的get请求，各个注解使用

​		1、GET请求
​		

```java
	1、单一参数@RequestMapping(path = "/{id}", method = RequestMethod.GET)
				1) public String getUser(@PathVariable String id ) {}		
```

```java
		2）@RequestMapping(path = "/{depid}/{userid}", method = RequestMethod.GET) 可以同时指定多个提交方法,Restfull风格,从Url中获取参数
		getUser(@PathVariable("depid") String departmentID,@PathVariable("userid") String userid
```
```java

			3）一个顶俩(url映射注解)
				@GetMapping = @RequestMapping(method = RequestMethod.GET)
				@PostMapping = @RequestMapping(method = RequestMethod.POST)
				@PutMapping = @RequestMapping(method = RequestMethod.PUT)
				@DeleteMapping = @RequestMapping(method = RequestMethod.DELETE)
```
```java
			4）@RequestParam(value = "name", required = true)
					可以设置默认值，比如分页 
```

```java
			5)@RequestBody 请求体映射实体类
				需要指定http头为 content-type为application/json charset=utf-8
```
```java
			6）@RequestHeader 请求头，比如鉴权
				@RequestHeader("access_token") String accessToken
```
```java
			7）HttpServletRequest request自动注入获取参数
```

