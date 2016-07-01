package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.PkhxqActivity;
import cn.deepai.evillage.model.bean.PkhjbxxBean;
import cn.deepai.evillage.manager.SettingManager;

/**
 * 贫困户列表
 */
public class PkhViewHolder extends BaseViewHolder {

    private Context mContext;
    private PkhjbxxBean mPkhjbxxBean;
    public ImageView photo;
    public TextView name;
    public TextView address;

    public PkhViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkh,parent,false));
        mContext = parent.getContext();
        photo = (ImageView)itemView.findViewById(R.id.item_person_photo);
        name = (TextView)itemView.findViewById(R.id.item_person_name);
        address = (TextView)itemView.findViewById(R.id.item_person_address);
    }

    public void onBindData(PkhjbxxBean pkhjbxxBean) {
        this.mPkhjbxxBean = pkhjbxxBean;
        ImageLoader.getInstance().displayImage(pkhjbxxBean.getBz(),photo, EVApplication.getDisplayImageOptions());
        name.setText(pkhjbxxBean.getHzxm());
        address.setText(pkhjbxxBean.getJzdz());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        SettingManager.getInstance().setCurrentHid(mPkhjbxxBean.getHid());
        Intent intent = new Intent(mContext, PkhxqActivity.class);
        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
