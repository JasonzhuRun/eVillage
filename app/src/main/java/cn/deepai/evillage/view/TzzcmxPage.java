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
import cn.deepai.evillage.adapter.TzzcmxRecyclerAdapter;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.BaseBean;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzsrmxBean;
import cn.deepai.evillage.model.bean.TzxmxxBean;
import cn.deepai.evillage.model.bean.TzxmxxList;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.model.bean.TzzcmxList;
import cn.deepai.evillage.model.event.PagexjItemEvent;
import cn.deepai.evillage.model.event.ReturnValueEvent;
import cn.deepai.evillage.model.event.TzzcmxClickEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.LogUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账支出明细
 */
public class TzzcmxPage extends BasePage implements BasePage.IDataEdit{

    public List<TzxmxxBean> mTzxmxxList;
    private String tzId;
    private String tznd;
    private List<TzzcmxBean> mTzzcmxList;
    private TzzcmxRecyclerAdapter mTzzcmxRecyclerAdapter;
    private RecyclerView mRecyclerView;

    public TzzcmxPage(Context context) {
        this(context, null, null, null);
    }

    public TzzcmxPage(Context context, String id, String nd) {
        this(context, null, id, nd);
    }

    public TzzcmxPage(Context context, AttributeSet attrs, String id, String nd) {
        this(context, attrs, 0, id, nd);
    }

    public TzzcmxPage(Context context, AttributeSet attrs, int defStyle, String id, String nd) {
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
    public void onEventMainThread(TzzcmxList event) {
        if (isSelected()) {
            this.mTzzcmxList = event.list;
            mTzzcmxRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }


    @SuppressWarnings("all")
    public void onEventMainThread(TzxmxxList event) {
        if (isSelected()) {
            mTzxmxxList = event.list;
            String[] xmmcList = new String[mTzxmxxList.size()];
            for (int i = 0;i < mTzxmxxList.size();i++) {
                xmmcList[i] = mTzxmxxList.get(i).getXmmc();
            }
            DialogManager.showSingleChoiceDialog(mContext, mContext.getString(R.string.tz_xmmc),
                    xmmcList,
                    new DialogManager.IOnDialogFinished() {
                        @Override
                        public void returnData(String data) {
//                            final TzjtcyBean bean = new TzjtcyBean();
//                            bean.beanState = BaseBean.NEW;
//                            bean.setXm(data);
//                            final Gson gson = new Gson();
//                            RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_addTzjtcy);
//                            EVRequest.request(Action.ACTION_ADD_JTCYJBXX, gson.toJson(header), gson.toJson(bean),
//                                    new ResponseCallback() {
//                                        @Override
//                                        public void onDataResponse(String dataJsonString) {
//                                            ReturnValueEvent returnValueEvent = gson.fromJson(dataJsonString,ReturnValueEvent.class);
//                                            if (returnValueEvent.returnValue == ReturnValueEvent.SUCCESS) {
//                                                EventBus.getDefault().post(bean);
//                                            }
//                                        }
//                                    });
                        }
                    });
        }
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PagexjItemEvent event) {
        if (isSelected()) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("hid", SettingManager.getCurrentPkh().getHid());
            } catch (JSONException e) {
                LogUtil.e(EVRequest.class, "Illegal json format:" + e.toString());
                return;
            }

            RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_getTzxmxx);

            final Gson gson = new Gson();
            EVRequest.request(Action.ACTION_GET_TZXMXX, gson.toJson(header), jsonObject.toString(),
                    new ResponseCallback() {
                        @Override
                        public void onDataResponse(String dataJsonString) {
                            TzxmxxList tzxmxxList = gson.fromJson(dataJsonString,TzxmxxList.class);
                            EventBus.getDefault().post(tzxmxxList);
                        }
                    });
        }
    }

    @SuppressWarnings("all")
    public void onEventMainThread(final TzzcmxClickEvent event) {
        if (isSelected()) {
            switch (event.viewId) {
                case R.id.zcmx_xmmc:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_zcmx_xmmc)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzzcmxBean bean: mTzzcmxList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setXmmc(data);
                                            bean.beanState = BaseBean.EDIT;
                                        }
                                    }
                                    mTzzcmxRecyclerAdapter.notifyResult(true, mTzzcmxList);
                                }
                            });
                    break;
                case R.id.zcmx_dkyt:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_zcmx_dkyt)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzzcmxBean bean: mTzzcmxList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setDkyt(data);
                                            bean.beanState = BaseBean.EDIT;
                                        }
                                    }
                                    mTzzcmxRecyclerAdapter.notifyResult(true, mTzzcmxList);
                                }
                            });
                    break;
                case R.id.zcmx_zcyf:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_zcmx_zcyf)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzzcmxBean bean: mTzzcmxList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setZcyf(data);
                                            bean.beanState = BaseBean.EDIT;
                                        }
                                    }
                                    mTzzcmxRecyclerAdapter.notifyResult(true, mTzzcmxList);
                                }
                            });
                    break;
                case R.id.zcmx_zcjey:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_zcmx_zcjey)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzzcmxBean bean: mTzzcmxList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setZcjey(data);
                                            bean.beanState = BaseBean.EDIT;
                                        }
                                    }
                                    mTzzcmxRecyclerAdapter.notifyResult(true, mTzzcmxList);
                                }
                            });
                    break;
            }
        }
    }

    @Override
    public void saveData() {
        for (final TzzcmxBean bean: mTzzcmxList) {
            if (bean.beanState == BaseBean.EDIT) {
                RequestHeaderBean header = new RequestHeaderBean(R.string.req_code_updateTzzcmx);

                final Gson gson = new Gson();
                EVRequest.request(Action.ACTION_UPDATE_TZZCMX, gson.toJson(header), gson.toJson(bean),
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
            jsonObject.put("tzid", tzId);
        } catch (JSONException e) {
            LogUtil.e(EVRequest.class, "Illegal json format:" + e.toString());
            return;
        }
        final Gson requestGson = new Gson();
        EVRequest.request(Action.ACTION_GET_TZZCMX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getTzZcmx)),
                jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzzcmxList responseEvent = requestGson.fromJson(dataJsonString, TzzcmxList.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.tz_zcmx);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_page);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTzzcmxRecyclerAdapter = new TzzcmxRecyclerAdapter();
        mRecyclerView.setAdapter(mTzzcmxRecyclerAdapter);
        mHasData = false;
    }
}
