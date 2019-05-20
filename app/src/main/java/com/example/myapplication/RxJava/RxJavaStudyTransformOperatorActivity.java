package com.example.myapplication.RxJava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityRxJavaStudyTransformOperatorBinding;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RxJavaStudyTransformOperatorActivity extends BaseActivity<ActivityRxJavaStudyTransformOperatorBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_study_transform_operator;
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
            case R.id.btn_map:
                clickMap();
                break;
            case R.id.btn_flat_map:
                clickFlatMap();
                break;
            case R.id.btn_flat_map_iterable:
                clickFlatMapIterable();
                break;
            case R.id.btn_concat_map:
                clickConcatMap();
                break;
            case R.id.btn_switch_map:
                clickSwitchMap();
                break;
            case R.id.btn_cast:
                clickCast();
                break;
            case R.id.btn_scan:
                clickScan();
                break;
            case R.id.btn_buffer:
                clickBuffer();
                break;
            case R.id.btn_to_list:
                clickToList();
                break;
            case R.id.btn_to_sorted_list:
                clickToSortedList();
                break;
            case R.id.btn_group_by:
                clickGroupBy();
                break;
            case R.id.btn_to_map:
                clickToMap();
                break;
        }
    }

    //map操作符
    private void clickMap() {
        Flowable.just(1, 2, 3)
                .map(item -> "this item is " + item)
                .subscribe((element) -> Log.i(TAG, "map onNext : " + element));
    }

    //flatMap操作符
    private void clickFlatMap() {
        Observable.intervalRange(1, 3, 0, 1, TimeUnit.SECONDS)
                .flatMap(
                        (item) -> Observable.create((emitter) -> emitter.onNext("this item is " + item))
                )
                .subscribe(ele -> Log.i(TAG, String.valueOf(ele)));
    }

    //flatMapIterable操作符
    private void clickFlatMapIterable() {
        Flowable.interval(0, 1, TimeUnit.SECONDS)
                .takeWhile((item) -> item < 5)
                .flatMapIterable(
                        (Function<? super Long, ? extends Iterable<?>>) ((item) -> Arrays.asList("h", item))
                )
                .subscribe((element) -> Log.i(TAG, "flatMapIterable onNext : " + element));
    }

    //concatMap操作符
    private void clickConcatMap() {
        Flowable.just(1, 2, 3)
                .concatMap((item) -> Flowable.just("a", item))
                .subscribe((element) -> Log.i(TAG, "concatMap onNext : " + element));
    }

    //switchMap操作符
    private void clickSwitchMap() {
        Flowable.just(1, 2, 3)
                .switchMap((item) -> Flowable.timer(1, TimeUnit.SECONDS)
                        .map((longValue) -> longValue.intValue())
                )
                .subscribe((element) -> Log.i(TAG, "switchMap onNext : " + element));
    }

    //cast操作符
    private void clickCast() {
        Flowable.just(1, 2, 3, "哈哈哈")
                .cast(Number.class)
                .subscribe((element) -> Log.i(TAG, "cast onNext : " + element),
                        (error) -> Log.i(TAG, "cast onError : " + error.getMessage())
                );
    }

    //scan操作符
    private void clickScan() {
        Flowable.just(1, 2, 3, 4)
                .scan((last, item) -> {
                    Log.i(TAG, "scan last is " + last);
                    Log.i(TAG, "scan item is " + item);
                    return last + item;
                }).subscribe(element -> Log.i(TAG, "scan onNext : " + element));
    }

    //buffer操作符
    private void clickBuffer() {
        Flowable.just(1, 2, 3, 4)
                .buffer(3)
                .subscribe((element) -> Log.i(TAG, "buffer onNext : " + element));
    }

    //toList操作符
    private void clickToList() {
        Flowable.just("哈哈哈", 1, 2, 3, "傻逼")
                .toList()
                .subscribe((element) -> Log.i(TAG, "toList onNext : " + element));
    }

    //toSortedList操作符
    private void clickToSortedList() {
        Flowable.just("哈哈哈", 1, 2, 3, "傻逼")
                .toSortedList(
                        (item1, item2) -> {
                            try {
                                if (Integer.parseInt(item1.toString()) > Integer.parseInt(item2.toString())) {
                                    return -1;
                                }
                            } catch (NumberFormatException e) {
                                return -1;
                            }
                            return 1;
                        }
                )
                .subscribe((element) -> Log.i(TAG, "toSortedList onNext : " + element),
                        (error) -> Log.i(TAG, "toSortedList onError : " + error.getMessage())
                );
    }

    //groupBy操作符
    private void clickGroupBy(){
        Flowable.just(1,2,3,4,5)
                .groupBy((item1) -> (item1 > 2 ? "A组" : "B组"))
                .subscribe((groupedFlowable) -> {
                   groupedFlowable.subscribe((element) -> Log.i(TAG,groupedFlowable.getKey() + " : " + element));
                });
    }

    //toMap操作符
    private void clickToMap(){
        Flowable.just(1,2,3,4,5)
                .toMap((keyItem) -> keyItem > 3 ? "largeKey" : "smallKey",
                        (valueItem) -> " : "+(valueItem+ 10)
                        )
                .subscribe((element) -> Log.i(TAG,"toMap onNext : "+ element));
    }
}
