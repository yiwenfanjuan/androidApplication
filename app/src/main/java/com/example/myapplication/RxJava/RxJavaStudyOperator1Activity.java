package com.example.myapplication.RxJava;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityRxJavaStudyOperator1Binding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxJavaStudyOperator1Activity extends BaseActivity<ActivityRxJavaStudyOperator1Binding> {

    private String result;
    private Disposable mDisposable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_study_operator1;
    }

    @Override
    protected void initUi() {

    }

    @Override
    protected void initData() {

    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.btn_operator_create:
                mBinding.setOperateInfo(getString(R.string.create_operate_info));
                mBinding.setCommandInfo("产生一个Observable被观察者对象");
                clickCreateOperate();
                break;
            case R.id.btn_operator_map:
                mBinding.setOperateInfo(getString(R.string.map_operate_info));
                mBinding.setCommandInfo("将发射事件的每一个事件前面都加上一串固定的字符串");
                clickMapOperate();
                break;
            case R.id.btn_operator_zip:
                mBinding.setOperateInfo(getString(R.string.zip_operate_info));
                mBinding.setCommandInfo("合并，两两配对");
                clickZipOperate();
                break;
            case R.id.btn_operator_concat:
                mBinding.setOperateInfo(getString(R.string.concat_operate_info));
                mBinding.setCommandInfo("将两个发射器合并成一个发射器");
                clickConcatOperate();
                break;
            case R.id.btn_operator_flat_map:
                mBinding.setOperateInfo(getString(R.string.flat_map_operate_info));
                mBinding.setCommandInfo("将一个发射器拆分做一些操作之后再合并成一个新的发射器");
                clickFlatMapOperate();
                break;
            case R.id.btn_operator_concat_map:
                mBinding.setOperateInfo(getString(R.string.concat_map_operate_info));
                clickConcatMapOperate();
                break;
            case R.id.btn_operator_distinct:
                mBinding.setOperateInfo(getString(R.string.distinct_operate_info));
                clickDistinctOperate();
                break;
            case R.id.btn_filter_operator:
                mBinding.setOperateInfo(getString(R.string.filter_operate_info));
                clickFilterOperate();
                break;
            case R.id.btn_buffer_operator:
                mBinding.setOperateInfo(getString(R.string.buffer_operate_info));
                clickBufferOperate();
                break;
            case R.id.btn_timer_operator:
                mBinding.setOperateInfo(getString(R.string.timer_operate_info));
                clickTimerOperate();
                break;
            case R.id.btn_interval_operator:
                mBinding.setOperateInfo(getString(R.string.interval_operate_info));
                clickIntervalOperate();
                break;
            case R.id.btn_doOnNext_operator:
                mBinding.setOperateInfo(getString(R.string.doOnNext_operate_info));
                clickDoNextOperate();
                break;
            case R.id.btn_skip_operator:
                mBinding.setOperateInfo(getString(R.string.skip_operate_info));
                clickSkipOperate();
                break;
            case R.id.btn_operator_take:
                mBinding.setOperateInfo(getString(R.string.take_operate_info));
                clickTakeOperate();
                break;
            case R.id.btn_operator_just:
                mBinding.setOperateInfo(getString(R.string.just_operate_info));
                clickJustOperate();
                break;
        }
    }

    //点击create操作符
    private void clickCreateOperate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                Log.i(TAG, "ObservableEmitter 1 \n");
                emitter.onNext(1);
                Log.i(TAG, "ObservableEmitter 2 \n");
                emitter.onNext(2);
                Log.i(TAG, "ObservableEmitter 3 \n");
                emitter.onNext(3);
                //emitter.onNext(4/0);
                emitter.onComplete();
            }
        }).subscribe(
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe:" + d);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext :" + integer);
                        result += integer.toString();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                        mBinding.setResultInfo(result);
                    }
                }
        );

    }

    //点击Map操作符
    private void clickMapOperate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(100);
                emitter.onNext(200);
                emitter.onNext(300);
                emitter.onComplete();
            }
        }).map(
                new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return "This is " + integer;
                    }
                }
        ).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, d.toString());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Map Observer onComplete");
            }
        });

    }

    //点击Zip操作符
    private void clickZipOperate() {
        String result = "";
        mBinding.setResultInfo(result);
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "Zip Consmer : " + s);
            }
        });
    }

    private Observable<String> getStringObservable() {

        Observable<String> stringObservable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        if (!emitter.isDisposed()) {
                            emitter.onNext("A");
                            Log.i(TAG, "String emitter A");
                            emitter.onNext("B");
                            Log.i(TAG, "String emitter B");
                            emitter.onNext("C");
                            Log.i(TAG, "String emitter C");
                        }
                    }
                }
        );
        return stringObservable;
    }

    private Observable<Integer> getIntegerObservable() {
        Observable<Integer> integerObservable = Observable.create(
                new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(1);
                            Log.i(TAG, "Integer emitter 1");
                            emitter.onNext(2);
                            Log.i(TAG, "Integer emitter 2");
                            emitter.onNext(3);
                            Log.i(TAG, "Integer emitter 3");
                            emitter.onNext(4);
                            Log.i(TAG, "Integer emitter 4");
                            emitter.onNext(5);
                            Log.i(TAG, "Integer emitter 5");
                        }
                    }
                }
        );
        return integerObservable;
    }

    //点击Concat操作符
    private void clickConcatOperate() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "Concat Operate accept:" + integer);
                    }
                });
    }

    //点击flatMap操作符
    private void clickFlatMapOperate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(100);
                emitter.onNext(200);
                emitter.onNext(300);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "flatMap accept:" + s + "\n");
                    }
                });
    }

    //点击concatMap操作符
    private void clickConcatMapOperate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(100);
                emitter.onNext(200);
                emitter.onNext(300);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "flatMap accept:" + s + "\n");
                    }
                });
    }

    //点击distinct操作符
    private void clickDistinctOperate() {
        Observable.just(1, 1, 1, 2, 3, 4, 5)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "distinct accept:" + integer);
                    }
                });
    }

    //点击filter操作符
    private void clickFilterOperate() {
        Observable.just("哈哈哈", "奥什", "wqdad", "额达伟大", "", "100")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        if (!TextUtils.isEmpty(s) && s.length() > 2) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "filter accept:" + s);
                    }
                });
    }

    //点击buffer操作符
    private void clickBufferOperate() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.i(TAG, "buffer size:" + integers.size());
                        for (int i : integers) {
                            Log.i(TAG, "buffer accept:" + i);
                        }
                    }
                });
    }

    //点击timer操作符
    private void clickTimerOperate() {
        final long startTime = System.currentTimeMillis();
        Observable.timer(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        long acceptTime = System.currentTimeMillis();
                        Log.i(TAG, "acceptTime - startTime = " + (acceptTime - startTime));
                    }
                });
    }

    //点击interval操作符
    private void clickIntervalOperate() {
        /*
        Observable.interval(0,2000,TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG,"interval :" + aLong);
                    }
                });
        */
        Observable.interval(3, 2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "interval Observer  onSubscribe(Disposable d)");
                        if (d != null) mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i(TAG, "interval Observer onNext:" + aLong);
                        if (aLong > 10) {
                            mDisposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //点击doNext操作符
    private void clickDoNextOperate() {
        Observable.just("first", "second")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String aLong) throws Exception {
                        Log.i(TAG, aLong + "已保存");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG,"doOnNext accept:"+s);
                    }
                });
    }

    //点击skip操作符
    private void clickSkipOperate(){
        Observable.just(1,2,3,4,5)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,"skip accept:"+integer);
                    }
                });
    }

    //点击take操作符
    private void clickTakeOperate(){
        Observable.just(100,200,300,400)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,"take accept:"+integer);
                    }
                });
    }

    //点击just操作符
    private void clickJustOperate(){
        Observable.just(1,2,3,4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,"just accept:"+integer);
                    }
                });
    }
}
