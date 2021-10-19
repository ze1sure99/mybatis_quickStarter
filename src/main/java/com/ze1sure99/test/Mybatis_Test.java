package com.ze1sure99.test;

import com.ze1sure99.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Mybatis_Test {
   @Test
   public void test1() throws IOException{
       ////加载核⼼配置⽂件
       InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
       //这一步中我们用到了构建者设计模式 获取到对象
       SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
       //获得sqlSession⼯⼚对象
       SqlSession sqlSession = sqlSessionFactory.openSession();
       //执⾏sql语句
       List<User> users = sqlSession.selectList("user.findAll");
       //打印结果
       for (User user : users) {
           System.out.println(user);
       }
       //释放资源
       sqlSession.close();
   }
}
