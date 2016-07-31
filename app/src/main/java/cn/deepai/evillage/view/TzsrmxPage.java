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
import cn.deepai.evillage.adapter.TzsrmxRecyclerAdapter;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.model.bean.RequestHeaderBean;
import cn.deepai.evillage.model.bean.TzjtcyBean;
import cn.deepai.evillage.model.bean.TzsrmxBean;
import cn.deepai.evillage.model.bean.TzsrmxList;
import cn.deepai.evillage.model.event.PagexjItemEvent;
import cn.deepai.evillage.model.event.TzsrmxClickEvent;
import cn.deepai.evillage.net.Action;
import cn.deepai.evillage.net.EVRequest;
import cn.deepai.evillage.net.ResponseCallback;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账收入明细
 */
public class TzsrmxPage extends BasePage {

    private String tzId;
    private String tznd;
    private List<TzsrmxBean> mTzsrmxList;
    private TzsrmxRecyclerAdapter mTzsrmxRecyclerAdapter;
    private RecyclerView mRecyclerView;

    public TzsrmxPage(Context context) {
        this(context, null, null, null);
    }

    public TzsrmxPage(Context context, String id, String nd) {
        this(context, null, id, nd);
    }

    public TzsrmxPage(Context context, AttributeSet attrs, String id, String nd) {
        this(context, attrs, 0, id, nd);
    }

    public TzsrmxPage(Context context, AttributeSet attrs, int defStyle, String id, String nd) {
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
    public void onEventMainThread(TzsrmxList event) {
        if (isSelected()) {
            this.mTzsrmxList = event.list;
            mTzsrmxRecyclerAdapter.notifyResult(true, event.list);
            mHasData = true;
        }
    }

    @SuppressWarnings("all")
    public void onEventMainThread(PagexjItemEvent event) {
        if (isSelected()) {
            mTzsrmxRecyclerAdapter.notifyResult(false, new TzsrmxBean());
            mRecyclerView.scrollToPosition(mTzsrmxRecyclerAdapter.getItemCount() - 1);
            mHasData = true;
        }
    }

    @SuppressWarnings("all")
    public void onEventMainThread(final TzsrmxClickEvent event) {
        if (isSelected()) {
            switch (event.viewId) {
                case R.id.srmx_xmmc:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_srmx_xmmc)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzsrmxBean bean:mTzsrmxList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setXmmc(data);
                                        }
                                    }
                                    mTzsrmxRecyclerAdapter.notifyResult(true, mTzsrmxList);
                                }
                            });
                    break;
                case R.id.srmx_xmgm:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_srmx_xmgm)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzsrmxBean bean:mTzsrmxList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setXmgm(data);
                                        }
                                    }
                                    mTzsrmxRecyclerAdapter.notifyResult(true, mTzsrmxList);
                                }
                            });
                    break;
                case R.id.srmx_clgj:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_srmx_clgj)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzsrmxBean bean:mTzsrmxList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setClgj(data);
                                        }
                                    }
                                    mTzsrmxRecyclerAdapter.notifyResult(true, mTzsrmxList);
                                }
                            });
                    break;
                case R.id.srmx_nsry:
                    DialogManager.showEditTextDialog(mContext, mContext.getString(R.string.tz_srmx_nsry)
                            ,new DialogManager.IOnDialogFinished() {
                                @Override
                                public void returnData(String data) {
                                    for (TzsrmxBean bean:mTzsrmxList) {
                                        if (event.id.equals(bean.getId())) {
                                            bean.setNsry(data);
                                        }
                                    }
                                    mTzsrmxRecyclerAdapter.notifyResult(true, mTzsrmxList);
                                }
                            });
                    break;
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
        EVRequest.request(Action.ACTION_GET_TZSRMX,
                requestGson.toJson(new RequestHeaderBean(R.string.req_code_getTzSrmx)),
                jsonObject.toString(),
                new ResponseCallback() {
                    @Override
                    public void onDataResponse(String dataJsonString) {
                        TzsrmxList responseEvent = requestGson.fromJson(dataJsonString, TzsrmxList.class);
                        EventBus.getDefault().post(responseEvent);
                    }
                });
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.tz_srmx);
    }


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_page);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTzsrmxRecyclerAdapter = new TzsrmxRecyclerAdapter();
        mRecyclerView.setAdapter(mTzsrmxRecyclerAdapter);
        mHasData = false;
    }
}
