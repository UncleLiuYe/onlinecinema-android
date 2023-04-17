
# Android端：<br/>
# 一、Java-WebSocket：<br/>
本项目使用Java-WebSocket实现客户端与服务器Websocket通信。Java-WebSocket库是一个 100% 由Java 编写的准系统 WebSocket 服务器和客户端实现。 底层类实现了 java.nio，它允许非阻塞事件驱动模型（类似于 Web 浏览器的 WebSocket API），实现的 WebSocket 协议版本为：RFC 6455、RFC 7692。<br/>
# 二、Jackson：<br/>
本项目使用Jackson进行Json序列化和反序列化。Jackson是一个使用比较广泛的，用户来序列化和反序列化的JSON的JAVA框架，社区比较活跃，更新速度较快。Jackson在解析大JSON的速度非常快，运行时占用内存比较低，性能比较好。同时依赖的Jar包较少，拥有灵活的API，可以很容易的进行扩展和定制。SpringBoot框架默认使用Jackson作为JSON解析工具。<br/>
# 三、Okhttp：<br/>
本项目使用Okhttp实现与服务器进行Http通信。Okhttp是一个来自于Square的HTTP客户端，用于JAVA和Andoird应用程序。他的设计目标是为了更快的加载资源和节省带宽。Okhttp支持同步、异步的请求方式，还提供了诸多的插件，比如拦截器插件，可以非常方便根据业务进行定制。<br/>
# 四、Alipaysdk-Android：<br/>
本项目使用Alipaysdk-Android调用Alipay支付接口。Alipaysdk-Android是支付宝官方编写的一个用于Android端的支付框架，设计的目标是方便开发人员使用很少的时间将支付组件集成进入系统。内部提供了大量封装好的支付API，非常方便快捷。<br/>
# 五、io.github.youth5201314:banner<br/>
本项目使用banner实现轮播图。Banner是一个基于ViewPager2的Android的安卓端轮播图开源框架。因为使用了ViewPager2，性能很高，同时内部提供了多种的页面变换的API。如果不能满足业务需求，还可以根据提供的接口进行个性化定制。<br/>
# 六、glide<br/>
本项目使用glide获取并加载图片，让图片加载变的流畅。Glide是一个被Google所推荐的Android图片框架，在Google开源的Android的项目中，大量使用了Glide。同时Glide性能很高，还提供了非常丰富的处理图片、图片转换、不同状态下的图片资源设置等等的API，方便开发与使用。v
# 七、com.intuit.sdp:sdp-android<br/>
sdp-android提供了一个新的尺寸单位sdp（即：可伸缩dp）。此尺寸单位跟随屏幕大小变化，适配不同分辨率屏幕。本项目视图尺寸单位均采用sdp。sdp-android是一个开源框架。使使用sdp-android，开发人员可以不必关心不同屏幕分辨率下的屏幕界面缩放问题，sdp-android内部提供了几十个不同分辨率下的尺寸资源文件，只需要将布局内部的dp更换为对应的sdp，即可使应用程序在不同屏幕分辨率下的界面正常缩放显示。<br/>
# 八、RoundedImageView<br/>
本项目使用RoundedImageView实现圆角组件。RoundedImageView是一个开源框架，使用此框架，可以将图片很容易的变为圆形、椭圆、圆角矩形等等使用非常方便。
