package cn.deepai.evillage.viewholder;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.PkhjtqkzpBean;
import cn.deepai.evillage.model.event.TakePhotoEvent;
import cn.deepai.evillage.utils.DictionaryUtil;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * 家庭情况照片
 */
public class JtqkzpViewHolder extends BaseViewHolder {

    private Context mContext;
    private ViewGroup mParent;
    private PkhjtqkzpBean mPkhjtqkzpBean;
    private PopupWindow mPopupWindow;
    private ImageView zp;
    private TextView zplx;
    private ImageView mPopZp;
    private int viewType;

    public JtqkzpViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_pkhjtqkzp,parent,false));
        mContext = parent.getContext();
        mParent = parent;
        initPopupWindow();
        zp = (ImageView) itemView.findViewById(R.id.item_jtqkzp_zp);
        zplx = (TextView) itemView.findViewById(R.id.item_jtqkzp_lx);
        this.viewType = viewType;
        if (viewType == ItemType.ADD_MORE) {
            zp.setImageResource(R.drawable.add_photo);
        }
    }

    public void onBindData(PkhjtqkzpBean pkhjtqkzpBean) {
        this.mPkhjtqkzpBean = pkhjtqkzpBean;
        zplx.setText(DictionaryUtil.getValueName("ZPLX",pkhjtqkzpBean.getZplx()));

        String path = pkhjtqkzpBean.getTpdz();
        if (TextUtils.isEmpty(path)) return;
        if (path.startsWith("/")) {
            path = "file://" + path;
        }
        ImageLoader.getInstance().displayImage(path, zp, EVApplication.getDisplayImageOptions());
        ImageLoader.getInstance().displayImage(path, mPopZp, EVApplication.getDisplayImageOptions());
    }

    @Override
    public void onClick(View v) {
        // 点击添加
        if (viewType == ItemType.ADD_MORE) {
            getImageFromCamera();
        } else {
            showWindow();
        }
    }

    @Override
    public boolean onLongClick(View v) {
//        // 长按删除
//        if (viewType == ItemType.NORMAL) {
//            mParent.notify();
//            return true;
//        }
        return false;
    }

    private void initPopupWindow() {
        if (mPopupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View root = layoutInflater.inflate(R.layout.popup_window_pkhjtqkzp, mParent,false);
            mPopZp = (ImageView)root.findViewById(R.id.popup_view_img);
            ImageView closeBtn = (ImageView)root.findViewById(R.id.popup_btn_close);
            mPopupWindow = new PopupWindow(root, dip2px(mContext,300), dip2px(mContext,450));
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });
        }
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void showWindow() {
        // 使其聚集
        mPopupWindow.setFocusable(true);
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(mParent, Gravity.CENTER,0,0);
    }

//
//    private void getImageFromAlbum() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");//相片类型
//        ((PkhxqActivity)mContext).startActivityForResult(intent, 0);
//    }

    private void getImageFromCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            TakePhotoEvent event = new TakePhotoEvent();
            event.zplx = mPkhjtqkzpBean.getZplx();
            EventBus.getDefault().post(event);
        }
        else {
            ToastUtil.shortToast(mContext.getString(R.string.no_sdcard));
        }
    }


}
