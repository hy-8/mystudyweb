这是我学习编写的模仿b站页面的基于对视频上传和播放功能的网站。使用了controller，service，map三端操作。
控制器类调用service，service包含业务逻辑，service调用map对数据进行操作。
用到了java编写后端技术实现，html，js等编写前端，json实现前后端关联。用到了spring boot，引入 spring-boot-starter-web 自动配置 Tomcat，mysq作为数据库l，使用mybatis简化 Java 程序与数据库的交互，下面是实现细节。

1.使用javabean封装数据，在dto文件下定义了video和每部分part但是写错了写成了“play”，使用get,set,有参构造和无参构造函数供外界访问。

2.使用控制器类（controller），负责接受用户请求，调用service处理业务逻辑并返回响应。
  2.1
  注解：
  @controller 
   类{}  将类定义为controller对象，

  @RequestMapping("/video/{bv}")  给控制器里的方法加参数，类型和我们之前定义的类型一致，名字和路径一致
     双引号内填写用户访问路径，与前端设定对应，参数{bv}对应网站访问时末尾#bv
     用于从 URL 中动态提取参数,

  @ResponseBody  把java中的map对象什么的对象转成json数据传输与前端通信
     用于 ​​将方法的返回值直接作为 HTTP 响应体（Response Body）​​，通常返回 ​​JSON/XML​​ 数据

  @PathVariable ​​String bv  表示参数值从路径{bv}获取
     从 URL 路径中提取变量值,用于查询、更新或删除资源的操作（如通过 ID 获取用户），可以用于多个参数
     获取。

  @Autowired  用于 ​​自动依赖注入（DI, Dependency Injection）​​。它允许 Spring 容器自动将所需的 Bean 注入到目标位置（如字段、构造方法、Setter 方法等），无需手动实例化对象。
  @RequestBody  把json数据转成java对象
     将 HTTP 请求体（Request Body）中的 JSON/XML 数据自动转换为 Java 对象​​ 

  @Value("${video-path}") 从配置文件（application.properties）中读取配置值，并注入Spring管理的Bean中。
  2.2用到的函数和对象
   MultipartFile  专用于上传二进制数据的类型
   data.transferTo(Path.of(videoPath,url...)把上传的临时文件输送到目录下，
   FileOutputStream 文件输出流，创建新文件并写入内容，Files.copy(part,os)参数一原始文件，参数二目标文件，
   注意最后要删除文件，如果使用try，则系统会自动在try,catch执行完后使用fianl删除文件，无需自己手动删除。

3.mybatis下的接口
  使用mybatis的条件：
//使用mybatis两个条件：1.在xml中添加依赖，注意版本。
//                    2.在resources下加一个application.properties填写数据库信息
//使用的时候写一个javabean，字段和表中的信息：类名和表名，字段和属性
//StudentMapper接口，加一个注解，然后实现增删改查注意注解，多个参数可以使用一个对象代替

   在mapper文件夹下有多个接口PlayMapper等
 3.1  注解
  @Mapper   这不是一个普通接口，专用于增删改查
  需要一个实现类(mybatis和spring共同提供)可以通过@Autowired依赖注入获取是实现类对象
  public interface PlayMapper{}  被 @Mapper 标记的接口会被 MyBatis 动态代理，生成数据库操作的实现类
  用于 ​​标识一个接口是 MyBatis 的映射器（Mapper）​​，Spring/Spring Boot 会自动为其生成实现类，从而可以直接注入使用。

  @Select()  @Insert()  @Update 等实现对数据库的操作
   void updateBv(@Param("bv") String bv, @Param("id") int id);@Param用于 ​​明确指定方法参数的名称
  @Param（“bv”）String bv,@Param("id") int id  当方法有多个参数时，避免 MyBatis 无法正确匹配参数名

 3.2 习惯与注意事项
  video类的有参构造要和select语句的参数顺序一样，所以建议加一个无参构造，他会使用get,set方法去找

  数据库的命名习惯：underscore 下划线分割多个单词
  java习惯：  驼峰命名法，多个单词第二个开始用大写开头  camel case
  导致名称不对应，导致查找不到，
  可以在application.properties配置文件中修改  mybatis.configuration.map-underscore-to-camel-case = true

  @Insert("""
            insert into video(type, category, title, cover, introduction, publish_time, tags) 
            values(#{type},#{category},#{title},#{cover},
            #{introduction},#{publishTime},#{tags})
            """)
    当获取多个值时可以使用#{}     注解配合 #{} 占位符实现了向数据库插入数据的功能。
  这个语法由mybatis提供，你要获取方法参数值，用#{}里面写上参数

    但是要注意#{}里面的值名称和javabean对应的名称一致

  @Insert("""
            insert into student(id,name) values
            (#{id},#{name})
            """)
    void insert(Student stu);//对调用student的javabean里的get，set方法赋值, 
    当多个参数来自同一个对象的时候可以使用


4.service
  4.1 service中主要编写业务逻辑
  
  4.2注解：
     @Service  标识一个类是业务逻辑层（Service 层）的组件
     @PostConstruct  这是一个初始化方法，在对象创建之后只会调用一次，
         所以如果不需要使用只要把这个注解注释就不实现对应操作

5 入口类
  5.1  作为功能实现的入口，在创建时一般会自己创建好（我没有写这几个注解的印象）
  
  5.2  注解
  @SpringBootApplication  //支持 SpringBoot 功能的应用程序
   在这个类里可以做一些url路径和磁盘路径的映射

  @Override 是 Java 的核心注解，用于 ​​显式标记方法覆写了父类或接口的方法​​。它的主要作用是增强代码可读性，并在编译时检查方法覆写的正确性。

  @Value("${video-path}")  //字符串的值来自配置文件
    private String videoPath；  为了方便修改磁盘路径


6.测试类  TestXxx
  6.1不是实际功能的地方，用于测试代码
 
  6.2  注解
  @SpringBootTest    单元测试类和spring连续起来
   Spring Boot 提供的测试注解，用于 ​​启动完整的 Spring 应用上下文​​，支持对应用程序进行​​集成测试​​（如测试           Controller、Service、数据库交互等）。

  @Autowired  StudentMapper studentMapper;
     这个注解会根据字段类型到spring中找看看有没有这个实现类对象
  @Test  测试入口，可以利用spring依赖注入什么的特性进行测试
     标记一个方法是​​可执行的单元测试方法，一个类只能有一个main但是可以有多个测试函数


7. 配置文件
pom.xml  
   是 ​​Maven 项目的核心配置文件​​，用于定义项目的依赖、构建配置、插件、环境信息等。一般在下载后就有了

application.properties 
   配置数据库名，密码，访问入口； 本地访问网站端口；配置java和mysql的名称定义规则的转换；文件存储在磁盘路径
   视频大小等等

application.properties 是 Spring Boot 项目的​​核心配置文件​​，用于定义应用程序的参数（如数据库连接、服务器端口、日志级别等）

总结：
分类​​	​​         技术/工具​​	​​                作用​​
​​后端框架​​	Spring Boot	        快速构建Web应用，自动配置Tomcat、依赖管理
​​数据库​​	MySQL + MyBatis	数据存储，通过MyBatis简化JDBC操作
​​前端技术​​	HTML/JS	                页面渲染和交互逻辑
​​数据交互​​	JSON	                前后端通信格式
​​构建工具​​	Maven (pom.xml)	依赖管理和项目构建
​​测试​​	JUnit + @SpringBootTest	单元测试和集成测试



