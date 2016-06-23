package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtqkzpRecyclerAdapter;
import cn.deepai.evillage.bean.PkhjtqkzpBean;

/**
 * 贫困户家庭情况照片
 */
public class PkhjtqkzpViewHolder extends BaseViewHolder {

    private Context mContext;
    private PkhjtqkzpBean mPkhjtqkzpBean;
    private ImageView zp;
    private int viewType;
    public PkhjtqkzpViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkhjtqkzp,parent,false));
        mContext = parent.getContext();
        zp = (ImageView) itemView.findViewById(R.id.item_jtqkzp_zp);
        this.viewType = viewType;
        if (viewType == PkhjtqkzpRecyclerAdapter.TYPT_ADD_MORE) {
            zp.setImageResource(R.drawable.add);
        }
    }

    public void onBindData(PkhjtqkzpBean pkhjtqkzpBean) {
        this.mPkhjtqkzpBean = pkhjtqkzpBean;
        ImageLoader.getInstance().displayImage(pkhjtqkzpBean.getTpdz(),zp, EVApplication.getDisplayImageOptions());
    }

    @Override
    public void onClick(View v) {
        // 点击添加
        if (viewType == PkhjtqkzpRecyclerAdapter.TYPT_ADD_MORE) {

        }
    }

    @Override
    public boolean onLongClick(View v) {
        // 长按删除
        if (viewType == PkhjtqkzpRecyclerAdapter.TYPT_IMAGE) {

            return true;
        }
        return false;
    }
}
