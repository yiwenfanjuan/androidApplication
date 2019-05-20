package com.example.myapplication.RxJava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityRxJavaStudyMergeOperatorBinding;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.BiConsumer;

import java.util.concurrent.Callable;

public class RxJavaStudyMergeOperatorActivity extends BaseActivity<ActivityRxJavaStudyMergeOperatorBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_study_merge_operator;
    }

    @Override
    protected void initUi() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void doClick(View view) {
        super.doClick(view);
        switch (view.getId()) {
            case R.id.btn_start_with:
                clickStartWith();
                break;
            case R.id.btn_start_with_array:
                clickStartWithArray();
                break;
            case R.id.btn_concat:
                clickConcat();
                break;
            case R.id.btn_concat_array:
                clickConcatArray();
                break;
            case R.id.btn_merge:
                clickMerge();
                break;
            case R.id.btn_merge_delay_error:
                clickMergeDelayError();
                break;
            case R.id.btn_zip:
                clickZip();
                break;
            case R.id.btn_combine_latest:
                clickCombineLatest();
                break;
            case R.id.btn_combine_latest_delay_error:
                alterCombineLatest();
                break;
            case R.id.btn_reduce:
                clickReduce();
                break;
            case R.id.btn_count:
                clickCount();
                break;
            case R.id.btn_collect:
                clickCollect();
                break;
        }
    }

    //点击startWith操作符
    private void clickStartWith() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(12);
        Flowable.just(1, 2, 3)
                .startWith(Flowable.just(6, 8, 7))
                .startWith(list)
                .startWith(30)
                .subscribe(
                        (element) -> Log.i(TAG, "startWith onNext : " + element)
                );

    }

    //点击startWithArray操作符
    private void clickStartWithArray() {
        Flowable.just(1, 2, 3)
                .startWithArray(6, 5, 7)
                .subscribe(
                        (element) -> Log.i(TAG, "startWithArray onNext : " + element)
                );
    }

    //点击concat操作符
    private void clickConcat() {
        //concat的参数列表中可以接收一个集合元素
        List<Publisher<? extends Integer>> list = new ArrayList<>();
        list.add(Flowable.just(2, 6));
        list.add(Flowable.just(6, 7));

        Flowable.concat(
                Flowable.just(1, 2, 3),
                Flowable.just(6, 5, 7)
        )
                .subscribe(
                        (element) -> Log.i(TAG, "concat onNext : " + element)
                );
    }

    //点击concatArray操作符
    private void clickConcatArray() {
        Flowable.concatArray(
                Flowable.just(1, 2, 6),
                Flowable.just(2, 3, 7)
        ).subscribe(
                (element) -> Log.i(TAG, "concatArray onNext : " + element)
        );
    }

    //点击merge操作符
    private void clickMerge() {
        Flowable.merge(
                Flowable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Flowable.intervalRange(4, 3, 1, 1, TimeUnit.SECONDS)
        ).subscribe(
                (element) -> Log.i(TAG, "merge onNext: " + element)
        );
    }

    //点击concatDelayError操作符
    private void clickMergeDelayError() {
        Flowable.mergeDelayError(
                Flowable.create(
                        (emitter) -> emitter.onError(new NullPointerException("test error")), BackpressureStrategy.BUFFER
                ),
                Flowable.intervalRange(0, 4, 1, 1, TimeUnit.SECONDS)
        ).subscribe(
                (element) -> Log.i(TAG, "mergeDelayError onNext: " + element),
                (error) -> Log.i(TAG, "mergeDelayError onError:" + error.getMessage())
        );
    }

    //点击zip操作符
    private void clickZip() {
        Flowable.zip(
                Flowable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Flowable.just("哈哈", "呵呵"),
                (num, str) -> {
                    return num + " is " + str;
                },
                true
        ).subscribe((element) -> Log.i(TAG, "zip onNext : " + element));
    }

    //点击combineLatest操作符
    private void clickCombineLatest() {
        Flowable.combineLatest(
                Flowable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS),
                Flowable.intervalRange(10, 5, 500, 2000, TimeUnit.MILLISECONDS),
                (num1, num2) -> {
                    return num1 + " and " + num2;
                }
        ).subscribe((element) -> Log.i(TAG, "combineLatest onNext : " + element),
                (error) -> Log.i(TAG, "combineLatest onError : " + error)
        );
    }

    //将combineLatest操作符的示例用combineLatestDelayError改写：
    private void alterCombineLatest(){
        Flowable.combineLatestDelayError(
                (sources) -> {
                    StringBuilder builder = new StringBuilder();
                    for(int i = 0; i < sources.length; i++){
                        builder.append(sources[i]).append(" and ");
                    }
                    if(builder.lastIndexOf(" and ") != -1){
                        builder.replace(builder.lastIndexOf(" and "),builder.length(),"");
                    }
                    return builder.toString();
                },
                Flowable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS),
                Flowable.intervalRange(10, 5, 500, 2000, TimeUnit.MILLISECONDS)
                ).subscribe((element) -> Log.i(TAG,"combineLatestDelayError onNext:" + element));
    }

    //点击reduce操作符
    private void clickReduce(){
        Flowable.just(1,2,3,5)
                .reduce(
                        (num1,num2) -> {
                            Log.i(TAG,"reduce num1 : "+num1+" ,num2:"+num2);
                            return num1 + num2;
                        }
                ).subscribe((element) -> Log.i(TAG,"reduce onNext : "+element));
    }

    //点击count操作符
    private void clickCount(){
        Flowable.just(1,2,3,5)
                .count()
                .subscribe((count) -> Log.i(TAG,"count onNext:" + count));
    }

    //点击collect操作符
    private void clickCollect(){
        Flowable.just(1,3,6)
                .collect(
                        new Callable<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> call() throws Exception {
                                return new ArrayList<>();
                            }
                        },
                        new BiConsumer<ArrayList<Integer>, Integer>() {
                            @Override
                            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                                integers.add(integer);
                            }
                        }
                )
                .subscribe((element) -> Log.i(TAG,"collect onNext : "+element));

        //lambda写法如下：
        Flowable.just(1,2,3)
                .collect(ArrayList::new,ArrayList::add)
                .subscribe((element) -> Log.i(TAG,"collect onNext : "+element));
    }
}
