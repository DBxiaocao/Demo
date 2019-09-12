package me.xiaocao.demo.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import me.xiaocao.demo.R;


public abstract class BaseDialog extends DialogFragment {

    protected AppCompatActivity mActivity;

    protected View.OnClickListener mCancelListener;
    protected View.OnClickListener mOkListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(mActivity, getStyle());
        View view = LayoutInflater.from(mActivity).inflate(getResLayout(), null);
        bindView(view);
        builder.setView(view);
        return builder.create();
    }

    @LayoutRes
    public abstract int getResLayout();

    public abstract void bindView(View view);

    public abstract boolean isCancel();

    @StyleRes
    protected int getStyle() {
        return R.style.CommonDialog;
    }

    public abstract float getDimAmount();

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        dialog.setCancelable(isCancel());
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = getDimAmount();
            window.setAttributes(params);
        }
    }

    public void show(FragmentManager fm) {
        super.show(fm, getClass().getName());
    }
}
