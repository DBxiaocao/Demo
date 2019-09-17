package me.xiaocao.demo.signview;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.xiaocao.demo.R;
import me.xiaocao.demo.base.BaseActivity;

/**
 * description: SgnatureActivity  自定义画板
 * author: xiaocao
 * date: 17/12/18 下午9:39
*/
public class SgnatureActivity extends BaseActivity {
    Button mClear;
    Button mSave;
    Button cAndel;
    SgnaturepadView pathView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgnature);
        mClear=findViewById(R.id.clear1);
        mSave=findViewById(R.id.save1);
        cAndel=findViewById(R.id.cancel);
        pathView=findViewById(R.id.view);
        verifyStoragePermissions(this);
        Log.e("FFFFFFFFF", "|KLDSFJKLDJFL");
        mSave.setOnClickListener(v -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss",
                    Locale.US);
            if (pathView.getTouched()) {
                try {
                    pathView.save(Environment.getExternalStorageDirectory() + "/DEMO/" + sdf.format(new Date()) + ".png", false, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(SgnatureActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                setResult(101);
                finish();
            } else {
                Toast.makeText(SgnatureActivity.this, "您没有签名~", Toast.LENGTH_SHORT).show();
            }
        });

        mClear.setOnClickListener(v -> {
            Log.e("EEEEEEEEEE", "|KLDSFJKLDJFL");
            pathView.clear();
        });
        cAndel.setOnClickListener(v -> finish());

    }

    private void getNet() {
        Log.e("MMMMMMMMMM", "|KLDSFJKLDJFL");
        pathView.clear();
    }


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
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
