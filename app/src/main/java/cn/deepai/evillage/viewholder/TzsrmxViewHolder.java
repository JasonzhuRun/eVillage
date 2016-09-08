package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.TzsrmxBean;
import cn.deepai.evillage.model.event.TzsrmxClickEvent;
import cn.deepai.evillage.utils.LogUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账收入明细
 */
public class TzsrmxViewHolder extends BaseViewHolder {

    private Context mContext;
    private TzsrmxBean mTzsrmxBean;
    private EditText xmmc;
    private EditText xmgm;
    private EditText clgjxsn;
    private EditText nsry;


    public TzsrmxViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_tzsrmx, parent, false));
        mContext = parent.getContext();
        xmmc = (EditText) itemView.findViewById(R.id.srmx_xmmc);
        xmmc.setOnClickListener(this);
        xmgm = (EditText) itemView.findViewById(R.id.srmx_xmgm);
        xmgm.setOnClickListener(this);
        clgjxsn = (EditText) itemView.findViewById(R.id.srmx_clgj);
        clgjxsn.setOnClickListener(this);
        nsry = (EditText) itemView.findViewById(R.id.srmx_nsry);
        nsry.setOnClickListener(this);
    }

    public void onBindData(TzsrmxBean tzsrmxBean) {
        this.mTzsrmxBean = tzsrmxBean;
        xmmc.setText(tzsrmxBean.getXmmc());
        xmgm.setText(tzsrmxBean.getXmgm());
        clgjxsn.setText(tzsrmxBean.getCglj());
        nsry.setText(tzsrmxBean.getNsry());

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        TzsrmxClickEvent event = new TzsrmxClickEvent();
        event.viewId = v.getId();
        event.id = mTzsrmxBean.getId();
        EventBus.getDefault().post(event);
        LogUtil.d(TzsrmxViewHolder.class,v.toString()+" is onClick");
    }
}
