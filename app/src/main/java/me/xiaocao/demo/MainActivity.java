package me.xiaocao.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.xiaocao.demo.marquee.MarqueeActivity;

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
    }

    public void GoActivity(Class<?> cls) {
        startActivity(new Intent(activity, cls));
    }
}
