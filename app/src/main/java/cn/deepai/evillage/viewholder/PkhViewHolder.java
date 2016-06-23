package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.PkhxqActivity;
import cn.deepai.evillage.bean.PkhjbxxBean;

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
//        byte[] temp = Base64.decode(pkhjbxxBean.getBz(),Base64.DEFAULT);
//        photo.setImageBitmap(BitmapFactory.decodeByteArray(temp, 0, temp.length));
        name.setText(pkhjbxxBean.getHzxm());
        address.setText(pkhjbxxBean.getJzdz());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent(mContext, PkhxqActivity.class);
        intent.putExtra(PkhxqActivity.PKH_KEY,mPkhjbxxBean.getHid());
        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
