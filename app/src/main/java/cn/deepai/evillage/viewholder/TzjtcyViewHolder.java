package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.TzjtcyBean;
import cn.deepai.evillage.utils.DictionaryUtil;

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
        xb = (EditText) itemView.findViewById(R.id.jtcy_xb);
        yhzgx = (EditText) itemView.findViewById(R.id.jtcy_yhzgx);
        jkqk = (EditText) itemView.findViewById(R.id.jtcy_jkqk);
        whcd = (EditText) itemView.findViewById(R.id.jtcy_whcd);
        zy = (EditText) itemView.findViewById(R.id.jtcy_zy);
        zwjn = (EditText) itemView.findViewById(R.id.jtcy_zwjn);
    }

    public void onBindData(TzjtcyBean tzjtcyBean) {
        this.mPkhjtcyBean = tzjtcyBean;
        xm.setText(tzjtcyBean.getXm());
        xb.setText(DictionaryUtil.getValueName("COMMON.GENDER", tzjtcyBean.getXb()));
        yhzgx.setText(DictionaryUtil.getValueName("YHZGX", tzjtcyBean.getYhzgx()));
        jkqk.setText(DictionaryUtil.getValueName("JKQK",tzjtcyBean.getJkqk()));
        whcd.setText(DictionaryUtil.getValueName("WHCD",tzjtcyBean.getJkqk()));
        zy.setText(tzjtcyBean.getZy());
        zwjn.setText(tzjtcyBean.getZwjn());
    }
}
