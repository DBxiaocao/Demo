
package me.xiaocao.demo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 类注释
 *
 * @author lijun
 * @date 2015-2-16 下午10:39:05
 */
public abstract class BaseCommAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mlayoutId;

    public BaseCommAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.mlayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return mDatas.size();
    }

    /**
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * @see android.widget.Adapter#getView(int, View, ViewGroup)
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = BaseViewHolder.get(mContext, convertView, parent, mlayoutId, position);

        convert(holder, getItem(position), position);

        return holder.getConvertView();
    }

    public abstract void convert(BaseViewHolder holder, T t, int position);


    public List<T> getAlldates(){
        return this.mDatas;
    }

    public void addDate(List<T> mDatas) {
        this.mDatas.addAll(mDatas);
        this.notifyDataSetChanged();
    }

    public void addHeadDate(List<T> mDatas){
        this.mDatas.addAll(0,mDatas);
        this.notifyDataSetChanged();
    }

    public void updateDate(List<T> dates) {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas = dates;
        } else {
            mDatas = new ArrayList<>();
            mDatas.addAll(dates);
        }
        this.notifyDataSetChanged();
    }
    public void clear(){
        this.mDatas.clear();
        notifyDataSetChanged();
    }
}