package com.example.administrator.myyinxiang.ui.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.model.TestModle;
import com.example.administrator.myyinxiang.ui.fragments.PageFragment;
import com.example.administrator.myyinxiang.ui.listener.OnItemClickListener;
import com.example.administrator.myyinxiang.utils.L;

import java.util.List;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.example.administrator.myyinxiang.ui.adapters.IndicateAdapter.java
 * @author: 魏红彬
 * @date: 2017-03-07 17:41
 */
public class IndicateAdapter extends PagerAdapter {
    private static final String TAG = "IndicateAdapter";

    private List<TestModle> mData;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public IndicateAdapter(List<TestModle> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView iv = new ImageView(context);
        iv.setImageResource(mData.get(position).getIds());
        container.addView(iv);
        // 在这个方法里面设置图片的点击事件

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        });
        return iv;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Log.e("333333",position+"");
        ViewCompat.animate((View)object).rotation(360).setDuration(1000).start();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


}