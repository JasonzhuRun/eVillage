package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhRecyclerAdapter;
import cn.deepai.evillage.controller.activity.PkhxqActivity;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.event.PkhSelectedEvent;
import de.greenrobot.event.EventBus;

/**
 * 贫困户列表
 */
public class PkhViewHolder extends BaseViewHolder {

    private Context mContext;
    private PkhjbxxBean mPkhjbxxBean;
    private int viewType;
    private ImageView photo;
    public PkhViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkh,parent,false));
        mContext = parent.getContext();
        this.viewType = viewType;
        photo = (ImageView)itemView.findViewById(R.id.item_person_photo);
    }

    public void onBindData(PkhjbxxBean pkhjbxxBean) {
        this.mPkhjbxxBean = pkhjbxxBean;
        TextView name = (TextView)itemView.findViewById(R.id.item_person_name);
        TextView address = (TextView)itemView.findViewById(R.id.item_person_address);
        TextView phone = (TextView)itemView.findViewById(R.id.item_person_phone);
        if (viewType == PkhRecyclerAdapter.TYPE_SELECT) {
            String hid = SettingManager.getCurrentPkh().getHid();
            if (mPkhjbxxBean.getHid().equals(hid)) {
                itemView.findViewById(R.id.item_person_layout).setBackgroundColor(Color.parseColor("#7f8daf"));
            } else {
                itemView.findViewById(R.id.item_person_layout).setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        ImageLoader.getInstance().displayImage(pkhjbxxBean.getTpdz(),photo, EVApplication.getDisplayImageOptions());
        name.setText(pkhjbxxBean.getHzxm());
        address.setText(pkhjbxxBean.getJzdz());
        phone.setText(pkhjbxxBean.getLxdh());
    }

    @Override
    public void onClick(View v) {
        if (viewType == PkhRecyclerAdapter.TYPE_SELECT) {
            SettingManager.setCurrentPkh(mPkhjbxxBean);
            EventBus.getDefault().post(new PkhSelectedEvent(mPkhjbxxBean.getHzxm()));
        } else{
            Intent intent = new Intent(mContext, PkhxqActivity.class);

            if (viewType == PkhRecyclerAdapter.TYPE_WRITE) {
                intent.putExtra("editable", true);
                SettingManager.setCurrentJdPkh(mPkhjbxxBean);
            } else {//TYPE_READ
                SettingManager.setCurrentPkh(mPkhjbxxBean);
                EventBus.getDefault().post(new PkhSelectedEvent(mPkhjbxxBean.getHzxm()));
            }
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        }
    }
}
