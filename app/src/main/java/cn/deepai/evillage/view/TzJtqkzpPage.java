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
import cn.deepai.evillage.utils.EncryptionUtil;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public class TzjtqkzpPage extends BasePage implements BasePage.IDataEdit,BasePage.IPhotoEdit{

    private String tzId;
    private String tznd;

    private List<String> mZplxCodeList;
    private List<PkhjtqkzpBean> localData;
    private PkhjtqkzpRecyclerAdapter mPkhjtqkzpRecyclerAdapter;

    public TzjtqkzpPage(Context context) {
        this(context, null,null,null);
    }

    public TzjtqkzpPage(Context context, String id, String nd) {
        this(context, null,id,nd);
    }

    public TzjtqkzpPage(Context context, AttributeSet attrs, String id, String nd) {
        this(context, attrs, 0,id,nd);
    }

    public TzjtqkzpPage(Context context, AttributeSet attrs, int defStyle, String id, String nd) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_recycerview, this);
        tzId = id;
        tznd = nd;
        initView();
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
            localData = event.list;
            mPkhjtqkzpRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }

    @Override
    public void addPhoto(String uri,String zplx) {
        PkhjtqkzpBean bean = new PkhjtqkzpBean();
        bean.setTpdz(uri);
        bean.setHid(SettingManager.getCurrentJdPkh().getHid());
        if(localData==null) {
            localData = new ArrayList<>();
        }
        localData.add(bean);
//        mPkhjtqkzpRecyclerAdapter.notifyResult(false, bean);
        mHasData = true;
    }

    @Override
    public void saveData() {
        final Gson gson = new Gson();
        RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_addPkhJtqkzp);
        for (final PkhjtqkzpBean bean:localData) {
            if (!TextUtils.isEmpty(bean.getHid())) {
                bean.setTpnr(encodePhoto(bean.getTpdz()));
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
                                    bean.setTpnr(null);
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
