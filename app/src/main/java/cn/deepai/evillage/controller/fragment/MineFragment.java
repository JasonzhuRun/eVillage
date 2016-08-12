package cn.deepai.evillage.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.math.BigDecimal;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.LoginActivity;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.utils.FileUtil;
import cn.deepai.evillage.utils.ToastUtil;

public class MineFragment extends BaseFragment {

    @Bind(R.id.mine_detail)
    View titleDetailView;
    @Bind(R.id.detail_text_name)
    TextView titleIdTextView;

    @OnClick(R.id.mine_clean)
    public void onCleanUpClick() {
        tryToShowProcessDialog();
        long size = getFolderSize(FileUtil.getPicCacheDir());
        if (size == 0) {
            tryToHideProcessDialog();
            ToastUtil.shortToast(getString(R.string.no_caches));
        } else {
            deleteFolderFile(FileUtil.getPicCacheDirPath(),false);
            tryToHideProcessDialog();
            ToastUtil.shortToast(getString(R.string.delete)+getFormatSize(size)+getString(R.string.caches));
        }
    }

    @OnClick(R.id.mine_about)
    public void onAboutClick() {
        DialogManager.showTextDialog(getContext(),getString(R.string.mine_about),getString(R.string.app_about));
    }

    @OnClick(R.id.mine_logout)
    public void onLogoutClick() {
        titleDetailView.setVisibility(View.GONE);
        SettingManager.setCurrentPkh(null);
        SettingManager.setCurrentJdPkh(null);
        SettingManager.getInstance().clearUserInfo();
        ToastUtil.shortToast(getResources().getString(R.string.mine_logout_success));
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this,root);
        initView();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected String getFragmentName() {
        return "Page_Mine";
    }

    private void initView() {
        titleDetailView.setVisibility(View.VISIBLE);
        titleIdTextView.setText(SettingManager.getInstance().getCurUser());
    }
    /**
     * 获取文件夹大小
     * @param file File实例
     * @return long
     */
    public long getFolderSize(java.io.File file){

        long size = 0;
        try {
            java.io.File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++)
            {
                if (fileList[i].isDirectory())
                {
                    size = size + getFolderSize(fileList[i]);

                }else{
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 格式化单位
     */
    public String getFormatSize(double size) {
        double kiloByte = size/1024;
        if(kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte/1024;
        if(megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte/1024;
        if(gigaByte < 1) {
            BigDecimal result2  = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte/1024;
        if(teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
