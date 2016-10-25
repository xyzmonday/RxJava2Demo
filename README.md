# RxJava2Demo
Some practices for RxJava2.x

#### 该项目是参考ReactiveX官网给出了的RxJava2.x新特性集合自己对RxJava1.x使用的经验写的一些Demo 目主要目的是是自己尽快的熟悉2.x的新特性，并将这些特性运用到实际项目中来。当然每一个demo都有遗漏和不足，请大家自己查资料补充。另外读者需要对1.x有一定的了解。RxJava2.x新特性的官网 <https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0 。

* practice1 : 练习Observable和Observer。在2.x中，Observer的接口多出了一个onSubscribe方法，方法的参数是Disposable。Disposable接口可以看做1.x的Subscription接口，
作用是用来取消订阅。而Obserable的create方法也有一些变化。具体请看practice1的注释；

* practice2 : 练习Flowable-Subscriber。Flowable是RxJava2.x中新增的类，专门用于应对背压（Backpressure）问题，但这并不是RxJava2.x中新引入的概念。所谓背压，
即生产者的速度大于消费者的速度带来的问题，比如在Android中常见的点击事件，点击过快则会造成点击两次的效果。同样Subscriber接口相比于Rx1.x多出了一个onSubscribe方法，
传入的参数是Subscription对象，具体的用法请看注释的说明；

* practice3 : 测试Flowable的被压。注意ActionX，和FunctionX都支持抛出异常;

* practice4 : 练习subscribeWith方法(`注意Observable和Flowable基本上相似，所以都具有该方法` )。一般订阅Flowable，可以用subscribe()方法，但是2.x该方法如果传入
Subscriber对象不再返回Subscription，不也返回Disposable对象，不好控制其订阅关系。

* practice5 : 练习线程切换以及Transformer。一般而言我们会使用subscribeOn，observerOn配合来实现从子线程到主线程的切换。RxJava2.0也可以这样做。但是我们可以使用
compose操作符实现流的变换；

* practice6 : 练习SingleObservable和Completable。在2.x中的数据源包括了: Observable,Flowable,SingleObservable,Completable。SingleObservable，MaybeSource等。
Single的意思就是说订阅后只能够接收到一次(或者说只能够发射一个数据流);Completable与Single类似，只能接受到完成(onComplete)和错误(onError);

* practice7 : 练习Maybe。Maybe同时包括Single和Completable，这个数据源可能发射0或者1，以及一个错误的信号。由于它只能发射一个元素所以没有被压。另外如果有一
个信号，那么onSuccess()被回调，而onComplete不会回调;

* practice8 : 练习使用Subject,以及suject使用的注意事项；

* practice9 : 综合运用2.x封装多种RxBus;

* practice10 : 运用2.x和Retrofit2.x实现文件下载;


###运行结图片展示: