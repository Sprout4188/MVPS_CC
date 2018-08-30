package com.songcw.basecore.util;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Sprout on 2018/7/26
 */

public class JumpUtil {

    public static void jumpTo(Activity from, Class clazz) {
        Intent intent = new Intent(from, clazz);
        from.startActivity(intent);
    }
}
