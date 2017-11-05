# dingdang
一个语音交互的demo示例
> 主要使用了reactiveX（github开源，star数量很多，目前还是基础使用）的观察者模式来解耦各个组件，各功能模块都是独立的，可根据需要执行添加。

第一个版本，还不太熟悉一些音频的处理，监听是隔断时间就去听一次，然后使用百度的语音识别为文字，再根据文字的具体内容调用不同的service,最后根据service的返回值（当前主要是文本），再次调用百度的语音合成api合成语音播报出来。因为当前百度语音平台提供免费的api,并承若永久免费因此每个人都可以申请接口服务。因为Constants.java涉及到我自己的一些信息，故以下面的形式给出：

### Constants.java 
```java
package cn.kotocean.heart;

public class Constants {
    public static final String appID="你的百度语音appID";
    public static final String apiKey="你的百度语音apikey";
    public static final String secretKey="你的secret";
}
```
