



![yatori](https://socialify.git.ci/Changbaiqi/yatori/image?description=1&font=Inter&forks=1&issues=1&logo=https%3A%2F%2Fraw.githubusercontent.com%2FChangbaiqi%2Fyatori%2Fmain%2FREADME%2Fimages%2F1710254379397-modified.png&name=1&pattern=Diagonal%20Stripes&pulls=1&stargazers=1&theme=Dark)

<div align="center"><h1>Yatori-core系列</h1></div>

<div align="center"><h2>Yatori-go-console课程助手</h2></div>

<div align="center"><img width="125px" src="https://img.shields.io/badge/GO1.22.4-building-r.svg?logo=go"></img> <img width="80px" src="https://img.shields.io/github/stars/Changbaiqi/yatori.svg"></img> <img width="90px" src="https://img.shields.io/github/downloads/Changbaiqi/yatori/total.svg"></img> <img width="70px" src="https://img.shields.io/github/license/Changbaiqi/yatori.svg"></img></div>

## 📢作者有话说

> 1、因作者学业繁忙，之后的更新需要等2025年才能开始更新，不过目前所有功能都还是能用的，这点不用担心。
>
> 2、有些学校可能用仓辉的时候会卡住要么一直刷屏报错，这种情况可能是因为你所用的平台是英华套壳的，所以你只需要把刷课类型“CANGHUI”改成“YINGHUA”即可。

## 🤔问题咨询

> QQ交流群：932447008
>
> B站：[BiliBili for 长白崎](https://space.bilibili.com/36987520)（不定时更新计算机相关技术教程）
>
> 个人博客：[长白崎の个人博客 (changbaiqi.top)](https://blogs.changbaiqi.top/)
>
> 技术打赏：[赞助墙 | 长白崎の个人博客 (changbaiqi.top)](https://blogs.changbaiqi.top/sponsorWall/)

## 🎯功能支持及特性：

> - [x] 独立程序，不依赖浏览器
> - [x] AI自动识别跳过验证码
> - [x] 多账号同刷
> - [x] 支持状态邮箱通知
> - [x] 支持自动考试（目前支持英华和仓辉。别问，问就是只有人提供了仓辉和英华的账号我才能开发，没人提供其他平台账号测试我也没办法）
> - [x] 答题支撑AI大模型加持(目前支持智普和星火大模型)
> - [x] 灵活配置文件
> - [x] 自动继续上次记录时长刷课
> - [x] 可部署服务器
> - [x] 部分平台支持暴力模式（无视前置课程学习限制，一门课所有视屏同刷！！！）

## 🎯支持平台：

> - [x] 英华学堂（支持限制性暴力模式,支持自动考试）
> - [x] 创能平台（不支持暴力模式，支持自动考试）
> - [x] 仓辉实训（支持暴力模式，支持自动考试）
> - [x] 学习公社（目前只支持普通模式）
>
> 🏗️ 学习通（支持ing）
> 
> 🏗️ 重庆工业学院CQIE（支持ing)
>
> - [ ] 智慧树（暂不支持，除非有人提供账号支持开发测试）
> - [ ] 盗梦空间抢活动（估计要等比较久的时间再整合了）
>
> ==注：==英华限制性暴力模式指的是如果你学校英华平台的课程视屏没有前置视屏观看限制那么就可以开，这个前置视屏观看限制指的是，一个章节的视屏你要观看必须要先把前面章节的视屏看完才能看，这就叫做前置视屏观看限制。

## 🎉食用方式：

### 代码食用：

> 代码食用请转至yatori-go-core项目

### 直接食用:

> 下载releases然后解压修改config配置文件之后点击start.bat启动即可。
>
> 注意：填url的时候是填写学校英华的根链接，不能带uri，
>
> 比如[https://mooc.xxx.edu.com/](https://mooc.xxx.edu.com/)，而不是[https://mooc.xxx.edu.cn/xx/xx](https://mooc.xxx.edu.cn/xx/xx)
>
> 以及不能用[https://mooc.yinghuaonline.com/](https://mooc.yinghuaonline.com/)，要用自己学校的链接，比如[https://mooc.xxx.cn/](https://mooc.xxx.cn/)，每个学校的链接都不同，这个可以自己去找去问。
>
> 配置文件说明（==注意==！！！其实大部分参数可以根据需求进行省略不写，以下只是对于各参数的例子罢了！）：
>
> ```json
> setting:
>   basicSetting:
>     completionTone: 1 #是否开启完成提示音，0为关闭，1为开启
>     colorLog: 1 #是否开启彩色日志，0为关闭，1为开启，如果控制台乱码可以尝试改为0关闭
>     logOutFileSw: 1 #是否开启日志文件输出，0为关闭，1为开启
>     logLevel: "INFO" #日志类型，一般INFO即可
>     logModel: 0 #日志输出模式，0为以视屏提交学时为单位进行日志输出，1为以课程信息为单位进行输出
>     ipProxySw: 0 #是否开启IP代理，0代表关闭，1代表开启，开启后一定要子当前启动目录下创建ip.txt这个ip池文件，里面填写对应的代理IP即可，一行一个。注意，代理的IP一定要支持Https
>   aiSetting:
>     aiType: "TONGYI" #智普：CHATGLM、星火：XINGHUO、通义千问：TONGYI、豆包：DOUBAO、其他模型：OTHER
>     aiUrl: "" #默认不填，除非你用的不是上面所指明的AI模型，比如ChatGPT
>     model: "" #AI模型，不填则使用yatori默认选择的模型，如果你用的豆包则必填并且填的是接入点ID非模型名称，比如ep-2024xxxxx
>     API_KEY: "" #AI平台对应的apikey
> users:
>   - accountType: "YINGHUA" #平台类型，英华学堂：YINGHUA、仓辉：CANGHUI、学习公社：ENAEA
>     url: "url" #对应平台的url链接,学习公社可以不用填且可以直接把这一行去掉
>     account: "账号" #账号
>     password: "密码" #密码
>     coursesCustom:
>       videoModel: 1 #刷视屏模式，0代表不刷，1代表普通模式，2代表暴力模式
>       autoExam: 0 #是否自动考试，0代表不考试，1代表考试
>       includeCourses: []  #include和exclude填一个即可，include代表只有这里面的课程才刷，填课程名称，比如["xxxx","xxxx"]，学习公社填必修课程或者选修课程等
>       excludeCourses: []  #include和exclude填一个即可，exclude代表除了这里面的课程其他都刷，填课程名称，比如["xxxx","xxxx"]，学习公社填必修课程或者选修课程等
> # 添加多个账号的时候像下面这样接着添加多个用户信息就行
> #  - accountType: "YINGHUA" #平台类型，英华学堂：YINGHUA、仓辉：CANGHUI、学习公社：ENAEA（注：目前暂时只支持英华，并且有些学校可能是英华套壳的仓辉，所以如果填仓辉刷不了可以尝试改英华
> #    url: "url" #对应平台的url链接
> #    account: "账号" #账号
> #    password: "密码" #密码
> #    coursesCustom:
> #      videoModel: 1 #刷视屏模式，0代表不刷，1代表普通模式，2代表暴力模式
> #      autoExam: 0 #是否自动考试，0代表不考试，1代表考试
> #      includeCourses: []  #include和exclude填一个即可，include代表只有这里面的课程才刷，填课程名称，比如["xxxx","xxxx"]，学习公社填必修课程或者选修课程等
> #      excludeCourses: []  #include和exclude填一个即可，exclude代表除了这里面的课程其他都刷，填课程名称，比如["xxxx","xxxx"]，学习公社填必修课程或者选修课程等
> #  - accountType: "YINGHUA" #平台类型，英华学堂：YINGHUA、仓辉：CANGHUI、学习公社：ENAEA（注：目前暂时只支持英华，并且有些学校可能是英华套壳的仓辉，所以如果填仓辉刷不了可以尝试改英华
> #    url: "url" #对应平台的url链接
> #    account: "账号" #账号
> #    password: "密码" #密码
> #    coursesCustom:
> #      videoModel: 1 #刷视屏模式，0代表不刷，1代表普通模式，2代表暴力模式
> #      autoExam: 0 #是否自动考试，0代表不考试，1代表考试
> #      includeCourses: []  #include和exclude填一个即可，include代表只有这里面的课程才刷，填课程名称，比如["xxxx","xxxx"]，学习公社填必修课程或者选修课程等
> #      excludeCourses: []  #include和exclude填一个即可，exclude代表除了这里面的课程其他都刷，填课程名称，比如["xxxx","xxxx"]，学习公社填必修课程或者选修课程等
> ```
>
> 刷课支持多账号，根据需求自行进行改动。
>
> 示例1：
>
> ```json
> setting:
>   basicSetting:
>     completionTone: 1
>     colorLog: 1
>     logOutFileSw: 1
>     logLevel: "INFO"
>     logModel: 0
>     ipProxySw: 0
>   aiSetting:
>     aiType: "TONGYI"
>     aiUrl: ""
>     model: ""
>     API_KEY: "sk-enaflfjasdlfjjlsdafj"
> users:
>   - accountType: "YINGHUA"
>     url: "https://mooc.xxxx.cn/"
>     account: "114514"
>     password: "114514"
>     coursesCustom:
>       videoModel: 1
>       autoExam: 0
>       includeCourses: []
> ```
>

## 免责声明：

> 代码已开源，程序只供学习使用，严禁贩卖，若对贵公司造成损失立马删库（保命(doge)）。

## 贡献者

<a href="https://github.com/Changbaiqi/yatori/graphs/contributors">   <img src="https://contrib.rocks/image?repo=Yatori-Dev/yatori" /></a>

## 鸣谢

> 感谢[**JetBrains**](https://www.jetbrains.com/zh-cn/community/opensource/#support)提供的开源开发许可证，JetBrains 通过为核心项目贡献者免费提供一套一流的开发者工具来支持非商业开源项目。
>
> <img src="./README/images/jetbrains-variant-3.png" alt="jetbrains-variant-3" width="200px" />

[![Stargazers over time](https://starchart.cc/Changbaiqi/yatori.svg?variant=adaptive)](https://starchart.cc/Changbaiqi/yatori)
