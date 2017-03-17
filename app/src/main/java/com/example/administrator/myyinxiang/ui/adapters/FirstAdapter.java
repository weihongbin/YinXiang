package com.example.administrator.myyinxiang.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.model.LastComversationModel;

import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
public class FirstAdapter extends BaseAdapter<LastComversationModel> {


    /**
     * 构造方法
     *
     * @param mContext
     * @param mData
     * @param mLayoutId
     * @param recyclerView
     */
    public FirstAdapter(Context mContext, List<LastComversationModel> mData, int mLayoutId, RecyclerView recyclerView) {
        super(mContext, mData, mLayoutId, recyclerView);
    }

    @Override
    protected void convert(Context mContext, RecyclerView.ViewHolder holder, LastComversationModel comversationModel) {
        if (holder instanceof BaseViewHolder){
            ((BaseViewHolder)holder).setImageResource(R.id.image_head,R.mipmap.ic_girl_selected)
            .setText(R.id.tv_name,comversationModel.getName())
            .setText(R.id.tv_last_meassage,comversationModel.getLastMeassage())
            .setText(R.id.tv_time_last,comversationModel.getTime());
        }

    }


}
