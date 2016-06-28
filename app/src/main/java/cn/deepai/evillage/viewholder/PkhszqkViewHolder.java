package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.PkhszqkActivity;
import cn.deepai.evillage.model.bean.PkhszqkBean;

/**
 * 贫困户家庭成员
 */
public class PkhszqkViewHolder extends BaseViewHolder {

    private Context mContext;
    private PkhszqkBean mPkhszqkBean;
    private TextView sznd;
    private TextView jtzsr;
    private TextView jtcsr;
    private TextView rjcsr;

    public PkhszqkViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkhszqk,parent,false));
        mContext = parent.getContext();
        sznd = (TextView)itemView.findViewById(R.id.item_szqk_sznd);
        jtzsr = (TextView)itemView.findViewById(R.id.item_szqk_jtzsr);
        jtcsr = (TextView)itemView.findViewById(R.id.item_szqk_jtcsr);
        rjcsr = (TextView)itemView.findViewById(R.id.item_szqk_rjcsr);
    }

    public void onBindData(PkhszqkBean pkhszqkBean) {
        this.mPkhszqkBean = pkhszqkBean;
        sznd.setText(String.valueOf(pkhszqkBean.getTjnd()));
        jtzsr.setText(String.valueOf(pkhszqkBean.getJtzsr()));
        jtcsr.setText(String.valueOf(pkhszqkBean.getJtcsr()));
        rjcsr.setText(String.valueOf(pkhszqkBean.getJtnrjcsr()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        mPkhszqkBean.getId();
        Intent intent = new Intent(mContext, PkhszqkActivity.class);
        intent.putExtra("id",mPkhszqkBean.getId());
        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
