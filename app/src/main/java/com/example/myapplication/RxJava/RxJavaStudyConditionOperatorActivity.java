package com.example.myapplication.RxJava;

import android.util.Log;
import android.view.View;

import com.project.baselib.ui.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityRxJavaStudyConditionOperatorBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * @description：RxJava2条件操作符
 * @author：yiwen
 * @date：2019/5/15
 * @remark：条件操作符主要用于控制被观察者开始，停止，跳过的各种操作
 */
public class RxJavaStudyConditionOperatorActivity extends BaseActivity<ActivityRxJavaStudyConditionOperatorBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_study_condition_operator;
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
            case R.id.btn_all:
                clickAll();
                break;
            case R.id.btn_amb_array:
                clickAmbArray();
                break;
            case R.id.btn_contains:
                clickContains();
                break;
            case R.id.btn_any:
                clickAny();
                break;
            case R.id.btn_is_empty:
                clickIsEmpty();
                break;
            case R.id.btn_default_if_empty:
                clickDefaultIfEmpty();
                break;
            case R.id.btn_switch_if_empty:
                clickSwitchIfEmpty();
                break;
            case R.id.btn_sequence_equal:
                clickSequenceEqual();
                break;
            case R.id.btn_take_until:
                clickTakeUntil();
                break;
            case R.id.btn_take_while:
                clickTakeWhile();
                break;
            case R.id.btn_skip_until:
                clickSkipUntil();
                break;
            case R.id.btn_skip_while:
                clickSkipWhile();
                break;
        }
    }

    //all操作符
    private void clickAll() {
        Flowable.just(1, 2, 3, 5, 6)
                .all(
                        (num) -> {
                            return num > 1;
                        }
                )//返回一个Single<Bool>
                .subscribe((result, error) -> Log.i(TAG, "all onNext : " + result));
    }

    //ambArray操作符
    private void clickAmbArray() {
        Flowable.ambArray(
                Flowable.timer(1, TimeUnit.SECONDS),
                Flowable.just(1, 2, 3)
        ).subscribe((element) -> Log.i(TAG, "ambArray onNext : " + element));
    }

    //contains操作符
    private void clickContains(){
        Flowable.just("哈哈哈","12","黑嘿嘿")
                .contains(12)
                .subscribe((result) -> Log.i(TAG,"contains onNext : "+ result));
    }

    //any操作符
    private void clickAny(){
        Flowable.just(1,2,"哈哈",true)
                .any(
                        (element) -> element instanceof Boolean
                ).subscribe((result) -> Log.i(TAG,"any onNext : "+result));
    }

    //isEmpty操作符
    private void clickIsEmpty(){
        Flowable.just(1)
                .isEmpty()
                .subscribe((result) -> Log.i(TAG,"isEmpty onNext : "+result));
    }

    //defaultIfEmpty操作符
    private void clickDefaultIfEmpty(){
        Flowable.empty()
                .defaultIfEmpty("default")
                .subscribe((element) -> Log.i(TAG,"defaultIfEmpty onNext : "+element));
    }

    //switchIfEmpty操作符
    private void clickSwitchIfEmpty(){
        Flowable.empty()
                .switchIfEmpty(Flowable.just(1,2,3,4))
                .subscribe((element) -> Log.i(TAG,"switchIfEmpty onNext : "+ element));
    }

    //sequenceEqual操作符
    private void clickSequenceEqual(){
        Flowable.sequenceEqual(
                Flowable.just(0,1,2,3),
                Flowable.intervalRange(0,4,2,1,TimeUnit.SECONDS),
                (element1,element2) -> {
                    Log.i(TAG,"element1 -> "+element1 + ",element2 -> "+ element2);
                    return element1.longValue() == element2.longValue();
                }
        ).subscribe((element) -> Log.i(TAG,"sequenceEqual onNext : "+ element));
    }

    //takeUntil操作符
    private void clickTakeUntil(){
        /*
        Flowable.interval(0,1,TimeUnit.SECONDS)
                .takeUntil((number) -> number > 2)
                .subscribe((element) -> Log.i(TAG,"takeUntil onNext : " + element));
                */
        Flowable.interval(0,1,TimeUnit.SECONDS)
                .takeUntil(Flowable.timer(2,TimeUnit.SECONDS))
                .subscribe((element) -> Log.i(TAG,"takeUntil onNext : " + element));

    }

    //takeWhile操作符
    private void clickTakeWhile(){
        Flowable.interval(1,TimeUnit.SECONDS)
                .takeWhile((element) -> element < 5)
                .subscribe((element) -> Log.i(TAG,"takeWhile onNext : "+element));
    }

    //skipUntil操作符
    private void clickSkipUntil(){
        Flowable.intervalRange(0,10,0,1,TimeUnit.SECONDS)
                .skipUntil(Flowable.timer(3,TimeUnit.SECONDS))
                .subscribe((element) -> Log.i(TAG,"skipUntil onNext : "+element));
    }

    //skipWhile操作符
    private void clickSkipWhile(){
        Flowable.intervalRange(0,10,0,1,TimeUnit.SECONDS)
                .skipWhile((element) -> element < 5)
                .subscribe((element) -> Log.i(TAG,"skipWhile onNext : " + element));
    }
}
