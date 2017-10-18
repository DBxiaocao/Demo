package me.xiaocao.demo.RichText;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.RichType;
import com.zzhoujay.richtext.callback.Callback;
import com.zzhoujay.richtext.callback.OnImageClickListener;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import me.xiaocao.demo.R;

public class RichTextActivity extends AppCompatActivity {
    String string;
    TextView richText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text);
        richText= (TextView) findViewById(R.id.richText);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection con= Jsoup.connect("http://comment.news.163.com/news2_bbs/CTOR5N210001899N.html").header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
                    Document doc= con.get();
                    Element els=doc.select("div.list").get(0);
                    Log.d("<<MMP", "run: "+els.toString());
                    string=els.toString();
                    handler.sendEmptyMessage(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RichText
                    .from(string) // 数据源
                    .type(RichType.HTML) // 数据格式,不设置默认是Html,使用fromMarkdown的默认是Markdown格式
                    .autoFix(true) // 是否自动修复，默认true
                    .autoPlay(true) // gif图片是否自动播放
                    .borderSize(10) // 边框尺寸
                    .borderRadius(50) // 图片边框圆角弧度
                    .scaleType(ImageHolder.ScaleType.FIT_CENTER) // 图片缩放方式
                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT) // 图片占位区域的宽高
                    .resetSize(false) // 默认false，是否忽略img标签中的宽高尺寸（只在img标签中存在宽高时才有效），true：忽略标签中的尺寸并触发SIZE_READY回调，false：使用img标签中的宽高尺寸，不触发SIZE_READY回调
                    .clickable(true) // 是否可点击，默认只有设置了点击监听才可点击
                    .imageClick(new OnImageClickListener() {
                        @Override
                        public void imageClicked(List<String> imageUrls, int position) {
                            Toast.makeText(RichTextActivity.this, "...."+imageUrls.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) // 设置图片点击回调
                    .urlClick(new OnUrlClickListener() {
                        @Override
                        public boolean urlClicked(String url) {
                            return false;
                        }
                    }) // 设置链接点击回调
                    .placeHolder(R.mipmap.ic_launcher) // 设置加载中显示的占位图
                    .error(R.mipmap.ic_launcher) // 设置加载失败的错误图
                    .done(new com.zzhoujay.richtext.callback.Callback() {
                        @Override
                        public void done(boolean imageLoadDone) {

                        }
                    }) // 解析完成回调
                    .into(richText); // 设置目标TextView
        }
    };
}
