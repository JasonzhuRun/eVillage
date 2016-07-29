package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.TzzfqkBean;
import cn.deepai.evillage.model.event.TzjtcyClickEvent;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

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
//        xmmc = (EditText) itemView.findViewById(R.id.zfqk_xmmc);
        xmmc.setOnClickListener(this);
//        jhny = (EditText) itemView.findViewById(R.id.zfqk_jhny);
        jhny.setOnClickListener(this);
//        jhnr = (EditText) itemView.findViewById(R.id.zfqk_jhnr);
        jhnr.setOnClickListener(this);
        zfsj = (EditText) itemView.findViewById(R.id.zfqk_zfsj);
        zfsj.setOnClickListener(this);
        lsqk = (EditText) itemView.findViewById(R.id.zfqk_lsqk);
        lsqk.setOnClickListener(this);
        bfcx = (EditText) itemView.findViewById(R.id.zfqk_bfcx);
        bfcx.setOnClickListener(this);
        sfzsy = (EditText) itemView.findViewById(R.id.zfqk_sfzsy);
        sfzsy.setOnClickListener(this);
    }

    public void onBindData(TzzfqkBean tzzfqkBean) {
        this.mTzzfqkBean = tzzfqkBean;
        xmmc.setText(tzzfqkBean.getXmmc());
        jhny.setText(tzzfqkBean.getJhny());
        jhnr.setText(tzzfqkBean.getJhnr());
        zfsj.setText(tzzfqkBean.getZfsj());
        lsqk.setText(tzzfqkBean.getLsqk());
        bfcx.setText(tzzfqkBean.getBfcx());
        sfzsy.setText(tzzfqkBean.getSfzsy());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        TzjtcyClickEvent event = new TzjtcyClickEvent();
        event.cyId = mTzzfqkBean.getXmid();
        event.viewId = v.getId();
        EventBus.getDefault().post(event);
        LogUtil.d(TzzfqkViewHolder.class,v.toString()+" is onClick");
    }
}
