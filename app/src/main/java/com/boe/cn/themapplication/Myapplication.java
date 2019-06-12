package com.boe.cn.themapplication;

import android.app.Application;

import com.unity3d.player.UnityPlayer;


/**
 * created by  Tigers
 * at 2019/6/4
 * desc
 * [modifiedTime]
 */
public class Myapplication extends Application {
    private UnityPlayer unityPlayer;
    private static Myapplication myApplication = null;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;



    }
    public void setUnityPlayer(UnityPlayer unityPlayer) {
        this.unityPlayer = unityPlayer;
    }

    public UnityPlayer getPlayer() {
        return unityPlayer;
    }

    public static Myapplication getInstance() {
        return myApplication;
    }
}
