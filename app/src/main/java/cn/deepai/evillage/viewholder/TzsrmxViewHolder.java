package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.TzsrmxBean;

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
        xmgm = (EditText) itemView.findViewById(R.id.srmx_xmgm);
        clgjxsn = (EditText) itemView.findViewById(R.id.srmx_clgj);
        nsry = (EditText) itemView.findViewById(R.id.srmx_nsry);
    }

    public void onBindData(TzsrmxBean tzsrmxBean) {
        this.mTzsrmxBean = tzsrmxBean;
        xmmc.setText(tzsrmxBean.getXmmc());
                xmgm.setText(tzsrmxBean.getXmgm());
        clgjxsn.setText(tzsrmxBean.getClgj());
                nsry.setText(tzsrmxBean.getNsry());

    }
}
