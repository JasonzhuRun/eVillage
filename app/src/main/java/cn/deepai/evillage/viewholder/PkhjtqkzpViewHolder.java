package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.PkhjtqkzpBean;

/**
 * 贫困户家庭情况照片
 */
public class PkhjtqkzpViewHolder extends BaseViewHolder {

    private Context mContext;
    private PkhjtqkzpBean mPkhjtqkzpBean;
    private ImageView zp;

    public PkhjtqkzpViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkhjtqkzp,parent,false));
        mContext = parent.getContext();
        zp = (ImageView) itemView.findViewById(R.id.item_jtqkzp_zp);
    }

    public void onBindData(PkhjtqkzpBean pkhjtqkzpBean) {
        this.mPkhjtqkzpBean = pkhjtqkzpBean;
        byte[] temp = Base64.decode(pkhjtqkzpBean.getTpnr(), Base64.DEFAULT);
        zp.setImageBitmap(BitmapFactory.decodeByteArray(temp, 0, temp.length));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
