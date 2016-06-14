package cn.deepai.evillage.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.NewsInfo;
import cn.deepai.evillage.model.PkhjbxxInfo;

/**
 * @author GaoYixuan
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter {

    private List<NewsInfo> mNewsInfos = new ArrayList<>();
    private OnItemClickListener mOnItemClickLitener;

    public void notifyResult(boolean isFirstPage, List<NewsInfo> newsInfos) {
        if (isFirstPage) {
            mNewsInfos.clear();
        }
        mNewsInfos.addAll(newsInfos);
    }

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public PkhViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PkhViewHodler(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item_news,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof PkhViewHodler) {
            ((PkhViewHodler) holder).onBindData(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mNewsInfos == null?0:mNewsInfos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private class PkhViewHodler extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView title;
        public TextView time;

        public PkhViewHodler(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.item_news_photo);
            title = (TextView)itemView.findViewById(R.id.item_person_name);
            time = (TextView)itemView.findViewById(R.id.item_person_address);
        }

        public void onBindData(int position) {
            byte[] temp = Base64.decode(mNewsInfos.get(position).getBz(),Base64.DEFAULT);
            photo.setImageBitmap(BitmapFactory.decodeByteArray(temp, 0, temp.length));
            title.setText(mNewsInfos.get(position).getTitle());
            time.setText(mNewsInfos.get(position).getPolicyDate());
        }
    }

}
