package com.example.administrator.myyinxiang.ui.adapters;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/4.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    /**
     * 控件的集合
     */
    SparseArray<View> mViews;
    /**
     * 每个控件
     */
    View mItemView;

    /**
     * 构造方法
     * @param itemView
     */
    public BaseViewHolder(View itemView) {
        super(itemView);
        mItemView=itemView;
        mViews=new SparseArray<>();
    }

    /**
     * 获取子控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mItemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
      return (T) view;
    }

    /**
     * 文字信息
     * @param viewid
     * @param resid
     * @return
     */
    public BaseViewHolder setText(int viewid,int resid){
        TextView view= getView(viewid);
        view.setText(resid);
        return this;
    }
    public BaseViewHolder setText(int viewid,String text){
        TextView view= getView(viewid);
        view.setText(text);
        return this;
    }
    public BaseViewHolder setText(int viewid,SpannableStringBuilder text){
        TextView view= getView(viewid);
        view.setText(text);
        return this;
    }
    public BaseViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * 图片背景
     * @param viewid
     * @param resid
     * @return
     */
    public BaseViewHolder setImageResource(int viewid,int resid){
        ImageView view= getView(viewid);
        view.setImageResource(resid);
        return this;
    }
    public BaseViewHolder setImageBitmap(int viewid,Bitmap bitmap){
        ImageView view= getView(viewid);
        view.setImageBitmap(bitmap);
        return this;
    }
    public BaseViewHolder setImageDrawable(int viewid,Drawable drawble){
        ImageView view= getView(viewid);
        view.setImageDrawable(drawble);
        return this;
    }
    public BaseViewHolder setBackgroundColor(int viewid,int color){
        View view= getView(viewid);
        view.setBackgroundColor(color);
        return this;
    }
    public BaseViewHolder setBackgroundResource(int viewid,int backgroundRes){
        ImageView view= getView(viewid);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * 设置通道
     * @param viewid
     * @param value
     * @return
     */
   public BaseViewHolder setAlpha(int viewid,float value){
       if(Build.VERSION.SDK_INT>Build.VERSION_CODES.HONEYCOMB){
           getView(viewid).setAlpha(value);
       }else{
           AlphaAnimation alpha=new AlphaAnimation(value,value);
           alpha.setDuration(0);
           alpha.setFillAfter(true);
           getView(viewid).startAnimation(alpha);
       }
       return  this;
   }

    /**
     * 是否可见
     * @param viewid
     * @param visible
     * @return
     */
    public BaseViewHolder setVisible(int viewid,boolean visible ){
        View view=getView(viewid);
        view.setVisibility(visible?View.VISIBLE:View.GONE);
        return  this;
    }

    public BaseViewHolder setTag(int viewid,Object tag){
        View view=getView(viewid);
        view.setTag(tag);
        return this;
    }
    public BaseViewHolder setTag(int viewid,int key,Object tag){
        View view=getView(viewid);
        view.setTag(key,tag);
        return this;
    }

    public BaseViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 事件监听
     * @param viewid
     * @param onClickListener
     * @return
     */

    public BaseViewHolder setOnClickListener(int viewid, View.OnClickListener onClickListener){
        View view=getView(viewid);
        view.setOnClickListener(onClickListener);
        return  this;
    }
    public BaseViewHolder setOnTouchListener(int viewid, View.OnTouchListener onTouchListener){
        View view=getView(viewid);
        view.setOnTouchListener(onTouchListener);
        return  this;
    }
    public BaseViewHolder setOnLongClickListener(int viewid, View.OnLongClickListener onLongClickListener){
        View view=getView(viewid);
        view.setOnLongClickListener(onLongClickListener);
        return  this;
    }
}
