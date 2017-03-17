package com.example.administrator.myyinxiang;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.example.administrator.myyinxiang.dataServer.ActivityTaskCallback;
import com.example.administrator.myyinxiang.dataServer.ManagerEngine;
import com.example.administrator.myyinxiang.model.VersionModel;
import com.example.administrator.myyinxiang.ui.activities.BaseActivity;
import com.example.administrator.myyinxiang.utils.AppUtils;
import com.example.administrator.myyinxiang.utils.LogUtils;
import com.example.administrator.myyinxiang.utils.T;


import java.io.File;

/**
 * Created by admin on 2016/1/18.
 */
public class BaseVersionActivity extends BaseActivity {
    //下载的apk文件名
    private String DOWNLOAD_UPDATE_APP_NAME;
    // 广播
    private BroadcastReceiver receiver;
    // 版本信息
    protected double versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        versionCode = AppUtils.getVersionCode(this);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void innitView() {

    }

    @Override
    public void innitListner() {

    }

    /**
     * 判断是否更新
     *
     * @param isShowMsg
     */
    protected void checkAppUpdate(final boolean isShowMsg) {

        VersionModel versionModel = new VersionModel();
        versionModel.setType("2");
        versionModel.setTyp1("1");
        ManagerEngine.getInstance().getSettingManager().getAppNewVersion(versionModel,new ActivityTaskCallback<VersionModel>(this, isShowMsg) {
            @Override
            public void success(final VersionModel versionModel) {
                if (Double.parseDouble(versionModel.getCode()) > versionCode) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BaseVersionActivity.this);
                    builder.setTitle("版本更新");
                    builder.setMessage(versionModel.getInfo());
                    builder.setNegativeButton("取消", null);
                    builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            receiver = new DownLoadCompleteReceiver();
                            IntentFilter filter = new IntentFilter();
                            filter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
                            registerReceiver(receiver, filter);
                            DOWNLOAD_UPDATE_APP_NAME = "EZParking" + versionModel.getName() + ".apk";
                            downloadApk(versionModel.getAdr());
                        }
                    });
                    builder.create().show();
                } else {
                    if (isShowMsg) {

                        T.showMeassage(getApplicationContext(),"\"当前已是最新版本\"");
                    }
                }
            }
        });
    }

    /**
     * 下载新版本apk
     */
    public void downloadApk(String uri) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("下载");
        request.setDescription(getResources().getString(R.string.app_name) + "正在下载");
        request.setAllowedOverRoaming(false);//设置漫游状态是否下载
        //设置文件存放目录
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, DOWNLOAD_UPDATE_APP_NAME);
        DownloadManager downManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downManager.enqueue(request);
    }


    /**
     * 广播接收者 处理apk下载完成或取消
     */
    public class DownLoadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                Toast.makeText(BaseVersionActivity.this, "编号：" + id + "的下载任务已经完成！", Toast.LENGTH_SHORT).show();
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), DOWNLOAD_UPDATE_APP_NAME);
                openFile(file);
                unregisterReceiver(receiver);
            }
        }
    }

    /**
     * 安装下载的apk
     *
     * @param file
     */
    private void openFile(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

}
