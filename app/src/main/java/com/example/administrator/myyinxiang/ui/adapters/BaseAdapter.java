package com.example.administrator.myyinxiang.ui.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.ui.listener.OnItemClickListener;
import com.example.administrator.myyinxiang.ui.listener.OnLoadMoreListener;
import com.example.administrator.myyinxiang.ui.listener.OnItemLongClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据源
     */
    private List<T> mDatas;
    /**
     * 布局
     */
    private int mLayoutId;
    /**
     * 解析布局
     */
    private LayoutInflater mInflater;

    private static final int TYPE_ITEM = 0;

    private static final int TYPE_FOOTER = 1;

    private boolean isLoading=false;
    /**
     * 点击条目
     */
    private OnItemClickListener mItemClickListener;
    /**
     * 长按
     */
    private OnItemLongClickListener mLongItemClickListener;

    private OnLoadMoreListener mOnLoadMoreListener;
    /**
     * 构造方法
     * @param mContext
     * @param mData
     * @param mLayoutId
     */
    public BaseAdapter(Context mContext, List<T> mData,int mLayoutId,RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mDatas = mData;
        this.mLayoutId=mLayoutId;
        mInflater=LayoutInflater.from(mContext);
        innit(recyclerView);
    }

    protected  void innit(RecyclerView recyclerView){
       recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
           }

           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
               LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
               int totalItemCount = linearLayoutManager.getItemCount();
               int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
               if (!isLoading &&dy>0&&lastVisibleItemPosition>=totalItemCount-1) {
                   isLoading = true;
                   if (mOnLoadMoreListener != null) {
                       mOnLoadMoreListener.onLoadMore();
                   }

               }
           }
       });
    }

    public void setOnLongItemClickListener(OnItemLongClickListener onLongItemClickListener) {
        this.mLongItemClickListener = onLongItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setmOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void addData(int position, T t) {
        mDatas.add(position, t);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    public void addAll(List<T> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }
    public void updateData(List<T> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View  view=mInflater.inflate(mLayoutId,parent,false);
            BaseViewHolder holder = new BaseViewHolder(view);
            return holder;
        }
        // type == TYPE_FOOTER 返回footerView
        else if (viewType == TYPE_FOOTER) {
            View view = mInflater.inflate(R.layout.load_more_foot, null);
            return new BaseFoorHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof BaseViewHolder){
            convert(mContext, holder, mDatas.get(position));
            if(mItemClickListener!=null){
                ((BaseViewHolder)holder).mItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mItemClickListener.onItemClick(view, position);
                    }
                });
            }
            if(mLongItemClickListener!=null){
                ((BaseViewHolder)holder).mItemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        mLongItemClickListener.onLongItemClick(view, position);
                        return true;
                    }
                });
            }
        }

    }

    protected abstract void convert(Context mContext, RecyclerView.ViewHolder holder, T t);


    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
    public void setLoading(boolean b){
        isLoading=b;
    }
}
