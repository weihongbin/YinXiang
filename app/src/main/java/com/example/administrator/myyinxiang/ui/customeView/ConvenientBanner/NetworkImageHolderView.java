package com.example.administrator.myyinxiang.ui.customeView.ConvenientBanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.utils.ImageLoaderUtils;


public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
//        imageView.setImageResource(R.mipmap.ic_launcher);
//        ImageLoader.getInstance().displayImage(data,imageView);
//        String imageUrl = getString(R.string.tsmp_url) + "/icon/" + path;


//        ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(context), new BitmapCache());
//        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.mipmap.jiazai_image, R.mipmap.jiazai_image);
//        imageLoader.get(data, listener);

        Bitmap defaultImage = BitmapFactory.decodeResource(context.getResources(), android.R.drawable.stat_notify_error);
        Bitmap errorImage = BitmapFactory.decodeResource(context.getResources(), R.mipmap.jiazai_image);
        ImageLoaderUtils.display(context,imageView,data);
//        ImageCacheManager.loadImage(context, data, imageView, defaultImage, errorImage);

    }

}
