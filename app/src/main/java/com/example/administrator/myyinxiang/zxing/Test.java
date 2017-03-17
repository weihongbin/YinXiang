package com.example.administrator.myyinxiang.zxing;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.administrator.myyinxiang.zxing.encoding.EncodingHandler;
import com.google.zxing.WriterException;

/**
 * Created by Administrator on 2017/2/18.
 */
public class Test {

    public static  void main(String [] args){

            //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
        try {
            Bitmap qrCodeBitmap = EncodingHandler.createQRCode("123132", 350);


        } catch (WriterException e) {
            e.printStackTrace();
        }


    }
}
