

## Spring Data JPA简介:

Spring Data JPA 是 spring data 项目下的一个模块。提供了一套基于 JPA标准操作数据库的简化方案。底层默认的是依赖 Hibernate JPA 来实现的。

## Spring Data JPA 的技术特点:

我们只需要**定义接口并集成 Spring Data JPA 中所提供的接口**就可以了。**不需要编写接口实现类**。

## 一、 创建SpringDataJPA项目

#### 1 导入依赖

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

#### 2 配置数据源信息

```yml
server:
  port: 8086

spring:
  #通用的数据源配置
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/jpadata?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: root
  jpa:
    #这个参数是在建表的时候，将默认的存储引擎切换为 InnoDB 用的
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    #配置在日志中打印出执行的 SQL 语句信息。
    show-sql: true
    hibernate:
      #配置指明在程序启动的时候要删除并且创建实体类对应的表
      ddl-auto: create
```

## 二、 Spring Data JPA 的接口继承结构

![img](https://img2018.cnblogs.com/blog/1155586/201907/1155586-20190722164809711-1125847282.jpg)

## 三、 Spring Data JPA 的运行原理

```java
@PersistenceContext(name="entityManagerFactory") 
private EntityManager em;
@Test
public void test1(){
    //org.springframework.data.jpa.repository.support.SimpleJpaRepositor y@fba8bf
    //System.out.println(this.usersDao);
    //class com.sun.proxy.$Proxy29 代理对象 是基于 JDK 的动态代理方式创建的
    //System.out.println(this.usersDao.getClass());
    JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
    //getRepository(UsersDao.class);可以帮助我们为接口生成实现类。而 这个实现类是 SimpleJpaRepository 的对象
    //要求:该接口必须要是继承 Repository 接口
    UsersDao ud = factory.getRepository(UsersDao.class); 
    System.out.println(ud); 
    System.out.println(ud.getClass());
}
```

## 四、 Repository 接口

Repository 接口是 Spring Data JPA 中为我们提供的所有接口中的顶层接口 Repository 提供了两种查询方式的支持:

- ​    **1)基于方法名称命名规则查询**    
- ​	 **2)基于@Query 注解查询**

### 1 .方法名称命名规则查询

规则:    **findBy(关键字)+属性名称(属性名称的首字母大写)+查询条件(首字母大写)**

规则:
**find+全局修饰+By+实体的属性名称+限定词+连接词+ …(其它实体属性)+OrderBy+排序属性+排序方向**

| 关键字             | 方法命名                       | sql where 字句             |
| ------------------ | ------------------------------ | -------------------------- |
| And                | findByNameAndPwd               | where name= ? and pwd =?   |
| Or                 | findByNameOrSex                | where name= ? or sex=?     |
| Is,Equal           | findById,                      | findByIdEquals             |
| Between            | findByIdBetween                | where id between ? and ?   |
| LessThan           | findByIdLessThan               | where id < ?               |
| LessThanEqual      | findByIdLessThanEquals         | where id <= ?              |
| GreaterThan        | findByIdGreaterThan            | where id > ?               |
| GreaterThanEqual   | findByIdGreaterThanEquals      | where id > = ?             |
| After              | findByIdAfter                  | where id > ?               |
| Before             | findByIdBefore                 | where id < ?               |
| IsNull             | findByNameIsNull               | where name is null         |
| isNotNull,Not Null | findByNameNotNull              | where name is not          |
| Like               | findByNameLike                 | where name like ?          |
| NotLike            | findByNameNotLike              | where name not like ?      |
| StartingWith       | findByNameStartingWith         | where name like '?%'       |
| EndingWith         | findByNameEndingWith           | where name like '%?'       |
| Containing         | findByNameContaining           | where name like '%?%'      |
| OrderBy            | findByIdOrderByXDesc           | where id=? order by x desc |
| Not                | findByNameNot                  | where name <> ?            |
| In                 | findByIdIn(Collection<?> c)    | where id in (?)            |
| NotIn              | findByIdNotIn(Collection<?> c) | where id not in (?)        |
| True               | findByAaaTue                   | where aaa = true           |
| False              | findByAaaFalse                 | where aaa = false          |
| IgnoreCase         | findByNameIgnoreCase           | where UPPER(name)=UPPER(?) |

例如:

```java
//分页查询出符合姓名的记录,同理Sort也可以直接加上
public List<User> findByName(String name, Pageable pageable);
```
```sql
全局修饰： Distinct， Top， First
关键词： IsNull， IsNotNull， Like， NotLike， Containing， In， NotIn，
IgnoreCase， Between， Equals， LessThan， GreaterThan， After， Before…
排序方向： Asc， Desc
连接词： And， Or

And — 等价于 SQL 中的 and 关键字，比如 findByUsernameAndPassword(String user, Striang pwd)；
Or — 等价于 SQL 中的 or 关键字，比如 findByUsernameOrAddress(String user, String addr)；
Between — 等价于 SQL 中的 between 关键字，比如 findBySalaryBetween(int max, int min)；
LessThan — 等价于 SQL 中的 “<”，比如 findBySalaryLessThan(int max)；
GreaterThan — 等价于 SQL 中的”>”，比如 findBySalaryGreaterThan(int min)；
IsNull — 等价于 SQL 中的 “is null”，比如 findByUsernameIsNull()；
IsNotNull — 等价于 SQL 中的 “is not null”，比如 findByUsernameIsNotNull()；
NotNull — 与 IsNotNull 等价；
Like — 等价于 SQL 中的 “like”，比如 findByUsernameLike(String user)；
NotLike — 等价于 SQL 中的 “not like”，比如 findByUsernameNotLike(String user)；
OrderBy — 等价于 SQL 中的 “order by”，比如 findByUsernameOrderBySalaryAsc(String user)；
Not — 等价于 SQL 中的 “！ =”，比如 findByUsernameNot(String user)；
In — 等价于 SQL 中的 “in”，比如 findByUsernameIn(Collection userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；
NotIn — 等价于 SQL 中的 “not in”，比如 findByUsernameNotIn(Collection userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；

```



**嵌套实体:**

> 主实体中子实体的名称+ _ +子实体的属性名称
> List findByAddress_ZipCode(ZipCode zipCode)
> 表示查询所有 Address（地址）的zipCode（邮编）为指定值的所有Person(人员)



### 	 **2.基于@Query 注解查询**

一个类似HQL的语法,在接口上使用@Query标识

```java
 @Query("select a from user a where a.id = ?1") 
 public User findById(Long id); 
```


使用**@Modifying标识修改**

使用@Modifying标识修改

```java
 @Modifying 
 @Query("update User a set a.name = ?1 where a.id < ?2") 
 public int updateName(String name, Long id);
```


携带分页信息:

携带分页信息:

```java
@Query("select u from User u where u.name=?1")
public List<User> findByName(String name, Pageable pageable);
```

除此之外也可以使用原生sql,只需要**@Query(nativeQuery=true)**标识即可.



### 3.Example查询(按例查询)

#### 3.1简介:

按例查询（QBE）是一种用户界面友好的查询技术。 它允许动态创建查询，并且**不需要编写包含字段名称的查询**。 实际上，**按示例查询不需要使用特定的数据库的查询语言来编写查询语句**。

#### 3.2Example api的组成

**Probe**: 含有对应字段的实例对象。
**ExampleMatcher**：ExampleMatcher携带有关如何匹配特定字段的详细信息，相当于匹配条件。
**Example**：由Probe和ExampleMatcher组成，用于查询。

#### 3.3限制

属性**不支持嵌套或者分组约束**，比如这样的查询 firstname = ?0 or (firstname = ?1 and lastname = ?2)
**灵活匹配只支持字符串类型，其他类型只支持精确匹配**

#### 3.4作用:

1. 通过在使用springdata jpa时可以通过**Example来快速的实现动态查询**，同时配合**Pageable**可以实现快速的分页查询功能。

2. 对于**非字符串属性的只能精确匹配**，比如想**查询在某个时间段内注册的用户信息，就不能通过Example来查询**

#### 3.5案例:使用example计算Users表中用户名为李思的数量

   Controller

   ```java
   @ApiOperation(value = "Example的计数", notes = "使用JPA的计数与Example用法,前端传入对象中的任意一个参数均可实现该参数的系列操作")
   @GetMapping("countUser")
   private Long countUser(Users users){
       return userService.countNumebr(users);
   }
   ```

   Service实现类

   ```Java
   //计数,通过example实现不需要传入精确参数的模糊匹配,类似ES的分词搜索?,因为是jpa原生接口,所以不需要写dao层实现类
   @Override
   public Long countNumebr(Users users) {
       Example<Users> example = Example.of(users);
       return usersDaoRepository.count(example);
   
   }
   ```

在上面的测试之中，我们只是只是定义了Probe而没有ExampleMatcher，是因为默认会不传时会使用默认的匹配器,可以**自定义匹配器实现复杂查询,**这个留着以后扩展

## 五、分页查询

Controller层:

```java
/**
 * jpa是根据返回的类型自动判断是否分页，如果返回类型为Page,则返回的数据是带分页参数的集合，
 * 如果返回类型是list,则返回的数据是list集合。
 * @param page
 * @param size
 * @param params
 * @return
 */
@ApiOperation(value = "分页排序查全部", notes = "使用jpa自带的page对象分页并排序,参数为页数,每页多少个,以及以什么参数排序")
@PostMapping("findByPage")
private List<Users> findAllByPageAntDesc(Integer page, Integer size, String params){
    return userService.FindallPage(page,size,params).getContent();
}
```

Service实现类:

```java
//单表的全部分页,返回封装了的分页信息
@Override
public Page<Users> FindallPage(Integer page, Integer size,String params) {
    //分页是从0页开始,新方法PageRequest.of,老方法new PageRequest不用了
    Page<Users> page1 = usersDaoRepository.findAll(PageRequest.of(page-1, size,Sort.by(Sort.Direction.ASC,params)));
    return page1;
}
```

## 六.JPQL查询与原生SQL的基本使用

在 SQL 的查询方法上面使用 **@Query 注解**，如涉及到**删除和修改需要加上 @Modifying**，也可以根据需要添加 **@Transactional 对事物的支持，查询超时的设置**等。

```java
@Transactional(timeout = 15) 
@Modifying
@Query("update User set name= ?1 where id = ?2")
int modifyById(String  name, Long id);

@Transactional
@Modifying
@Query("delete from User where id = ?1")
void deleteById(Long id);

@Query("select u from User u where u.pwd= ?1")
User findByPwd(String pwd);
```

除此之外也可以**使用原生sql**,只需要`@Query(nativeQuery=true)`标识即可.