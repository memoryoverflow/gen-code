# 听说你在接私活？ 一个助你效率翻倍的项目工具！！

最近，个人也接了点不足挂齿的小小私活。为了快速开发，开箱即用，每个项目不需要重新搭建。提升效率，就写了基本的项目架子，然后根据已建好的数据库，生成对应的增删改查的接口。生成即可使用。

## 一、生成的项目结构说明

### 一、项目技术

**SpringBoot + shiro + mysql + mybatis-plus;**



项目统一 restful 风格，统一异常 Json 处理化，respones 数据结构统一。



跨域问题处理。



也会过滤掉给前端的 一些 为 null 的属性，将值填充为 空字符串 “”；



也包含了日志文件输出。



**下面就是工具生成的项目结构**



### 二、项目结构

![image-20200526200735333](/Users/yongjian/%E9%A1%B9%E7%9B%AE%E7%BB%93%E6%9E%84.png)

项目的基本结构如上图所示。这是一块公共的代码块。



**core 包：** 是数据库生成的业务代码增删改；

mapper 文件 在resoure 目录下。



![](/Users/yongjian/%E9%A1%B9%E7%9B%AE%E7%BB%93%E6%9E%842.png)

每一个类，都有一个注释说明，在这就不一一做解释了。





### 二、业务代码模块

每一张表，都有对应的 增 删 改 查 的接口。使用的不同请求方式。



![](/Users/yongjian/core.png)





**增 删 改 查 接口 这样。**

![image-20200526202207939](/Users/yongjian/Library/Application%20Support/typora-user-images/image-20200526202207939.png)





**数据结构长这样：**

这是查的接口数据结果，包含了分处理。

![接口结果](/Users/yongjian/%E6%8E%A5%E5%8F%A3%E7%BB%93%E6%9E%9C.png)





**Service 接口：**

![service](/Users/yongjian/service.png)



**ServiceImpl**

![service](/Users/yongjian/impl.png)





**Mapper接口：**

![service](/Users/yongjian/mapper.png)



**xml:**

![service](/Users/yongjian/xml.png)

这是项目的基本结构了。



### 三、相关文件

**1、R.java 封装统一响应前端数据结构**

![service](/Users/yongjian/R.png)



**2、统一异常枚举**

![service](/Users/yongjian/Error.png)

**3、定义自定义异常，限制错误枚举**

![service](/Users/yongjian/serviceException.png)

**4、全局异常捕获拦截**

![service](/Users/yongjian/异常捕获.png)



基本的结构代码，介绍到此。





## 二、项目生成工具源码结构

工具源码结构

![gen](/Users/yongjian/gen.png)

源码结构不做说明了，太多东西。



### 2、1 项目工具的使用

![使用](/Users/yongjian/%E4%BD%BF%E7%94%A8.png)

项目所需属性在类 `ConfigProperties.java` 中

可通过 main 方法进行项目的快速生成 也可以部署出去，通过 http 的形式，打包下载。



![](/Users/yongjian/%E6%89%93%E5%8C%85%E4%B8%8B%E8%BD%BD.png)





源码地址![]()