package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.TzjtcyBean;
import cn.deepai.evillage.model.event.TzjtcyClickEvent;
import cn.deepai.evillage.utils.DictionaryUtil;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账家庭成员
 */
public class TzjtcyViewHolder extends BaseViewHolder {

    private Context mContext;
    private TzjtcyBean mPkhjtcyBean;
    private EditText xm;
    private EditText xb;
    private EditText yhzgx;
    private EditText jkqk;
    private EditText whcd;
    private EditText zy;
    private EditText zwjn;


    public TzjtcyViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_tzjtcy, parent, false));
        mContext = parent.getContext();
        xm = (EditText) itemView.findViewById(R.id.jtcy_xm);
        xm.setOnClickListener(this);
        xb = (EditText) itemView.findViewById(R.id.jtcy_xb);
        xb.setOnClickListener(this);
        yhzgx = (EditText) itemView.findViewById(R.id.jtcy_yhzgx);
        yhzgx.setOnClickListener(this);
        jkqk = (EditText) itemView.findViewById(R.id.jtcy_jkqk);
        jkqk.setOnClickListener(this);
        whcd = (EditText) itemView.findViewById(R.id.jtcy_whcd);
        whcd.setOnClickListener(this);
        zy = (EditText) itemView.findViewById(R.id.jtcy_zy);
        zy.setOnClickListener(this);
        zwjn = (EditText) itemView.findViewById(R.id.jtcy_zwjn);
        zwjn.setOnClickListener(this);
    }

    public void onBindData(TzjtcyBean tzjtcyBean) {
        this.mPkhjtcyBean = tzjtcyBean;
        xm.setText(tzjtcyBean.getXm());
        xb.setText(DictionaryUtil.getValueName("COMMON.GENDER", tzjtcyBean.getXb()));
        yhzgx.setText(DictionaryUtil.getValueName("YHZGX", tzjtcyBean.getYhzgx()));
        jkqk.setText(DictionaryUtil.getValueName("JKQK",tzjtcyBean.getJkqk()));
        whcd.setText(DictionaryUtil.getValueName("WHCD",tzjtcyBean.getWhcd()));
        zy.setText(tzjtcyBean.getZy());
        zwjn.setText(tzjtcyBean.getZwjn());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        TzjtcyClickEvent event = new TzjtcyClickEvent();
        event.cyId = mPkhjtcyBean.getId();
        event.viewId = v.getId();
        EventBus.getDefault().post(event);
        LogUtil.d(TzjtcyViewHolder.class,v.toString()+" is onClick");
    }
}
