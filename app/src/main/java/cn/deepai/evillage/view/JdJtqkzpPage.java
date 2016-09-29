package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtqkzpRecyclerAdapter;
import cn.deepai.evillage.controller.activity.PkhxqActivity;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.PkhRequestBean;
import cn.deepai.evillage.model.bean.PkhjtqkzpBean;
import cn.deepai.evillage.model.bean.PkhjtqkzpList;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.DictionaryUtil;
import cn.deepai.evillage.utils.EncryptionUtil;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public class JdJtqkzpPage extends BasePage implements BasePage.IDataEdit,BasePage.IPhotoEdit{

    private List<PkhjtqkzpBean> localData;
    private PkhjtqkzpRecyclerAdapter mPkhjtqkzpRecyclerAdapter;

    public JdJtqkzpPage(Context context) {
        this(context, null);
    }

    public JdJtqkzpPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdJtqkzpPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_recycerview, this);
        initView();
        initData();
    }

    @Override
    public void registeEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unRegisteEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PkhjtqkzpList event) {
        if (isSelected()) {
            for (int i = 0;i < localData.size();i++) {
                for (PkhjtqkzpBean pkhjtqkzpBean:event.list) {
                    if (localData.get(i).getZplx().equals(pkhjtqkzpBean.getZplx())) {
                        localData.set(i,pkhjtqkzpBean);
                    }
                }
            }
            mPkhjtqkzpRecyclerAdapter.notifyResult(true, localData);
            mHasData = true;
        }
    }

    @Override
    public void addPhoto(String uri,String zplx) {

        for (PkhjtqkzpBean bean:localData) {
            if (bean.getZplx().equals(zplx)) {
                bean.setTpdz(uri);
                bean.setTjnd(SettingManager.getCurrentJdPkh().getJdnf());
                bean.setHid(SettingManager.getCurrentJdPkh().getHid());
            }
        }

        mPkhjtqkzpRecyclerAdapter.notifyResult(true, localData);
        mHasData = true;
    }

    @Override
    public void saveData() {
        final Gson gson = new Gson();
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_addPkhJtqkzp);
        for (final PkhjtqkzpBean bean:localData) {
            if (!TextUtils.isEmpty(bean.getHid())) {
                bean.setZpnr(encodePhoto(bean.getTpdz()));
                String[] args = bean.getTpdz().split(File.separator);
                if (args.length > 0) {
                    String tpmc = args[args.length - 1];
                    bean.setTpmc(tpmc);
                    String[] postfix = tpmc.split("\\.");
                    if (postfix.length > 0) {
                        bean.setWjlx(postfix[postfix.length - 1]);
                    } else {
                        bean.setWjlx("png");
                    }
                }
                ((PkhxqActivity)mContext).tryToShowProcessDialog();
                EVRequest.request(Action.ACTION_ADD_PKHJTQKZP, gson.toJson(header), gson.toJson(bean),
                        new ResponseCallback() {
                            @Override
                            public void onDataResponse(String dataJsonString) {
                                ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString, ReturnValueEvent.class);
                                if (returnValueEvent.returnValue == 1)  {
                                    bean.setHid(null);
                                    bean.setZpnr(null);
                                    mHasData = false;
                                }
                                EventBus.getDefault().post(returnValueEvent);
                            }
                        });
            }
        }
    }

    @Override
    public void requestData() {
        if (mHasData) {
            ((PkhxqActivity)mContext).tryToHideProcessDialog();
            return;
        }
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_PKHJTQKZPLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getPkhJtqkzpList)),
                requestGson.toJson(new PkhRequestBean(true)),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        PkhjtqkzpList responseEvent = requestGson.fromJson(dataJsonString, PkhjtqkzpList.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtqkzp);
    }

    private void initView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_page);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mPkhjtqkzpRecyclerAdapter = new PkhjtqkzpRecyclerAdapter();
        recyclerView.setAdapter(mPkhjtqkzpRecyclerAdapter);
    }

    private void initData() {

        final String jdlkStr = getContext().getString(R.string.pkh_jtqkzp_jdlk);
        Map<String,String> values = DictionaryUtil.getValueNames("ZPLX");
        localData = new ArrayList<>();
        for (int i = 8;i < values.size() + 1;i++) {
            String valueName = values.get(String.valueOf(i));
            if (!TextUtils.isEmpty(valueName)&&valueName.startsWith(jdlkStr)) {
                PkhjtqkzpBean bean = new PkhjtqkzpBean();
                bean.setZplx(String.valueOf(i));
                localData.add(bean);
            }
        }
        mPkhjtqkzpRecyclerAdapter.notifyResult(true, localData);
    }

    private String encodePhoto(String addr) {
        byte[] data = null;
        try {
            InputStream in = new FileInputStream(addr);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return EncryptionUtil.base64Encode(data);
    }
}
