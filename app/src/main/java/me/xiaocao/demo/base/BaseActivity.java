package me.xiaocao.demo.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 　xiaocao
 * Description
 * Date:2019/9/12 0012 10:10
 */
public class BaseActivity extends AppCompatActivity {
    protected AppCompatActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
    }

    public void GoActivity(Class<?> cls) {
        startActivity(new Intent(mActivity, cls));
    }

   public void showMessage(String msg){
        Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT).show();
    }
}
