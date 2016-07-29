package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.TzxqActivity;
import cn.deepai.evillage.model.bean.TzjbxxBean;

/**
 * 台账信息列表
 */
public class TzViewHolder extends BaseViewHolder {

    private Context mContext;
    private TzjbxxBean mTzjbxxBean;

    public TzViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_tz,parent,false));
        mContext = parent.getContext();
    }

    public void onBindData(TzjbxxBean tzjbxxBean) {
        this.mTzjbxxBean = tzjbxxBean;
        TextView tznd = (TextView)itemView.findViewById(R.id.item_tznd);
        tznd.setText(tzjbxxBean.getTznd());
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(mContext, TzxqActivity.class);
        intent.putExtra("tznd", mTzjbxxBean.getTznd());
        intent.putExtra("tzId", mTzjbxxBean.getId());
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
