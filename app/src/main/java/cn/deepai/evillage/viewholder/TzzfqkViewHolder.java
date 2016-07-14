package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.model.bean.TzzfqkBean;

/**
 * 台账支出明细
 */
public class TzzfqkViewHolder extends BaseViewHolder {

    private Context mContext;
    private TzzfqkBean mTzzfqkBean;
    private EditText xmmc;
    private EditText jhny;
    private EditText jhnr;
    private EditText zfsj;
    private EditText lsqk;
    private EditText bfcx;
    private EditText sfzsy;


    public TzzfqkViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_tzzfqk, parent, false));
        mContext = parent.getContext();
        xmmc = (EditText) itemView.findViewById(R.id.zfqk_xmmc);
        jhny = (EditText) itemView.findViewById(R.id.zfqk_jhny);
        jhnr = (EditText) itemView.findViewById(R.id.zfqk_jhnr);
        zfsj = (EditText) itemView.findViewById(R.id.zfqk_zfsj);
        lsqk = (EditText) itemView.findViewById(R.id.zfqk_lsqk);
        bfcx = (EditText) itemView.findViewById(R.id.zfqk_bfcx);
        sfzsy = (EditText) itemView.findViewById(R.id.zfqk_sfzsy);
    }

    public void onBindData(TzzfqkBean tzzfqkBean) {
        this.mTzzfqkBean = tzzfqkBean;
        xmmc.setText(tzzfqkBean.getXmmc());
        jhny.setText(tzzfqkBean.getJhny());
        jhnr.setText(tzzfqkBean.getJhnr());
        zfsj.setText(tzzfqkBean.getZfsj());
        lsqk.setText(tzzfqkBean.getLsqk());
        bfcx.setText(tzzfqkBean.getBfcx());
        sfzsy = (EditText) itemView.findViewById(R.id.zfqk_sfzsy);
    }
}
