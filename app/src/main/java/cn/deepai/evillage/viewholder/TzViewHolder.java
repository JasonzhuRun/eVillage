package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhRecyclerAdapter;
import cn.deepai.evillage.controller.activity.PkhxqActivity;
import cn.deepai.evillage.controller.activity.TzxqActivity;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.model.event.PkhSelectedEvent;
import de.greenrobot.event.EventBus;

/**
 * 台账信息列表
 */
public class TzViewHolder extends BaseViewHolder {

    private Context mContext;
    private TzjbxxBean mTzjbxxBean;

    public TzViewHolder(ViewGroup parent, int viewType) {
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
        intent.putExtra("tzid", mTzjbxxBean.getId());
        mContext.startActivity(intent);
        ((Activity) mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
