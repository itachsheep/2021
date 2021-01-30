package com.tao.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG,"onCreate ");
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("CheckResult")
    public void on_bt_start(View view) {
        LogUtils.d(TAG,"on_bt_start ");
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.d(TAG, "subscribe");
                e.onNext(111);
            }
        });

        integerObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d(TAG, "onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d(TAG, "onComplete");
            }
        });


        /**
         * RxJava 2.x 也为我们保留了简化订阅方法，我们可以根据需求，进行相应的简化订阅，只不过传入对象改为了 Consumer。
         * Consumer 即消费者，用于接收单个值
         */
        /*integerObservable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.d(TAG,"accept " + integer);
            }
        });*/
    }

    @SuppressLint("CheckResult")
    public void on_bt_qiehuan(View view) {

        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.d(TAG, "subscribe ");
                e.onNext(9999);
            }
        });

        integerObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG,"accept1 :" + integer);
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG,"accept3 :" + integer);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG,"accept2 : " + integer);
                    }
                });

    }
}