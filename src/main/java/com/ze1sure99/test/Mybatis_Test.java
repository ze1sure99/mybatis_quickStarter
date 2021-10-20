package com.ze1sure99.test;

import com.ze1sure99.dao.IUserDao;
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
       //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
       InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
       //2.解析了配置文件，并创建了SqlSessionFactory工厂,获取到对象
       SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
       //3.生产sqlSession openSession会默认开启一个事务，但是该事务不会自动提交，在进行增删改操作时，要手动提交事务
       SqlSession sqlSession = sqlSessionFactory.openSession(true);
       //4.sqlSession调用方法：查询所有 selectList  查询单个 selectOne 添加 insert 修改 update 删除 delete
       List<User> users = sqlSession.selectList("user.findAll");
       //打印结果
       for (User user : users) {
           System.out.println(user);
       }
       //释放资源
       sqlSession.close();
   }
    @Test
    public void test2() throws IOException{
        ////加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //这一步中我们用到了构建者设计模式 获取到对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession⼯⼚对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//openSession给了一个true的时候，事务都是自动提交的
        User user = new User();
        user.setId(4);
        user.setUsername("ze1");
        user.setTel("155");
        //执⾏sql语句
        sqlSession.insert("user.saveUser",user);

        //释放资源
        sqlSession.close();
    }
    @Test
    public void test3() throws IOException{
        ////加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //这一步中我们用到了构建者设计模式 获取到对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession⼯⼚对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(4);
        user.setUsername("ze1sure");
        user.setTel("156");
        //执⾏sql语句
        sqlSession.update("user.updateUser",user);
        //增删改的时候提交一下事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
    @Test
    public void test4() throws IOException{
        ////加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //这一步中我们用到了构建者设计模式 获取到对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession⼯⼚对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        //执⾏sql语句
        sqlSession.update("user.deleteUser",user);
        //增删改的时候提交一下事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    /**
     * 代理开放方式
     * 采⽤ Mybatis 的代理开发⽅式实现 DAO 层的开发，这种⽅式是我们后⾯进⼊企业的主流。
     * Mapper 接⼝开发⽅法只需要程序员编写Mapper 接⼝（相当于Dao 接⼝），由Mybatis 框架根据接⼝定义创建接
     * ⼝的动态代理对象，代理对象的⽅法体同上边Dao接⼝实现类⽅法。
     * Mapper 接⼝开发需要遵循以下规范：
     * 1) Mapper.xml⽂件中的namespace与mapper接⼝的全限定名相同
     * 2) Mapper接⼝⽅法名和Mapper.xml中定义的每个statement的id相同
     * 3) Mapper接⼝⽅法的输⼊参数类型和mapper.xml中定义的每个sql的parameterType的类型相同
     * 4) Mapper接⼝⽅法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
     */
    @Test
    public void test5() throws IOException {
        //加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //这一步中我们用到了构建者设计模式 获取到对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession⼯⼚对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //动态代理IUserDao
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void test6() throws IOException {
        //加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //这一步中我们用到了构建者设计模式 获取到对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession⼯⼚对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //动态代理IUserDao
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user1 = new User();
//        user1.setId(5);
        user1.setUsername("ze1Sure");
        List<User> byCondition = mapper.findByCondition(user1);
        for (User user : byCondition) {
            System.out.println(user);
        }
    }
    @Test
    public void test7() throws IOException {
        //加载核⼼配置⽂件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //这一步中我们用到了构建者设计模式 获取到对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得sqlSession⼯⼚对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //动态代理IUserDao
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int[] array ={1,2,4};
        List<User> byCondition = mapper.findByIds(array);
        for (User user : byCondition) {
            System.out.println(user);
        }
    }
}
