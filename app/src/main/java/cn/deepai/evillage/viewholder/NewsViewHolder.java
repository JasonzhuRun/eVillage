package cn.deepai.evillage.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.deepai.evillage.EVApplication;
import cn.deepai.evillage.R;
import cn.deepai.evillage.controller.activity.NewsDetailActivity;
import cn.deepai.evillage.controller.activity.PkhjtcyActivity;
import cn.deepai.evillage.model.bean.NewsBean;

/**
 * @author GaoYixuan
 */
public class NewsViewHolder extends BaseViewHolder {

    private Context mContext;

    private NewsBean mNewsBean;
    public ImageView photo;
    public TextView title;
    public TextView content;
    public TextView time;

    public NewsViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_news,parent,false));
        photo = (ImageView)itemView.findViewById(R.id.item_news_photo);
        title = (TextView)itemView.findViewById(R.id.item_news_title);
        content = (TextView)itemView.findViewById(R.id.item_news_content);
        time = (TextView)itemView.findViewById(R.id.item_news_time);
        mContext = parent.getContext();
    }

    public void onBindData(NewsBean newsBean) {
        this.mNewsBean = newsBean;
        ImageLoader.getInstance().displayImage(newsBean.getBz(),photo, EVApplication.getDisplayImageOptions());
        title.setText(newsBean.getTitle());
        content.setText(newsBean.getPolicy());
        time.setText(newsBean.getPolicyDate());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        mNewsBean.getId();
        Intent intent = new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra("news",mNewsBean);
        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
