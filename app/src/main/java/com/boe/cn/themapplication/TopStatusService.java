package com.boe.cn.themapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * created by  Tigers
 * at 2019/6/4
 * desc
 * [modifiedTime]
 */
public class TopStatusService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
