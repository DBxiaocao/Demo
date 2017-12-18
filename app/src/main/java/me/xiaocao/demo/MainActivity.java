package me.xiaocao.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baidu.idl.util.FileUtil;
import com.baidu.ocr.ui.camera.CameraActivity;

import java.io.File;

import me.xiaocao.demo.RichText.RichTextActivity;
import me.xiaocao.demo.marquee.MarqueeActivity;
import me.xiaocao.demo.signaturepad.SgnatureActivity;

public class MainActivity extends AppCompatActivity {
    private AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        findViewById(R.id.btnMarquee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoActivity(MarqueeActivity.class);
            }
        });
        findViewById(R.id.btnMarkDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoActivity(RichTextActivity.class);
            }
        });
        findViewById(R.id.btnSgnature).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GoActivity(SgnatureActivity.class);
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent, 111);
            }
        });
        SgnatureActivity.verifyStoragePermissions(this);
    }
    public static File getSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "pic.jpg");
        return file;
    }
    public void GoActivity(Class<?> cls) {
        startActivity(new Intent(activity, cls));
    }
}
