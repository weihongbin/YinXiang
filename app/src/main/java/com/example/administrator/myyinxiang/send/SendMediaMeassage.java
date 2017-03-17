package com.example.administrator.myyinxiang.send;

import android.os.Message;
import android.util.Log;

import com.example.administrator.myyinxiang.Contents;
import com.example.administrator.myyinxiang.utils.HandlerHolder;
import com.example.administrator.myyinxiang.utils.UDPUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;



/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.chatwei.send.SendMeassage.java
 * @author: 魏红彬
 * @date: 2017-02-08 10:19
 */
public class SendMediaMeassage implements Runnable {
    private static final String TAG = "SendMeassage";
    private Long startTime = System.currentTimeMillis();//获取当前的时间
    byte[] buf = new byte[UDPUtils.BUFFER_SIZE];//接收发送数据的缓冲区
    byte[] receiveBuf = new byte[1];//确认消息的缓冲
    BufferedInputStream bis = null;
    DatagramPacket dpk = null;
    DatagramSocket dsk = null;
    int readSize = -1;
    private String path;
    private String ip;
    private int port;
    private HandlerHolder handlerHolder;

    public SendMediaMeassage(String path, String ip, int port, HandlerHolder handlerHolder) {
        this.path = path;
        this.ip = ip;
        this.port = port;
        this.handlerHolder = handlerHolder;
    }

    @Override
    public void run() {
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(path + File.separator + "abca.mp3")));
            dpk = new DatagramPacket(buf, buf.length, new InetSocketAddress(InetAddress.getByName(ip), UDPUtils.PORT));
            dsk = new DatagramSocket();
            int sendCount = 0;
            while ((readSize = bis.read(buf, 0, buf.length)) != -1) {
                Thread.sleep(100);
                dpk.setData(buf, 0, readSize);
                dsk.send(dpk);
                // wait server response
                {
                    while (true) {
                        dpk.setData(receiveBuf, 0, receiveBuf.length);
                        dsk.receive(dpk);

                        // confirm server receive
                        if (!UDPUtils.isEqualsByteArray(UDPUtils.successData, receiveBuf, dpk.getLength())) {
                            Log.e(">>>>>>>", "resend ...");
                            SendMsg("resend ...");
                            dpk.setData(buf, 0, readSize);
                            dsk.send(dpk);
                        } else
                            break;
                    }
                }
                SendMsg("send count of " + (++sendCount) + "!");
            }
            // send exit wait server response
            while (true) {
                Log.e(">>>>>>>", "client send exit message ....");
                SendMsg("client send exit message ....");
                dpk.setData(UDPUtils.exitData, 0, UDPUtils.exitData.length);
                dsk.send(dpk);
                dpk.setData(receiveBuf, 0, receiveBuf.length);
                dsk.receive(dpk);
                // byte[] receiveData = dpk.getData();
                if (!UDPUtils.isEqualsByteArray(UDPUtils.exitData, receiveBuf, dpk.getLength())) {
                    SendMsg("client Resend exit message ....");
                    dsk.send(dpk);
                } else
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (dsk != null)
                    dsk.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time:" + (endTime - startTime));
    }

    private void SendMsg(String content) {
        Message msg = Message.obtain();
        msg.what = Contents.MSG_SEND;
        msg.obj = content;
        handlerHolder.sendMessage(msg);
    }
}