# fstart
之前用了好久的vstart，最近一直被杀毒软件报有病毒（网上查资料也说有后台动作），干脆自己基于quartz和springboot做一个吧。

目前1.0版本实现了定时启动指定应用程序（包括指定是否在fstart启动时启动），定时提醒。 

所有的定时配置都基于excel（方便管理，暂时没有太多调整ui的计划）。

模板中的前两行是样例，大家照着配置就行了。

如果不需要定时执行，cron表达式那一列留空就行。
 
所有的定时都是基于cron表达式的，不熟悉的同学请自行查询资料。
  
需要注意的是：启动程序如果要带参数，请一并放入programPath列。

#### 使用
##### 只需要将该jar放到启动程序中（windows平台自行配置好java路径）。
##### 任务配置文件默认在E:\FengZiLi\AssistantTools\fstart\jobs.xlsx ,请通过fstart-1.0.0.jar --jobpath=c:/myjobs.xlsx 来修改。
##### 启动后在windows右下角的提示区出现相应的图标，点击ConfigJobs即可自动打开任务配置用的Excel文件。

2023-04-09 11:09:13  调整：将弹窗提示从原来的模态对话框切换成了非模态对话框，解决多个提示运行时相互遮挡的问题。
另外executeWhenStart是指在fstart启动时是否执行。