package com.example.administrator.myyinxiang;
import android.content.Context;

import com.example.administrator.myyinxiang.basedb.ChatMessgeDao;
import com.example.administrator.myyinxiang.model.MeassageModel;

import java.util.List;



public class DemoHelper {

	private static DemoHelper instance = null;
	
	private DemoModel demoModel = null;

    private ChatMessgeDao chatMessgeDao;


    private Context appContext;

	private DemoHelper() {
	}

	public  static DemoHelper getInstance() {
		if (instance == null) {
            synchronized(DemoHelper.class){
                instance = new DemoHelper();
            }
		}
		return instance;
	}

	/**
	 * init helper
	 * 
	 * @param context
	 *            application context
	 */
	public void init(Context context) {
	    demoModel = new DemoModel(context);
        appContext=context;
        initDbDao();

	}
    private void initDbDao() {
        chatMessgeDao = new ChatMessgeDao(appContext);
    }



    public void addChatMsgList(List<MeassageModel> meassageModelList) {
        demoModel.addChatMsgList(meassageModelList);
    }


    public List<MeassageModel> getMessagesList(String from, String to) {

        return demoModel.getMessagesList(from,to);
    }


    public void removeChatMsg(String from) {
        demoModel.removeChatMsg(from);
    }


    public void addChatMsg(MeassageModel msg) {
        demoModel.addChatMsg(msg);
    }
    public void updateChatMsg(MeassageModel msg) {
        demoModel.updateChatMsg(msg);
    }

}
