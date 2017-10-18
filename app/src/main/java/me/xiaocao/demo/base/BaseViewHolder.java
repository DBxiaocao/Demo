package me.xiaocao.demo.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by lm on 2016/4/22.
 */
public class BaseViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context context;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId,
                          int position) {
        this.mViews = new SparseArray<View>();
        this.mPosition = position;
        this.context = context;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId,
                parent, false);
        this.mConvertView.setTag(this);
    }

    public static BaseViewHolder get(Context context, View convertView,
                                     ViewGroup parent, int layoutId, int position) {
        if (null == convertView) {
            return new BaseViewHolder(context, parent, layoutId, position);
        } else {
            BaseViewHolder holder = (BaseViewHolder) convertView.getTag();
            holder.mPosition = position;

            return holder;
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * @param viewId
     * @return
     * @return T
     * @author LiJun
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    /**
     * @param viewId 视图id
     * @param text  显示的内容
     * @return ViewHolder 对应的viewHolder
     * @author LiJun
     */
    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        if (!TextUtils.isEmpty(text)) {
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setText("");
            tv.setVisibility(View.GONE);
        }
        return this;
    }
}
