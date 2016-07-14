package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.TzjtcyBean;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.utils.DictionaryUtil;

/**
 * 台账支出明细
 */
public class TzzcmxViewHolder extends BaseViewHolder {

    private Context mContext;
    private TzzcmxBean mPkhjtcyBean;
    private EditText xmmc;
    private EditText dkyt;
    private EditText zcyf;
    private EditText zcjey;


    public TzzcmxViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_tzzcmx, parent, false));
        mContext = parent.getContext();
        xmmc = (EditText) itemView.findViewById(R.id.zcmx_xmmc);
        dkyt = (EditText) itemView.findViewById(R.id.zcmx_dkyt);
        zcyf = (EditText) itemView.findViewById(R.id.zcmx_zcyf);
        zcjey = (EditText) itemView.findViewById(R.id.zcmx_zcjey);
    }

    public void onBindData(TzzcmxBean tzzcmxBean) {
        this.mPkhjtcyBean = tzzcmxBean;
        xmmc.setText(tzzcmxBean.getXmmc());
        dkyt.setText(tzzcmxBean.getDkyt());
        zcyf.setText(tzzcmxBean.getZcyf());
        zcjey.setText(tzzcmxBean.getZcjey());
    }
}
