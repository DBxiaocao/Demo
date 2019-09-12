package me.xiaocao.demo.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 　xiaocao
 * Description　　 RecyclerView 通用的Adapter
 * Date:　　2019/4/30 13:14
 */

public class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    private int mLayoutResId;
    private List<T> mDataList;
    private LayoutInflater mInflater;
    private OnBindViewListener<T> mOnBindViewListener;

    public SimpleRecyclerAdapter() {
        mDataList = new ArrayList<>();
    }

    public SimpleRecyclerAdapter(int layoutResId) {
        mLayoutResId = layoutResId;
        mDataList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(mInflater.inflate(mLayoutResId, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mOnBindViewListener != null)
            mOnBindViewListener.onBindViewHolder(position,
                    mDataList.get(position), holder.getItemView());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;

        ViewHolder(View itemView, int viewType) {
            super(itemView);
            mItemView = itemView;
        }

        View getItemView() {
            return mItemView;
        }

    }

    public interface OnBindViewListener<T> {
        void onBindViewHolder(int position, T t, View view);
    }

    public SimpleRecyclerAdapter<T> setOnBindViewListener(OnBindViewListener<T> l) {
        mOnBindViewListener = l;
        return this;
    }

    public SimpleRecyclerAdapter<T> setDataList(List<T> dataList) {
        mDataList.clear();
        mDataList = dataList;
        notifyDataSetChanged();
        return this;
    }

    public SimpleRecyclerAdapter<T> addDataList(List<T> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
        return this;
    }

    public SimpleRecyclerAdapter<T> clearDataList() {
        mDataList.clear();
        notifyDataSetChanged();
        return this;
    }
    public SimpleRecyclerAdapter<T> setLayoutResId(int layoutResId) {
        mLayoutResId = layoutResId;
        return this;
    }




    public T getItem(int position) {
        return mDataList.get(position);
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public void removeData(int position) {
        mDataList.remove(position);
    }
}
