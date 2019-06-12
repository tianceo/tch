package com.boe.cn.themapplication;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayer;


/**
 * created by  Tigers
 * at 2019/6/3
 * 全息人体
 * [modifiedTime] * [modifiedTime]
 */
public class StandardHumanBodyFragment extends Fragment {

    private volatile static StandardHumanBodyFragment singleton = null;
    private Handler mHandler = null;
    public static StandardHumanBodyFragment getInstance(int status) {
        if (singleton == null) {
            synchronized (StandardHumanBodyFragment.class) {
                if (singleton == null) {
                    singleton = new StandardHumanBodyFragment();
                }
            }
        }
        return singleton;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_splash, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        inidata();
    }

    private void inidata() {
    }


    protected void initView(View view) {
        mHandler=new Handler();
       FrameLayout rl_unityView= view.findViewById(R.id.fr_cunity);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UnityPlayer unityPlayer=Myapplication.getInstance().getPlayer();
                View unityPlayevewr= unityPlayer.getView();
                ViewGroup viewGroup = (ViewGroup) unityPlayevewr.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                rl_unityView.addView(unityPlayevewr);
                unityPlayer.UnitySendMessage("_GlobalManager", "ChangeScene", "Pedia");
            }
        }, 1000 * 3);

//        mUnityPlayer.UnitySendMessage("_GlobalManager", "ChangeScene", "Pedia");
    }


}
