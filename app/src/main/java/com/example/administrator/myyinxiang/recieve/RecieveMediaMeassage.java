package com.example.administrator.myyinxiang.recieve;

import android.os.Message;

import com.example.administrator.myyinxiang.Contents;
import com.example.administrator.myyinxiang.utils.HandlerHolder;
import com.example.administrator.myyinxiang.utils.UDPUtils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;



/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.chatwei.recieve.RecieveMeassage.java
 * @author: 魏红彬
 * @date: 2017-02-08 10:20
 */
public class RecieveMediaMeassage implements Runnable {
    private static final String TAG = "RecieveMeassage";
    byte[] buf = new byte[UDPUtils.BUFFER_SIZE];
    DatagramPacket dpk = null;
    DatagramSocket dsk = null;
    BufferedOutputStream bos = null;
    private int port;
    private String path;//路径
    private HandlerHolder handlerHolder;

    public RecieveMediaMeassage(String path, int port, HandlerHolder handlerHolder) {
        this.path = path;
        this.port = port;
        this.handlerHolder = handlerHolder;
    }

    @Override
    public void run() {
        try {
            dsk = new DatagramSocket(UDPUtils.PORT);
            dpk = new DatagramPacket(buf, buf.length);//用户数据包
            SendMsg("wait client ....");
            dsk.receive(dpk);
            int readSize = 0;
            int readCount = 0;
            int flushSize = 0;
            String content = new String(dpk.getData(), 0, dpk.getLength(), "utf-8");
            bos = new BufferedOutputStream(new FileOutputStream(path));
            dpk.setData(UDPUtils.ok, 0, UDPUtils.ok.length);
            dsk.send(dpk);
            dpk.setData(buf, 0, buf.length);
            dsk.receive(dpk);
            while ((readSize = dpk.getLength()) != 0) {
                // validate client send exit flag
                if (UDPUtils.isEqualsByteArray(UDPUtils.exitData, buf, readSize)) {
                    SendMsg("server exit ...");
                    // send exit flag
                    dpk.setData(UDPUtils.exitData, 0, UDPUtils.exitData.length);
                    dsk.send(dpk);
                    break;
                }

                bos.write(buf, 0, readSize);
                if (++flushSize % 1000 == 0) {
                    flushSize = 0;
                    bos.flush();
                }
                dpk.setData(UDPUtils.successData, 0, UDPUtils.successData.length);
                dsk.send(dpk);
                dpk.setData(buf, 0, buf.length);
                SendMsg("receive count of " + (++readCount) + " !");
                dsk.receive(dpk);
            }
            // last flush
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dpk.setData(UDPUtils.exitData, 0, UDPUtils.exitData.length);
            try {
                dsk.send(dpk);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                if (bos != null)
                    bos.close();
                if (dsk != null)
                    dsk.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void SendMsg(String content) {
        Message msg = Message.obtain();
        msg.what = Contents.MSG_RECIEVE;
        msg.obj = content;
        handlerHolder.sendMessage(msg);
    }
}