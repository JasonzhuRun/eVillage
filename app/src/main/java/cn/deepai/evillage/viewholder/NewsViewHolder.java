package cn.deepai.evillage.viewholder;

import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.NewsBean;

/**
 * @author GaoYixuan
 */
public class NewsViewHolder extends BaseViewHolder {

    private NewsBean mNewsBean;
    public ImageView photo;
    public TextView title;
    public TextView time;

    public NewsViewHolder(ViewGroup parent, int viewType) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_news,parent,false));
        photo = (ImageView)itemView.findViewById(R.id.item_news_photo);
        title = (TextView)itemView.findViewById(R.id.item_news_title);
        time = (TextView)itemView.findViewById(R.id.item_news_time);
    }

    public void onBindData(NewsBean newsBean) {
        this.mNewsBean = newsBean;
        byte[] temp = Base64.decode(newsBean.getBz(),Base64.DEFAULT);
        photo.setImageBitmap(BitmapFactory.decodeByteArray(temp, 0, temp.length));
        title.setText(newsBean.getTitle());
        time.setText(newsBean.getPolicyDate());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        mNewsBean.getId();
    }
}
