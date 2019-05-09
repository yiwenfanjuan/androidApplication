package com.example.myapplication.RxJava;

import android.util.Log;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityRxJavaStudy2Binding;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

/**
 * @description：RxJava简介
 * @author：yiwen
 * @date：2019/4/22
 * @remark：内容来源：https://maxwell-nc.github.io/android/rxjava2-1.html
 */
public class RxJavaIntroductionActivity extends BaseActivity<ActivityRxJavaStudy2Binding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_study2;
    }

    @Override
    protected void initUi() {

    }

    @Override
    protected void initData() {
        //test();
        //useJust();
        //useFromArray();
        //useEmpty();
        //useError();
        //useNever();
        //useFromIterable();
        //useTimer();
        //useInterval();
        //useIntervalRange();
        //useRange();
        useDefer();
    }

    //创建FlowableSubscriber
    private void test() {
        final FlowableSubscriber<String> subscriber = new FlowableSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);//请求多少事件，这里表示不限制
                Log.i(TAG, "FlowableSubscriber<String> onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "FlowableSubscriber<String> onNext(String s)" + s);
            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG, "FlowableSubscriber<String> onError(Throwable t)");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "FlowableSubscriber<String> onComplete()");
            }
        };


        //创建一个被观察者
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                Log.i(TAG, "Flowable subscribe(FlowableEmitter<String> emitter)");
                //订阅观察者时执行的操作
                emitter.onNext("test1");
                emitter.onNext("test2");
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        //通过subscribe来实现订阅
        flowable.subscribe(subscriber);

        //使用Consumer作为观察者
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "Consumer<String> accept(String s) : " + s);
            }
        };
        //通过subscribe来实现订阅
        flowable.subscribe(consumer);

        //使用subscribe完整的重载方法：
        flowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {//相当于onNext
                Log.i(TAG, "accept(String s) with onNext: " + s);
            }
        }, new Consumer<Throwable>() {//相当于onError
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, "accept(Throwable) with onError : " + throwable.getMessage());
            }
        }, new Action() {//相当于onComplete,注意这里是Action
            @Override
            public void run() throws Exception {
                Log.i(TAG, "run() with onComplete ");
            }
        }, new Consumer<Subscription>() {//相当于onSubscribe
            @Override
            public void accept(Subscription subscription) throws Exception {
                subscription.request(Long.MAX_VALUE);
                Log.i(TAG, "accept(Subscription subscription :) with onSubscribe:" + subscription);
            }
        });

        //创建一个Observable被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.i(TAG, "Observable subscribe(ObservableEmitter<String> emitter)");
            }
        });

        //创建一个Observer观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "Observer onSubscribe(Disposable d)");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "Observer onNext(String s) :" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "Observer onError : ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Observer onComplete");
            }
        };
        //绑定订阅关系
        observable.subscribe(observer);


    }

    //使用Single和SingleObserver
    private void useSingle() {
        //创建一个Single被订阅者
        Single<String> single = Single.create(new SingleOnSubscribe() {
            @Override
            public void subscribe(SingleEmitter emitter) throws Exception {
                Log.i(TAG, "Single subscribe(SingleEmitter emitter)");
                emitter.onSuccess("test");
                emitter.onSuccess("test2");//错误写法，重复调用也不会处理
            }
        });
        //创建SingleObserver观察者
        SingleObserver<String> singleObserver = new SingleObserver<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "SingleObserver onSubscribe(Disposable)");
            }

            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "SingleObserver onSuccess : " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "SingleObserver onError: " + e.getMessage());
            }
        };

        single.subscribe(singleObserver);

        //使用Actions简化Observer
        single.subscribe(new BiConsumer<String, Throwable>() {
            //重写成功和失败两个参数额方法
            @Override
            public void accept(String s, Throwable throwable) throws Exception {
                Log.i(TAG, "BiConsumer<String,Throwable> accept(Strings s,Throwable throwable) success:" + s + ",fail:" + throwable.getMessage());
            }
        });

        //将Single转换为Flowable或者Observable
        single.toFlowable();
        single.toObservable();
    }

    //使用Completable或CompletableObserver
    private void useCompletable() {
        //创建被观察者Completable
        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                Log.i(TAG, "Completable subscribe(CompletableEmitter emitter) : " + emitter);
                emitter.onComplete();//单一Complete或者onError;
            }
        });
        //创建观察者CompletableObserver
        CompletableObserver completableObserver = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "CompletableObserver onSubscribe(Disposable d) : " + d);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "CompletableObserver onComplete()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "CompletableObserver onError(Throwable e)" + e);
            }
        };
        //绑定订阅关系
        completable.subscribe(completableObserver);

        //可以使用简化Observer的Actions,接收一个和两个参数的Actions
        completable.subscribe(new Action() {
            @Override
            public void run() throws Exception {//相当于onComplete
                Log.i(TAG, "Completable Action:");
            }
        }, new Consumer<Throwable>() {//相当于onError
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, "Completable Consumer accept:" + throwable.getMessage());
            }
        });

    }

    //使用Maybe
    private void useMaybe() {
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                int i = new Random().nextInt();
                Log.i(TAG, "Maybe subscribe(MayEitter<String> emitter) : " + i);
                if (i > 100) {
                    emitter.onSuccess("this is " + i);//发送一个和数据的情况，调用onSuccess或者onError，不需要再调用onComplete(调用了也不会触发onComplete回调方法)
                } else {
                    emitter.onComplete();//不需要发送数据的情况，调用onComplete或者OnError
                }
            }
        });
        MaybeObserver<String> maybeObserver = new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "MaybeObserver onSubscribe(Disposable):" + d);
            }

            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "MaybeObserver onSuccess(String) :" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "MaybeObserver onError(Throwable)" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "MaybeObserver onComplete:");
            }
        };
        //绑定订阅关系
        maybe.subscribe(maybeObserver);

        //使用Actions简化Observer
        maybe.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "maybe Consumer<String> with onSuccess:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, "maybe Consumer<Throwable> with onError : " + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.i(TAG, "maybe Action with onComplete");
            }
        });
    }


    //使用just操作符
    private void useJust() {
        Flowable.just("test", "test2")
                .subscribe((str) -> Log.i(TAG, "just:" + str), (e) -> Log.i(TAG, "出现异常：" + e.getMessage()), () -> Log.i(TAG, "onComplete"));

    }

    //使用fromArray操作符
    private void useFromArray() {
        Flowable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 12)
                .subscribe(
                        (num) -> Log.i(TAG, "fromArray：" + num),
                        (exception) -> Log.i(TAG, "fromArray:" + exception.getMessage()),
                        () -> {
                            Log.i(TAG, "fromArray:complete");
                        });

        int[] nums = new int[]{10, 20, 30};
        Observable.fromArray(new int[]{10, 20, 30})
                .subscribe((n) -> Log.i(TAG, "fromArray:" + n));
    }

    //使用empty操作符
    private void useEmpty() {
        Flowable.empty()
                .subscribe((o) -> Log.i(TAG, "empty:" + o.toString()),
                        e -> Log.i(TAG, "empty exception:" + e.getMessage()),
                        () -> Log.i(TAG, "empty onComplete")
                );
    }

    //使用error操作符
    private void useError() {
        Flowable.error(new RuntimeException("error"))
                .subscribe(
                        (object) -> Log.i(TAG, "error:" + object.toString()),
                        (exception) -> Log.i(TAG, "error exception:" + exception.getMessage()),
                        () -> Log.i(TAG, "error onComplete")
                );
    }

    //使用never操作符
    private void useNever() {
        Flowable.never()
                .subscribe(
                        (object) -> Log.i(TAG, "never:" + object.toString()),
                        (exception) -> Log.i(TAG, "never exception:" + exception),
                        () -> Log.i(TAG, "never onComplete")
                );
    }

    //使用fromIterable
    private void useFromIterable() {
        List<String> items = new ArrayList<String>();
        items.add("123");
        items.add("哈哈");
        items.add("滚");
        Flowable.fromIterable(items)
                .subscribe(
                        (str) -> Log.i(TAG, "fromIterable:" + str),
                        (exception) -> Log.i(TAG, "fromIterable:" + exception.getMessage()),
                        () -> Log.i(TAG, "fromIterable onComplete")
                );
    }

    //使用timer
    private void useTimer() {
        Flowable.timer(2, TimeUnit.SECONDS)
                .subscribe((time) -> {
                            Log.i(TAG, "timer onNext :" + time);
                        },
                        (exception) -> {
                            Log.i(TAG, "timer onError:" + exception.getMessage());
                        },
                        () -> {
                            Log.i(TAG, "timer onComplete");
                        }
                );
    }

    //使用interval
    private void useInterval(){
        Flowable.interval(2,1, TimeUnit.SECONDS)
                .subscribe(
                        (time) -> Log.i(TAG,"interval onNext :"+time),
                        (exception) -> Log.i(TAG,"interval onError:"+ exception.getMessage()),
                        () -> Log.i(TAG,"interval onComplete:")
                );
    }

    //使用intervalRange操作符
    private void useIntervalRange(){
        Flowable.intervalRange(10,5,0,2, TimeUnit.SECONDS)
                .subscribe(
                        (time) -> Log.i(TAG,"intervalRange onNext:"+time),
                        (exception) -> Log.i(TAG,"intervalRange onError :" + exception.getMessage()),
                        () -> Log.i(TAG,"intervalRange onComplete")
                 );
    }

    //使用range/rangeLong操作符
    private void useRange(){
        Flowable.range(100,6)
                .subscribe(
                        (count) -> Log.i(TAG,"range onNext:"+count),
                        (exception) -> Log.i(TAG,"range onError :"+exception.getMessage()),
                        () -> Log.i(TAG,"range onComplete")
                );
    }

    //使用defer操作符
    private void useDefer(){
                        Flowable<String> flowable = Flowable.defer(new Callable<Publisher<? extends String>>() {
                            @Override
                            public Publisher<? extends String> call() throws Exception {
                                return Flowable.just("1","2");
                            }
                        });

                        //第一个订阅者：
                        flowable.subscribe((str) -> Log.i(TAG,"defer first observer:"+ str));

                        //第二个订阅者
                        flowable.subscribe((str) -> Log.i(TAG,"defer second observer:" + str));
    }

}
