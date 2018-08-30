package com.songcw.basecore.mvp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.jakewharton.rxbinding2.view.RxView;
import com.songcw.basecore.R;
import com.songcw.basecore.event.JumpConfigBaseUrlEvent;
import com.songcw.basecore.http.IInterceptor;
import com.songcw.basecore.http.InterceptorUtil;
import com.songcw.basecore.util.statusbar.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by Sprout on 2018/8/27
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    public String TAG = this.getClass().getSimpleName();
    public List<BaseSection> sections = new ArrayList<>();
    private Disposable disposable;
    private IInterceptor interceptor;

    private LinearLayout llRoot;    //根布局
    private View actionBar;         //标题栏布局
    private View contentView;       //内容布局
    private RelativeLayout rlBack;  //返回箭头
    private TextView tvTitle;       //标题

    public void addSection(BaseSection section) {
        sections.add(section);
        section.addDefaultLifecycle();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //设置状态栏字体颜色为黑色
//        StatusBarUtil.statusBarLightMode(this);
        //设置状态栏背景颜色
        StatusBarUtil.setStatusBarColor(this, R.color.color_statusbar);
        //设置根布局
        setContentView(R.layout.activity_root);
        //设置标题栏
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        addActionBar(setMyActionBar());
        //设置内容布局
        addContentView(View.inflate(this, setContentLayout(), null));
        RxBus.get().register(this);
        if (interceptor == null) interceptor = InterceptorUtil.defaultLoadingInterceptor(this);
        addSections();
        for (BaseSection section : sections) section.onCreate(savedInstanceState);
    }

    protected abstract void addSections();

    /**
     * 添加actionbar
     */
    private void addActionBar(View view) {
        if (llRoot != null && view != null) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            actionBar = view;
            llRoot.addView(view, lp);
            //默认隐藏, 只有当setTitle()时才显示
            actionBar.setVisibility(View.GONE);
        }
    }

    /**
     * 添加contentview
     */
    private void addContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (llRoot != null && view != null) {
            contentView = view;
            llRoot.addView(view, lp);
        }
    }

    /**
     * 设置内容布局
     */
    protected abstract @LayoutRes
    int setContentLayout();

    /**
     * 获取acitonbar
     */
    protected View setMyActionBar() {
        View view = getLayoutInflater().inflate(R.layout.actionbar_default_layout, null);
        rlBack = (RelativeLayout) view.findViewById(R.id.image_left);
        tvTitle = (TextView) view.findViewById(R.id.textView_title);
        registerConfigBaseUrl();
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
            }
        });
        return view;
    }

    /**
     * 注册配置根地址监听
     */
    private void registerConfigBaseUrl() {
        Observable<Object> observable = RxView.clicks(tvTitle).share();
        //表示连续点击3次时, 才会发送给下游
        disposable = observable.buffer(observable.debounce(800, TimeUnit.MILLISECONDS))
                .filter(new Predicate<List<Object>>() {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull List<Object> objects) throws Exception {
                        return objects.size() == 3;   //表示连续点击3次时, 才会发送给下游
                    }
                }).observeOn(AndroidSchedulers.mainThread()).compose(this.<List<Object>>bindToLifecycle())
                .subscribe(new Consumer<List<Object>>() {
                    @Override
                    public void accept(List<Object> objects) throws Exception {
                        // to = MainActivity
                        RxBus.get().post(new JumpConfigBaseUrlEvent());
                    }
                });
    }

    /**
     * 后退箭头点击事件, 默认finish当前界面
     */
    public void onBackClick() {
        finish();
    }

    /**
     * 设置title
     */
    public void setTitle(String title) {
        if (tvTitle != null && !TextUtils.isEmpty(title)) {
            actionBar.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        } else {
            actionBar.setVisibility(View.GONE);
        }
    }

    /**
     * 显示左边按钮
     */
    public void showBack() {
        if (rlBack != null) rlBack.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏后退箭头
     */
    public void hideBack() {
        if (rlBack != null) rlBack.setVisibility(View.INVISIBLE);
    }

    /**
     * 获取根布局
     */
    public LinearLayout getMyRootView() {
        return llRoot;
    }

    /**
     * 获取actionbar
     */
    public View getMyActionBar() {
        return actionBar;
    }

    /**
     * 获取contentView
     */
    public View getMyContentView() {
        return contentView;
    }

    public void showLoading() {
        if (interceptor != null) interceptor.runOnStart();
    }

    public void hideLoading() {
        if (interceptor != null) interceptor.runOnComplete();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (BaseSection section : sections) section.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (BaseSection section : sections) section.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (BaseSection section : sections) section.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (BaseSection section : sections) section.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (BaseSection section : sections) section.onStop();
        hideLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (BaseSection section : sections) section.onDestroy();
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
        RxBus.get().unregister(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (BaseSection section : sections) section.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (BaseSection section : sections) section.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (BaseSection section : sections) section.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for (BaseSection section : sections) section.onConfigurationChanged(newConfig);
    }
}
