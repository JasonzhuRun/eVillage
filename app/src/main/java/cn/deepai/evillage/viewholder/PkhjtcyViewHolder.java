package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.PkhjtcyActivity;
import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.PkhjtcyBean;
import cn.deepai.evillage.utils.DictionaryUtil;

/**
 * 贫困户家庭成员
 */
public class PkhjtcyViewHolder extends BaseViewHolder {

    private Context mContext;
    private PkhjtcyBean mPkhjtcyBean;
    private TextView xm;
    private TextView gx;

    public PkhjtcyViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkhjtcy,parent,false));
        xm = (TextView)itemView.findViewById(R.id.item_pkhjtcy_xm);
        gx = (TextView)itemView.findViewById(R.id.item_pkhjtcy_gx);
        mContext = parent.getContext();
    }

    public void onBindData(PkhjtcyBean pkhjtcyBean) {
        this.mPkhjtcyBean = pkhjtcyBean;
        xm.setText(pkhjtcyBean.getXm());
        gx.setText(DictionaryUtil.getValueName("YHZGX",pkhjtcyBean.getYhzgx()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent(mContext, PkhjtcyActivity.class);
        intent.putExtra("id",mPkhjtcyBean.getId());
        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
