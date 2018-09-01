package com.songcw.login.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.songcw.basecore.grobal.Constant;
import com.songcw.login.activity.LoginActivity;

/**
 * Created by Sprout on 2018/8/31
 */
public class ComponentLogin implements IComponent {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        if (Constant.action.ALogin.equals(actionName)) {
            goLogin(cc);
        } else {
            CC.sendCCResult(cc.getCallId(), CCResult.error("CC找不到此Action = " + actionName));
        }
        // false: 组件同步实现（onCall方法执行完之前会将执行结果CCResult发送给CC）
        // true: 组件异步实现（onCall方法执行完之后再将CCResult发送给CC，CC会持续等待组件调用CC.sendCCResult发送的结果，直至超时）
        return true;
    }

    private void goLogin(CC cc) {
        Context context = cc.getContext();
        Intent intent = new Intent(context, LoginActivity.class);
        if (!(context instanceof Activity)) {
            //调用方没有设置context或app间组件跳转，context为application
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("ccCallId", cc.getCallId());
        context.startActivity(intent);
    }
}
