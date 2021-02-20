> cube基础工程脚手架

>> package分层

>>> UserController OrderController PayController

>>>> 该层有统一的RequestBody和ResponseBody的切面操作，主要用于请求报文解密和响应报文加密

>>> UserService MemeberService GoodsService StockService

>>> GlobalManager（Cache,ExternalSystem Interface）

>>> Manager层编写规范
>>>> 增 
>>>>> saveXXX,返回int,标明该方法影响的行数;传参对象实体或List

>>>> 删
>>>>> deleteXXX,返回int,标明该方法影响的行数;传参具体条件

>>>> 改 
>>>>> updateXXX,返回int,标明该方法影响的函数;传参对象实体

>>>> 查
>>>>> getXXX,返回对象实体或List;传参具体条件

>>>> 按照上述规范的方法可以加缓存注解进行统一缓存处理，List暂不做缓存处理，不满足上述规范的方法未必可行

>>> 缓存统一处理逻辑，参照MyCacheInterceptor

>>>> Manager层收口，也是为了后续数据层操作管控提前做好准备

>>>> MYSQL DAO层使用了tk.mybatis,常用语句封装完善，只需要自定义复杂SQL即可,              具体参照com.cube.mapper.PhoenixUserMapper

>> 接口层参数校验
>>> 参照com.cube.controller.UserController

>> 多个线程池
>>> 参照com.cube.thread.ThreadPoolConfig的多个线程池定义

>> 轻量级定时任务
>>> 参照com.cube.task.MyTask

>> 异步调用
>>> 参照com.cube.event.MyEventListener--@Async

>> AOP自定义切面
>>> 参照com.cube.cache.MyCacheInterceptor(统一缓存操作)

>> 前后端交互统一数据格式
>>> 参照com.cube.pojo.MyResp,com.cube.pojo.Resp

>> 自定义配置加载
>>> 参照com.cube.config.MyConfig

>> 基于redis的分布式锁
>>> 参照com.cube.task.MyTask--@SchedulerLock

>> MOCK单元测试
>>> 参照test package

>> Swagger API
>>> 参照com.cube.controller.UserController



> 常用工具
hutool,common-lang3

> 工程打包

>> 通过pom+assembly打出zip，解压后的目录参照开源软件常规目录形式

>>> xxx.zip

>>>> bin        工程启停脚本

>>>> config     工程应用配置，日志配置

>>>> lib        工程依赖

>>>> xxx.jar    工程执行jar包

>>>> banner.txt 自定义启动字符画

> 运维标准目录
>> 按照公司运维标准，把zip包放到/apprun根目录下解压后运行即可

> clone该工程后，需要修改cube-project名字的文件如下

>> pom.xml

>> assembly.xml

>> bin/log_clean.sh

>> bin/processor_check.sh

>> bin/start.sh

>> bin/stop.sh

>> 启动命令

>>> nohup sh bin/start.sh >/dev/null 2>&1 &

未完待续...








