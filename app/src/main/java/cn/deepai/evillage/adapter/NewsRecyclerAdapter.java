package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.NewsInfo;
import cn.deepai.evillage.viewholder.NewsViewHolder;

/**
 * @author GaoYixuan
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter {

    private List<NewsInfo> mNewsInfos = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<NewsInfo> newsInfos) {
        if (isFirstPage) {
            mNewsInfos.clear();
        }
        mNewsInfos.addAll(newsInfos);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NewsViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            ((NewsViewHolder) holder).onBindData(mNewsInfos.get(position));
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


}
