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
import cn.deepai.evillage.controller.activity.MsgDetailActivity;
import cn.deepai.evillage.controller.activity.NewsDetailActivity;
import cn.deepai.evillage.model.bean.MsgBean;
import cn.deepai.evillage.model.bean.NewsBean;

/**
 * @author GaoYixuan
 */
public class MsgViewHolder extends BaseViewHolder {

    private Context mContext;

    private MsgBean mMsgBean;
    public TextView title;
    public TextView content;
    public TextView time;

    public MsgViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_msg,parent,false));
        title = (TextView)itemView.findViewById(R.id.item_msg_title);
        content = (TextView)itemView.findViewById(R.id.item_msg_content);
        time = (TextView)itemView.findViewById(R.id.item_msg_time);
        mContext = parent.getContext();
    }

    public void onBindData(MsgBean msgBean) {
        this.mMsgBean = msgBean;
        title.setText(msgBean.getBulletin_title());
        content.setText(msgBean.getBulletin_content1());
        time.setText(msgBean.getPublish_time());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent(mContext, MsgDetailActivity.class);
        intent.putExtra("msg", mMsgBean);
        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
