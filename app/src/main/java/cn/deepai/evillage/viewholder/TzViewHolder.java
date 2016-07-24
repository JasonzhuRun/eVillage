package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.TzRecyclerAdapter;
import cn.deepai.evillage.controller.activity.TzxqActivity;
import cn.deepai.evillage.manager.DialogManager;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.model.event.TzXjNdtzEvent;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 台账信息列表
 */
public class TzViewHolder extends BaseViewHolder {

    private Context mContext;
    private TzjbxxBean mTzjbxxBean;
    private int viewType;

    public TzViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_tz,parent,false));
        mContext = parent.getContext();
        this.viewType = viewType;
    }

    public void onBindData(TzjbxxBean tzjbxxBean) {
        if (tzjbxxBean == null) {
            TextView tznd = (TextView)itemView.findViewById(R.id.item_tznd);
            tznd.setText(mContext.getString(R.string.tz_new_ndtz));
        } else {
            this.mTzjbxxBean = tzjbxxBean;
            TextView tznd = (TextView)itemView.findViewById(R.id.item_tznd);
            tznd.setText(tzjbxxBean.getTznd());
        }
    }

    @Override
    public void onClick(View v) {
        if (viewType == TzRecyclerAdapter.ADD_MORE) {
            DialogManager.showEditTextDialog(mContext,mContext.getString(R.string.tz_new_ndtz_tip), new DialogManager.IOnDialogFinished() {
                @Override
                public void returnData(String data) {
                    if (!TextUtils.isEmpty(data)) {
                        EventBus.getDefault().post(new TzXjNdtzEvent(data));
                    }
                }
            });
        } else {
            Intent intent = new Intent(mContext, TzxqActivity.class);
            intent.putExtra("tznd", mTzjbxxBean.getTznd());
            intent.putExtra("tzId", mTzjbxxBean.getId());
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        }
    }
}
