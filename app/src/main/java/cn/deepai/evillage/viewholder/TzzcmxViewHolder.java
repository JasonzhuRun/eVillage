package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.model.event.TzjtcyClickEvent;
import cn.deepai.evillage.model.event.TzzcmxClickEvent;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账支出明细
 */
public class TzzcmxViewHolder extends BaseViewHolder {

    private Context mContext;
    private TzzcmxBean mTzzcmxBean;
    private EditText xmmc;
    private EditText dkyt;
    private EditText zcyf;
    private EditText zcjey;


    public TzzcmxViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_tzzcmx, parent, false));
        mContext = parent.getContext();
        xmmc = (EditText) itemView.findViewById(R.id.zcmx_xmmc);
        xmmc.setOnClickListener(this);
        dkyt = (EditText) itemView.findViewById(R.id.zcmx_dkyt);
        dkyt.setOnClickListener(this);
        zcyf = (EditText) itemView.findViewById(R.id.zcmx_zcyf);
        zcyf.setOnClickListener(this);
        zcjey = (EditText) itemView.findViewById(R.id.zcmx_zcjey);
        zcjey.setOnClickListener(this);
    }

    public void onBindData(TzzcmxBean tzzcmxBean) {
        this.mTzzcmxBean = tzzcmxBean;
        xmmc.setText(tzzcmxBean.getXmmc());
        dkyt.setText(tzzcmxBean.getDkyt());
        zcyf.setText(tzzcmxBean.getZcyf());
        zcjey.setText(tzzcmxBean.getZcjey());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        TzzcmxClickEvent event = new TzzcmxClickEvent();
        event.viewId = v.getId();
        event.id = mTzzcmxBean.getId();
        EventBus.getDefault().post(event);
        LogUtil.d(TzjtcyViewHolder.class,v.toString()+" is onClick");
    }
}
