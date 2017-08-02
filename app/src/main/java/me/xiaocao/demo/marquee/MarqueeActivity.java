package me.xiaocao.demo.marquee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import me.xiaocao.demo.R;

public class MarqueeActivity extends AppCompatActivity {

    private static final String[] imgs = new String[]{"https://ws1.sinaimg.cn/large/610dc034ly1fhovjwwphfj20u00u04qp.jpg", "https://ws1.sinaimg.cn/large/610dc034ly1fhnqjm1vczj20rs0rswia.jpg", "https://ws1.sinaimg.cn/large/610dc034ly1fhj5228gwdj20u00u0qv5.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        List<Marquee> marquees = new ArrayList<>();
        List<Marquee> marquees1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Marquee marquee = new Marquee();
            marquee.setImgUrl(imgs[i]);
            marquee.setTitle("我是图片轮播文字" + i);
            marquees.add(marquee);
            Marquee marquee1 = new Marquee();

            marquee1.setTitle("我是没有文字的" + i);
            marquees1.add(marquee1);
        }
        MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        marqueeView.setImage(true);
        marqueeView.startWithList(marquees);
        MarqueeView marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
        marqueeView1.setImage(false);
        marqueeView1.startWithList(marquees1);
    }
}
