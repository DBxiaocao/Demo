package me.xiaocao.demo.signaturepad;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.xiaocao.demo.R;

public class SgnatureActivity extends AppCompatActivity {
    @Bind(R.id.clear1)
    Button mClear;
    @Bind(R.id.save1)
    Button mSave;
    @Bind(R.id.cancel)
    Button cAndel;
    @Bind(R.id.view)
    SgnaturepadView pathView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgnature);
        ButterKnife.bind(this);
        Log.e("FFFFFFFFF", "|KLDSFJKLDJFL");
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss",
                        Locale.US);
                if (pathView.getTouched()) {
                    try {
                        pathView.save(Environment.getExternalStorageDirectory() + "/简洁新闻/" + sdf.format(new Date()) + ".png", false, 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(SgnatureActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    setResult(101);
                    finish();
                } else {
                    Toast.makeText(SgnatureActivity.this, "您没有签名~", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("EEEEEEEEEE", "|KLDSFJKLDJFL");
                pathView.clear();
            }
        });
        cAndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getNet() {
        Log.e("MMMMMMMMMM", "|KLDSFJKLDJFL");
        pathView.clear();
    }


    //这里是 在进入签名页面的时候将屏幕设置为横屏
    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();

    }
    public static void verifyStoragePermissions(AppCompatActivity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
