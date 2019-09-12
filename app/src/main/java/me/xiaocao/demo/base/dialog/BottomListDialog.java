package me.xiaocao.demo.base.dialog;

import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.xiaocao.demo.R;
import me.xiaocao.demo.base.utils.DisplayUtil;
import me.xiaocao.demo.base.view.DiverItemDecoration;
import me.xiaocao.demo.base.utils.OnItemClickListener;
import me.xiaocao.demo.base.adapter.SimpleRecyclerAdapter;

/**
 * 作者：${xiaocao} on 2019/7/8 21:37
 * 邮箱：
 */
public class BottomListDialog extends BaseDialog {
    RecyclerView mRecyclerView;
    AppCompatTextView cancel;
    private SimpleRecyclerAdapter mAdapter;
    private List<String> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public BottomListDialog setItems(List<String> items) {
        this.items = items;
        return this;
    }
    public BottomListDialog setItems(String[] items) {
        this.items = Arrays.asList(items);
        return this;
    }
    public BottomListDialog setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }


    @Override
    public int getResLayout() {
        return R.layout.dialog_bottom_list_layout;
    }

    @Override
    public void bindView(View view) {
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> dismiss());
        mAdapter = new SimpleRecyclerAdapter<String>()
                .setLayoutResId(R.layout.item_dialog_list_bottom)
                .setDataList(items)
                .setOnBindViewListener((position, s, view1) -> {
                    TextView text = view1.findViewById(R.id.text);
                    text.setText(s);
                    view1.setOnClickListener(v -> {
                        onItemClickListener.onClick(v, position);
                        dismiss();
                    });
                });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DiverItemDecoration(ContextCompat.getColor(mActivity, R.color.default_layout_color), DisplayUtil.dp2px(0.5f)));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean isCancel() {
        return true;
    }

    @Override
    public float getDimAmount() {
        return 0.4f;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            DisplayMetrics dm =new DisplayMetrics();
            if (window != null) {
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                WindowManager.LayoutParams params = window.getAttributes();
                params.gravity = Gravity.BOTTOM;
                params.windowAnimations = R.style.BottomToTopAnim;
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                window.setAttributes(params);
            }
        }
    }
}
