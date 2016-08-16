package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.NewsBean;
import cn.deepai.evillage.viewholder.NewsViewHolder;

/**
 * @author GaoYixuan
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter {

    private List<NewsBean> mNewsBean = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<NewsBean> newsBean) {
        if (isFirstPage) {
            mNewsBean.clear();
        }
        mNewsBean.addAll(newsBean);
        notifyDataSetChanged();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NewsViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            ((NewsViewHolder) holder).onBindData(mNewsBean.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mNewsBean == null?0: mNewsBean.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}
