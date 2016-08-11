package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.TzzfqkRecyclerAdapter;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.BaseBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.model.bean.TzzfqkBean;
import cn.deepai.evillage.model.bean.TzzfqkList;
import cn.deepai.evillage.model.event.PagexjItemEvent;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.model.event.TzzfqkClickEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账走访信息
 */
public class TzzfxxPage extends BasePage implements BasePage.IDataEdit{

    private String tzId;
    private String tznd;
    private List<TzzfqkBean> mTzzfqkList;
    private TzzfqkRecyclerAdapter mTzzfqkRecyclerAdapter;
    private RecyclerView mRecyclerView;

    public TzzfxxPage(Context context) {
        this(context, null, null, null);
    }

    public TzzfxxPage(Context context, String id, String nd) {
        this(context, null, id, nd);
    }

    public TzzfxxPage(Context context, AttributeSet attrs, String id, String nd) {
        this(context, attrs, 0, id, nd);
    }

    public TzzfxxPage(Context context, AttributeSet attrs, int defStyle, String id, String nd) {
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


    /**
     * 获取到页面信息
     */
    @SuppressWarnings("all")
    public void onEventMainThread(TzzfqkList event) {
        if (isSelected()) {
            this.mTzzfqkList = event.list;
            mTzzfqkRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }

    /**
     * 新建新条目
     */
    @SuppressWarnings("all")
    public void onEventMainThread(PagexjItemEvent event) {
        if (isSelected()) {
            List<TzzfqkBean> itemList = new ArrayList<>();
            itemList.add(new TzzfqkBean());
            mTzzfqkRecyclerAdapter.notifyResult(false, itemList);
            mRecyclerView.scrollToPosition(mTzzfqkRecyclerAdapter.getItemCount() - 1);
            mHasData = true;
        }
    }

    /**
     * 点击某个条目
     */
    @SuppressWarnings("all")
    public void onEventMainThread(final TzzfqkClickEvent event) {
        if (isSelected()) {
            switch (event.viewId) {
                case R.id.zfqk_zfsj:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_zfqk_zfsj)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzzfqkBean bean: mTzzfqkList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setZfsj(data);
                                            bean.beanState = BaseBean.EDIT;
                                        }
                                    }
                                    mTzzfqkRecyclerAdapter.notifyResult(true, mTzzfqkList);
                                }
                            });
                    break;
                case R.id.zfqk_lsqk:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_zfqk_lsqk)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzzfqkBean bean: mTzzfqkList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setLsqk(data);
                                            bean.beanState = BaseBean.EDIT;
                                        }
                                    }
                                    mTzzfqkRecyclerAdapter.notifyResult(true, mTzzfqkList);
                                }
                            });
                    break;
                case R.id.zfqk_bfcx:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_zfqk_bfcx)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzzfqkBean bean: mTzzfqkList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setBfcx(data);
                                            bean.beanState = BaseBean.EDIT;
                                        }
                                    }
                                    mTzzfqkRecyclerAdapter.notifyResult(true, mTzzfqkList);
                                }
                            });
                    break;
                case R.id.zfqk_sfzsy:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_zfqk_sfzsy)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzzfqkBean bean: mTzzfqkList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setSfzsy(data);
                                            bean.beanState = BaseBean.EDIT;
                                        }
                                    }
                                    mTzzfqkRecyclerAdapter.notifyResult(true, mTzzfqkList);
                                }
                            });
                    break;
            }
        }
    }

    @Override
    public void saveData() {
        for (final TzzfqkBean bean: mTzzfqkList) {
            if (bean.beanState == BaseBean.EDIT) {
                RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_updateTzzfxx);

                final Gson gson = new Gson();
                EVRequest.request(Action.ACTION_UPDATE_TZZFQK, gson.toJson(header), gson.toJson(bean),
                        new ResponseCallback() {
                            @Override
                            public void onDataResponse(String dataJsonString) {
                                ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
                                if (returnValueEvent.returnValue == ReturnValueEvent.SUCCESS) bean.beanState = BaseBean.NORMAL;
                                EventBus.getDefault().post(returnValueEvent);
                            }
                        });
            }
        }
    }

    @Override
    public void requestData() {
        JSONObject jsonObject = new JSONObject();

        try {
            String hid = SettingManager.getCurrentPkh().getHid();
            jsonObject.put("hid",hid);

            jsonObject.put("tznd", tznd);
        } catch (JSONException e) {
            LogUtil.e(EVRequest.class, "Illegal json format:" + e.toString());
            return;
        }
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_TZJHLSLIST,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getTzBfjh)),
                jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzzfqkList responseEvent = requestGson.fromJson(dataJsonString, TzzfqkList.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.tz_zfxx);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_page);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTzzfqkRecyclerAdapter = new TzzfqkRecyclerAdapter();
        mRecyclerView.setAdapter(mTzzfqkRecyclerAdapter);
        mHasData = false;
    }
}
