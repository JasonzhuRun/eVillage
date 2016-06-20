package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.NewsBean;
import cn.deepai.evillage.viewholder.NewsViewHolder;

/**
 * @author GaoYixuan
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter {

    private List<NewsBean> mNewsBeen = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<NewsBean> newsBeen) {
        if (isFirstPage) {
            mNewsBeen.clear();
        }
        mNewsBeen.addAll(newsBeen);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NewsViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            ((NewsViewHolder) holder).onBindData(mNewsBeen.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mNewsBeen == null?0: mNewsBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}
