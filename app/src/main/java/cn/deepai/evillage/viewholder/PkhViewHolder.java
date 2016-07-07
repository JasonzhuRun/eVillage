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

import butterknife.ButterKnife;
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

    public PkhViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkh,parent,false));
        mContext = parent.getContext();
    }

    public void onBindData(PkhjbxxBean pkhjbxxBean) {
        this.mPkhjbxxBean = pkhjbxxBean;
        ImageView photo = (ImageView)itemView.findViewById(R.id.item_person_photo);
        TextView name = (TextView)itemView.findViewById(R.id.item_person_name);
        TextView address = (TextView)itemView.findViewById(R.id.item_person_address);
        TextView phone = (TextView)itemView.findViewById(R.id.item_person_phone);
        ImageLoader.getInstance().displayImage(pkhjbxxBean.getZp(),photo, EVApplication.getDisplayImageOptions());
        name.setText(pkhjbxxBean.getHzxm());
        address.setText(pkhjbxxBean.getJzdz());
        phone.setText(pkhjbxxBean.getLxdh());
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
