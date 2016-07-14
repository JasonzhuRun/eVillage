package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.TzjtcyBean;
import cn.deepai.evillage.viewholder.TzjtcyViewHolder;

/**
 * 贫困户家庭成员
 */
public class TzjtcyRecyclerAdapter extends RecyclerView.Adapter {

    private List<TzjtcyBean> mTzjtcyBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<TzjtcyBean> tzjtcyBean) {
        if (isFirstPage) {
            mTzjtcyBeans.clear();
        }
        mTzjtcyBeans.addAll(tzjtcyBean);
        notifyDataSetChanged();
    }

    @Override
    public TzjtcyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TzjtcyViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TzjtcyViewHolder) {
            ((TzjtcyViewHolder) holder).onBindData(mTzjtcyBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mTzjtcyBeans == null?0: mTzjtcyBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
