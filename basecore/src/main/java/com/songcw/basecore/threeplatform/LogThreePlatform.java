package com.songcw.basecore.threeplatform;

import android.content.Context;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.songcw.basecore.BuildConfig;
import com.songcw.basecore.grobal.Grobal;

/**
 * Created by Sprout on 2018/8/21
 */
public class LogThreePlatform implements ThreePlatform {
    @Override
    public void init(Context context) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(Grobal.Logger.Show_Thread)// (Optional) Whether to show thread info or not. Default true
                .methodCount(Grobal.Logger.Method_Count)// (Optional) How many method line to show. Default 2
                .tag(Grobal.Logger.Base_Tag)            // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
