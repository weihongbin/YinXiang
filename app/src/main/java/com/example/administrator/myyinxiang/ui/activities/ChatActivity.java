package com.example.administrator.myyinxiang.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myyinxiang.Contents;
import com.example.administrator.myyinxiang.DemoHelper;
import com.example.administrator.myyinxiang.R;
import com.example.administrator.myyinxiang.model.MeassageModel;
import com.example.administrator.myyinxiang.utils.HandlerHolder;
import com.example.administrator.myyinxiang.utils.ThreadPoolUtils;

import java.util.List;



public class ChatActivity extends BaseActivity implements View.OnClickListener {
    private Button btnSend, btnDel, btnUpdate, btnSelect;//增删改查
    private TextView tvSend;
    private TextView tvRecieve;
    private MeassageModel meassageModel;
    private List<MeassageModel> list;
    private ThreadPoolUtils mThreadPoolUtils = new ThreadPoolUtils(ThreadPoolUtils.Type.SingleThread, 1);
    private HandlerHolder mHandlerHolder = new HandlerHolder(this) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Contents.MSG_SEND:
                    String path = (String) msg.obj;
                    show(path);
                    Log.e("path", path);
                    tvSend.append(path + "\n");
                    break;
                case Contents.MSG_RECIEVE:
                    String content = (String) msg.obj;
                    show(content);
                    Log.e("content", content);
                    tvRecieve.append(content + "\n");
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    public void innitView() {
            btnSend = (Button) findViewById(R.id.btn_send);
            btnDel = (Button) findViewById(R.id.btn_delete);
            btnUpdate = (Button) findViewById(R.id.btn_update);
            btnSelect = (Button) findViewById(R.id.btn_select);
            tvSend = (TextView) findViewById(R.id.tv_send);
            tvRecieve = (TextView) findViewById(R.id.tv_recieve);

    }


    @Override
    public void innitListner() {
        btnSend.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnSelect.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btn_send){

        }

//        switch (v.getId()) {
//
//            case R.id.btn_send:
//                list = new ArrayList<>();
//                for (int i = 0; i < 2; i++) {
//                    meassageModel = new MeassageModel();
//                    meassageModel.setId("1");
//                    meassageModel.setUserId("navigation_selector.xml");
//                    meassageModel.setToUserId("b" + i);
//                    meassageModel.setCurrentTime(TimeUtils.getCurTimeString(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())));
//                    list.add(meassageModel);
//                }
//                DemoHelper.getInstance().addChatMsgList(list);
//                show("navigation_selector.xml", "b");
//                break;
//            case R.id.btn_delete:
//                DemoHelper.getInstance().removeChatMsg("1");
//                show("navigation_selector.xml", "b");
//                break;
//            case R.id.btn_update:
//                meassageModel = new MeassageModel();
//                meassageModel.setId("1");
//                meassageModel.setUserId("navigation_selector.xml");
//                meassageModel.setToUserId("b56");
//                meassageModel.setType(1);
//                DemoHelper.getInstance().updateChatMsg(meassageModel);
//                show("navigation_selector.xml", "b56");
//                break;
//            case R.id.btn_select:
//                show("navigation_selector.xml", "b");
//                break;

//        }
//         if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//             show("sd卡不可用");
//             return;
//         }
//        String path2 =  Environment.getExternalStorageDirectory().getPath();
//        File file=new File(path2+"/abcaaaa.mp3");
////        mThreadPoolUtils.execute(new SendMeassage(path2,"1",1,mHandlerHolder));
//        mThreadPoolUtils.execute(new RecieveMediaMeassage(path2+ File.separator+"weihb.mp3",1,mHandlerHolder));

    }

    private void show(String a, String b) {

        List<MeassageModel> lis = DemoHelper.getInstance().getMessagesList(a, b);
        if (lis != null) {
            for (int i = 0; i < lis.size(); i++) {
                tvRecieve.append(lis.get(i).toString());
            }
        }

    }
}
