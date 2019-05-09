package com.example.myapplication.RxJava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityRxJavaStudyFilterOperationBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Predicate;

public class RxJavaStudyFilterOperationActivity extends BaseActivity<ActivityRxJavaStudyFilterOperationBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_study_filter_operation;
    }

    @Override
    protected void initUi() {

    }

    @Override
    protected void initData() {
        //useFilter();
        //useTake();
        //useTakeLast();
        //useFirstElementOrLastElement();
        //useFirstOrLast();
        //firstOrLastElementNullError();
        //useElementAt();
        //useOfType();
        //useSkipOrSkipLast();
        useIgnoreElements();
    }

    //使用filter过滤出大于等于2的数据
    private void useFilter(){
        Flowable.just(1,2,3,4,0)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        //过滤出大于等于2的数据
                        if(integer >= 2){
                            return true;
                        }
                        return false;
                    }
                })
                .subscribe((num)->{
                    Log.i(TAG,"filter onNext:"+num);
                });
    }

    //使用take操作符限制发送数据的数量
    private void useTake(){
        //限制发送的数量
//        Flowable.interval(1, TimeUnit.SECONDS)
//                .take(5)//限制只能发送5个数据
//                .subscribe((num) -> {Log.i(TAG,"take限制发送的数量5个当前是:"+num);});

        //限制5秒内发送的数据
        Flowable.interval(500,TimeUnit.MILLISECONDS)
                .take(5, TimeUnit.SECONDS)
                .subscribe((num) -> {Log.i(TAG,"take限制发送5秒内的数据:" +num);});
    }

    //使用takeLast操作符筛选出最后的几个数据
    private void useTakeLast(){
        //筛选出最后3个元素
//        Flowable.just(1,2,4,6,899,8)
//                .takeLast(3)
//                .subscribe((num) -> {Log.i(TAG,"takeLast筛选最后的3个元素:"+num);});

        //筛选出最后三秒发送的数据
        Flowable.intervalRange(100,20,0,1,TimeUnit.SECONDS)
                .takeLast(3,TimeUnit.SECONDS)
                .subscribe((num) -> { Log.i(TAG,"takeLast筛选出最后三秒发送的数据:"+num);});
    }

    //使用firstElement和lastElement获取第一个/最后一个元素
    private void useFirstElementOrLastElement(){
        //获取第一个元素
        Flowable.just(1,2,3)
                .firstElement()
                .subscribe((num) -> {Log.i(TAG,"firstElement:"+num);});

        //获取最后一个元素
        Flowable.just(1,3,4)
                .lastElement()
                .subscribe((num) -> {Log.i(TAG,"lastElement:"+num);});
    }

    //使用first/last操作符设置当被观察者不发送任何数据时的默认值
    private void useFirstOrLast(){
        Flowable.empty()
                .first("w")
                .subscribe((str) -> {Log.i(TAG,"first : "+str);});

        Flowable.empty()
                .last("g")
                .subscribe((str) -> Log.i(TAG,"last:"+str));
    }

    //使用firstOrError和lastOrError当元素为空时抛出异常
    private void firstOrLastElementNullError(){
        Flowable.empty()
                .firstOrError()
                .subscribe(
                        (o) -> Log.i(TAG,"firstOrError : "+ o),
                        (error) -> Log.i(TAG,"firstOrError error:"+error.getMessage())
                );

        Flowable.just(1)
                .lastOrError()
                .subscribe(
                        (o) -> Log.i(TAG,"lastOrError : "+ o),
                        (error) -> Log.i(TAG,"lastOrError error : "+error.getMessage())
                );
    }

    //使用elementAt/elementAtOrError来指定发射第几个元素
    private void useElementAt(){
        Flowable.just("q","w","r","t")
                .elementAt(2)//指定发射索引为2的元素，如果不存在则直接完成
                .subscribe(element -> Log.i(TAG,"elementAt :" +element));

        Flowable.just(1,2,3)
                .elementAt(1000,5)//指定索引为1000的元素，如果不存在则发射5
                .subscribe(element -> Log.i(TAG,"elementAt : "+element));

        //如果希望越界后抛出异常，使用elementAtOrError
        Flowable.just(1,2,4)
                .elementAtOrError(1000)
                .subscribe(
                        element -> Log.i(TAG,"elementAtOrError:"+element),
                        error -> Log.i(TAG,"elementAtOrError:"+error.getMessage())
                );
    }

    //使用ofType操作符筛选出指定类型的数据进行发送
    private void useOfType(){
        Flowable.just("1",232,"哈哈哈")
                .ofType(Integer.class)
                .subscribe(
                        element -> Log.i(TAG,"ofType : "+element)
                );
    }

    //使用skip/skipLast跳过若干个元素
    private void useSkipOrSkipLast(){
        Flowable.just(1,3,4,7,7,5,4)
                .skip(3)
                .subscribe(
                        element -> Log.i(TAG,"skip:"+element)
                );

        //使用skipLast跳过最后2秒钟发送的元素
        Flowable.intervalRange(100,10,0,1,TimeUnit.SECONDS)
                .skipLast(2,TimeUnit.SECONDS)
                .subscribe(element -> Log.i(TAG,"skipLast : "+ element));
    }

    //使用ignoreElements将当前的被观察者转换为Completable类型的被观察者:
    private void useIgnoreElements(){
        Flowable.just(1,2,3,4)
                .ignoreElements()
                .subscribe(() -> Log.i(TAG,"ignoreElements onComplete"),
                        (error) -> Log.i(TAG,"ignoreElements onError:" + error.getMessage())
                );
    }

}
