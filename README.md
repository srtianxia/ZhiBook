# 寒假
### 先说说收获
- 最开始先租了三个月的阿里云服务器自行配置了centOS，php， MySQL，apache等环境，操作centOS自然学了一些Linux命令。
- 从0到有学习了php和MySQL写了app的接口部分，但是php也就是几天的水平，边查边写。
- RxJava的学习（ps：说下感想,下面是RxJava的自我介绍，好处就是异步好嘛！提供了Schedulers机制并提供常用的io线程，计算线程，和android中的ui线程（主线程），可以方便调用一行切换，在android上使用后台线程获得数据，前台UI线程更新数据使用Rx就是两行代码的事儿，此外还有高级一点的变换（map，flatMap） 只是看了教程，没有在实际中使用，觉得线程切换一行完事儿就很简便了~）。
>  "a library for composing asynchronous and event-based programs using observable sequences for the Java VM"（一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库）

- 代码量

### 作业完成情况

#### 本着学习为主的态度，尽量少用开源
- 后台api方面，自学php+MySQL，问答方面使用自己写的，还找了个知乎日报的api，和图灵机器人的api（没用bmob的原因就是bomb把网络请求都封装好了连线程方面都进行了封装... 以前用过，现在写了感觉也没什么长进...）。
- retrofit + RxAndroid组合进行网络请求，实现了离线加载缓存数据（rx比较爽，尤其是mvp下在model层，思路特别清晰！！本来自己对先对httpUrlConnection和Okhttp的异步部分进行了简单封装，httpUrlConnection还使用了线程池进行了封装减少new Thread的性能损耗，但是有时候会出现请求不出来的情况（服务器返回数据过多偶尔会抽风））。
- 图片加载方面暂时使用fresco（不是我不想造轮子 = = 查了一部分资料和教主的书，想自己实现一个bitmap压缩和缓存imageLoader但是失败了，原因应该在网络请求方面上）
- ui方面主要使用 android.support:design官方提供的控件和一些动画效果（CoordinatorLayout + Behavior机制）以及afollestad.material-dialogs ，暂时自己实现了fab的滚动消失，一个LinearLayout的底部功能bar随滚动手势出现与消失（Behavior），fab随viewpager滚动而出现与消失。
- RecyclerView的灵活用法，通过getItemViewType()这个方法取得每一个item的类型，在onCreate和onBind方法对holder进行创建和绑定的时候判断从而加载出不同类型的item，实现了对recyclerview的header和footer的添加（发现-知乎日报部分），和一个简单的聊天界面（聊天-吵吵机器人部分）。
- 实现了一个markdown语法的文本编辑器（通过SpannableStringBuilder实现）可以预览，数据存在本地数据库，预览后可以存为图片。（感谢jnote开源项目）
- 回答页面实现editview和textview的图文混排（有bug为解决）
- 使用了一个图片裁剪的库（相机这块是个坑，有空要研究下）
- 想实现不重启activity的主题切换，尚未实现。
- dagger2的部分使用，熟悉下依赖注入框架

### 博客
- 应用内截取屏幕[Android应用内截取屏幕
](http://www.jianshu.com/p/0156f9eb39d3)
- 缓存的实现方法(retrofit + Okhttp的缓存实现)         [Android网络请求&缓存](http://www.jianshu.com/p/42a396430be5);
- RecyclerView多种效果实现 [RecyclerView多种效果实现](http://www.jianshu.com/p/dc2e4a2e924e)
