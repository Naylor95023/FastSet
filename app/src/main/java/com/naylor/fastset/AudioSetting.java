package com.naylor.fastset;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;

import android.os.PowerManager;
import android.os.SystemClock;
import java.lang.reflect.InvocationTargetException;

public class AudioSetting {
    private Context context;
    private AudioManager am;

    static final int MAX = 15;
    static final int MIN = 0;
    static final int P25 = 4;
    static final int P50 = 8;
    static final int P75 = 12;

    static final int LMAX = 255;
    static final int LMIN = 0;
    static final int LP25 = 64;
    static final int LP50 = 128;
    static final int LP75 = 192;



    public AudioSetting(Context context){
        this.context = context;
        am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public int getCurrentVolume(){
        int current = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        return (current * 100) / MAX ;
    }

    public int getCurrentLight(){
        try {
            int b = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
            return (b * 100) / LMAX;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }return -1;
    }

    public void setVolume(int v){
        am.setStreamVolume(AudioManager.STREAM_MUSIC, v, AudioManager.FLAG_SHOW_UI);
        am.setStreamVolume(AudioManager.STREAM_RING, v, AudioManager.FLAG_SHOW_UI);
        am.setStreamVolume(AudioManager.STREAM_NOTIFICATION, v, AudioManager.FLAG_SHOW_UI);
        am.setStreamVolume(AudioManager.STREAM_SYSTEM, v, AudioManager.FLAG_SHOW_UI);
    }

    public void setLight(int brightness){
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    public void sleep(){
        /*PowerManager pm = (PowerManager)context.getSystemService(context.POWER_SERVICE);
        try {
            pm.getClass().getMethod("goToSleep", new Class[]{long.class}).invoke(pm, SystemClock.uptimeMillis());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/

        DevicePolicyManager mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDPM.lockNow();

        //https://blog.csdn.net/w815878564/article/details/81519200
    }
}
