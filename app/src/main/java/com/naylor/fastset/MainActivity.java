package com.naylor.fastset;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AudioSetting mas;

    private Button btMax;
    private Button btMute;
    private Button bt25;
    private Button bt50;
    private Button bt75;
    private TextView tvV;

    private Button btLMax;
    private Button btLMin;
    private Button btL25;
    private Button btL50;
    private Button btL75;
    private TextView tvL;
    private Button btExit;
    private View btSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mas = new AudioSetting(this);
        findViews();
        setVolumeViews();
        setLightViews();
        setSystemViews();
    }

    public void findViews(){
        btMax = findViewById(R.id.btMax);
        btMute = findViewById(R.id.btMute);
        bt25 = findViewById(R.id.bt25per);
        bt50 = findViewById(R.id.bt50per);
        bt75 = findViewById(R.id.bt75per);
        tvV = findViewById(R.id.tvVolume);

        btLMax = findViewById(R.id.btLMax);
        btLMin = findViewById(R.id.btLMin);
        btL25 = findViewById(R.id.btL25);
        btL50 = findViewById(R.id.btL50);
        btL75 = findViewById(R.id.btL75);
        tvL = findViewById(R.id.tvLight);

        btExit = findViewById(R.id.btExit);
        btSleep = findViewById(R.id.btSleep);
    }

    public void setVolumeViews(){
        tvV.setText(mas.getCurrentVolume() + "%");
        btMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setVolume(AudioSetting.MAX);
                tvV.setText("MAX");
            }
        });
        btMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setVolume(AudioSetting.MIN);
                tvV.setText("MUTE");
            }
        });
        bt25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setVolume(AudioSetting.P25);
                tvV.setText("25%");
            }
        });
        bt50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setVolume(AudioSetting.P50);
                tvV.setText("50%");
            }
        });
        bt75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setVolume(AudioSetting.P75);
                tvV.setText("75%");
            }
        });
    }

    public void setLightViews(){
        checkPermission(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);

        tvL.setText(mas.getCurrentLight() + "%");
        btLMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setLight(AudioSetting.LMAX);
                tvL.setText("BRIGHT");
            }
        });
        btLMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setLight(AudioSetting.LMIN);
                tvL.setText("DARK");
            }
        });
        btL75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setLight(AudioSetting.LP75);
                tvL.setText("75%");
            }
        });
        btL50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setLight(AudioSetting.LP50);
                tvL.setText("50%");
            }
        });
        btL25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.setLight(AudioSetting.LP25);
                tvL.setText("25%");
            }
        });


    }

    public void setSystemViews(){
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mas.sleep();
                finish();
            }
        });
    }

    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(permission);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
                return false;
            }else{
                return true;
            }
        }return true;
    }

    public void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
