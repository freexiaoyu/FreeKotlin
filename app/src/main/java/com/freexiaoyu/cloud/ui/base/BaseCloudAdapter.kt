package com.freexiaoyu.cloud.ui.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

/**
 * Created by DIY on 2018-11-19. 18:52
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
abstract class BaseCloudAdapter<T>: RecyclerView.Adapter<BaseRecyclerViewHolder>() {
    abstract var mContext: Context
    abstract var mInflater: LayoutInflater
    abstract var mDatas: MutableList<T>
    abstract var mOnItemClickListener: OnItemClickListener<T>


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    override fun getItemCount(): Int {
        var count = 0
        if (mDatas.size > 0) {
            count = mDatas.size
        }
        return count
    }

    fun addItemLast(datas: List<T>) {
        mDatas.addAll(datas)
        notifyDataSetChanged();
    }

    fun addItemTop(datas: MutableList<T>) {
        mDatas = datas
        notifyDataSetChanged();
    }

    fun remove(position: Int) {
        mDatas.removeAt(position)
        notifyItemRemoved(position);
    }

    fun removeAll() {
        mDatas.clear()
        notifyDataSetChanged();
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getDatas(): List<T> {
        return mDatas
    }

    // 点击事件接口
    interface OnItemClickListener<T> {
        fun onItemClick(view: View, position: Int, model: T)

        fun onItemLongClick(view: View, position: Int, model: T)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        this.mOnItemClickListener = listener
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        showViewHolder(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        return createView(parent, viewType)
    }


    protected abstract fun showViewHolder(holder: BaseRecyclerViewHolder, position: Int)

    /***
     *
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract fun createView(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder

}