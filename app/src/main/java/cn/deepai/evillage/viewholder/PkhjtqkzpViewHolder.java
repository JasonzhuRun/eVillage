package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

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
    private ViewGroup mParent;
    private PkhjtqkzpBean mPkhjtqkzpBean;
    private PopupWindow mPopupWindow;
    private ImageView zp;
    private ImageView mPopZp;
    private int viewType;
    public PkhjtqkzpViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkhjtqkzp,parent,false));
        mContext = parent.getContext();
        mParent = parent;
        initPopupWindow();
        zp = (ImageView) itemView.findViewById(R.id.item_jtqkzp_zp);
        this.viewType = viewType;
        if (viewType == PkhjtqkzpRecyclerAdapter.TYPT_ADD_MORE) {
            zp.setImageResource(R.drawable.add);
        }
    }

    public void onBindData(PkhjtqkzpBean pkhjtqkzpBean) {
        this.mPkhjtqkzpBean = pkhjtqkzpBean;
        ImageLoader.getInstance().displayImage(pkhjtqkzpBean.getTpdz(),zp, EVApplication.getDisplayImageOptions());
        ImageLoader.getInstance().displayImage(pkhjtqkzpBean.getTpdz(), mPopZp, EVApplication.getDisplayImageOptions());
    }

    @Override
    public void onClick(View v) {
        // 点击添加
        if (viewType == PkhjtqkzpRecyclerAdapter.TYPT_ADD_MORE) {

        } else {
            showWindow();
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

    private void initPopupWindow() {
        if (mPopupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View root = layoutInflater.inflate(R.layout.popup_window_pkhjtqkzp, mParent,false);
            mPopZp = (ImageView)root.findViewById(R.id.popup_view_img);
            Button closeBtn = (Button)root.findViewById(R.id.popup_btn_close);
            mPopupWindow = new PopupWindow(root, mParent.getWidth(), mParent.getHeight());
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
        }
    }

    private void showWindow() {
        // 使其聚集
        mPopupWindow.setFocusable(true);
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(mParent, Gravity.CENTER,0,0);
    }
}
