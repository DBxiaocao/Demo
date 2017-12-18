package me.xiaocao.demo.base;

import android.content.Context;

import java.util.List;

/**
 * description: MyListAdapter
 * author: lijun
 * date: 17/8/21 14:27
 */

public class MyListAdapter<T> extends BaseCommAdapter<T> {
    public MyListAdapter(Context context, List<T> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, T t, int position) {
        if (onCallBackData != null) {
            onCallBackData.convertView(holder, t);
        }
    }

    public void setOnCallBackData(OnCallBackData<T> onCallBackData) {
        this.onCallBackData = onCallBackData;
    }

    private OnCallBackData onCallBackData;

    public interface OnCallBackData<T> {
        void convertView(BaseViewHolder helper, T item);
    }
}
