常用Hql语句:

1. // HQL: Hibernate Query Language.
   // 特点：

   ```sql
   // >> 1，与SQL相似，SQL中的语法基本上都可以直接使用。
   // >> 2，SQL查询的是表和表中的列；HQL查询的是对象与对象中的属性。
   // >> 3，HQL的关键字不区分大小写，类名与属性名是区分大小写的。
   // >> 4，SELECT可以省略.
   ```

   ```
   // 1，简单的查询，Employee为实体名而不是数据库中的表名(面向对象特性)
   hql = "FROM Employee";
   hql = "FROM Employee AS e"; // 使用别名
   hql = "FROM Employee e"; // 使用别名，as关键字可省略
   ```

   ```
   // 2，带上过滤条件的（可以使用别名）：Where
   hql = "FROM Employee WHERE id<10";
   hql = "FROM Employee e WHERE e.id<10";
   hql = "FROM Employee e WHERE e.id<10 AND e.id>5";
   ```

   ```
   // 3，带上排序条件的：Order By
   hql = "FROM Employee e WHERE e.id<10 ORDER BY e.name";
   hql = "FROM Employee e WHERE e.id<10 ORDER BY e.name DESC";
   hql = "FROM Employee e WHERE e.id<10 ORDER BY e.name DESC, id ASC";
   ```

   // 4，指定select子句（不可以使用select *）
   hql = "SELECT e FROM Employee e"; // 相当于"FROM Employee e"
   hql = "SELECT e.name FROM Employee e"; // 只查询一个列，返回的集合的元素类型就是这个属性的类型
   hql = "SELECT e.id,e.name FROM Employee e"; // 查询多个列，返回的集合的元素类型是Object数组
   hql = "SELECT new Employee(e.id,e.name) FROM Employee e"; // **可以使用new语法，指定把查询出的部分属性封装到对象中**

   ```
   // 5，执行查询，获得结果（list、uniqueResult、分页 ）
   Query query = session.createQuery("FROM Employee e WHERE id<3");
   query.setFirstResult(0);
   query.setMaxResults(10); // 等同于 limit 0,10
   //两种查询结果list、uniqueResult
   // List list = query.list(); // 查询的结果是一个List集合
   // Employee employee = (Employee) query.uniqueResult();// 查询的结果是唯一的一个结果，当结果有多个，就会抛异常
   ```

   ```
   // 6，方法链
   List list = session.createQuery(//
   "FROM Employee e")//
   .setFirstResult(0)//
   .setMaxResults(10)//
   .list();
   ```

   // 7，聚集函数：count(), max(), min(), avg(), sum()
   hql = "SELECT COUNT(*) FROM Employee"; // 返回的结果是Long型的
   hql = "SELECT min(id) FROM Employee"; // 返回的结果是id属性的类型

   //8，分组: Group By ... Having
   hql = "SELECT e.name,COUNT(e.id) FROM Employee e GROUP BY e.name";
   hql = "SELECT e.name,COUNT(e.id) FROM Employee e GROUP BY e.name HAVING count(e.id)>1";
   hql = "SELECT e.name,COUNT(e.id) FROM Employee e WHERE id<9 GROUP BY e.name HAVING count(e.id)>1";
   hql = "SELECT e.name,COUNT(e.id) " + //
   "FROM Employee e " + //
   "WHERE id<9 " + //
   "GROUP BY e.name " + //
   "HAVING count(e.id)>1 " + //
   "ORDER BY count(e.id) ASC";
   hql = "SELECT e.name,COUNT(e.id) AS c " + //
   "FROM Employee e " + //
   "WHERE id<9 " + //
   "GROUP BY e.name " + //
   "HAVING count(e.id)>1 " + // 在having子句中不能使用列别名
   "ORDER BY c ASC"; // 在orderby子句中可以使用列别名

   ```
   // 9，连接查询 / HQL是面向对象的查询
   //>> 内连接（inner关键字可以省略）
   hql = "SELECT e.id,e.name,d.name FROM Employee e JOIN e.department d";
   hql = "SELECT e.id,e.name,d.name FROM Employee e INNER JOIN e.department d";
   //>> 左外连接（outer关键字可以省略）
   hql = "SELECT e.id,e.name,d.name FROM Employee e LEFT OUTER JOIN e.department d";
   //>> 右外连接（outer关键字可以省略）
   hql = "SELECT e.id,e.name,d.name FROM Employee e RIGHT JOIN e.department d";
   //可以使用更方便的方法
   hql = "SELECT e.id,e.name,e.department.name FROM Employee e";
   ```

   // 10，查询时使用参数
   // >> 方式一：使用'?'占位
   hql = "FROM Employee e WHERE id BETWEEN ? AND ?";
   List list2 = session.createQuery(hql)//
   .setParameter(0, 5)// 设置参数，第1个参数的索引为0。
   .setParameter(1, 15)//
   .list();

   // >> 方式二：使用变量名
   hql = "FROM Employee e WHERE id BETWEEN :idMin AND :idMax";
   List list3 = session.createQuery(hql)//
   .setParameter("idMax", 15)//
   .setParameter("idMin", 5)//
   .list();

   // 当参数是集合时，一定要使用setParameterList()设置参数值
   hql = "FROM Employee e WHERE id IN (:ids)";
   List list4 = session.createQuery(hql)//
   .setParameterList("ids", new Object[] { 1, 2, 3, 5, 8, 100 })//
   .list();

   // 11，update与delete，不会通知Session缓存
   // >> Update
   int result = session.createQuery(//
   "UPDATE Employee e SET e.name=? WHERE id>15")//
   .setParameter(0, "无名氏")//
   .executeUpdate(); // 返回int型的结果，表示影响了多少行。
   // >> Delete
   int result1 = session.createQuery(//
   "DELETE FROM Employee e WHERE id>15")//
   .executeUpdate(); // 返回int型的结果，表示影响了多少行。

   