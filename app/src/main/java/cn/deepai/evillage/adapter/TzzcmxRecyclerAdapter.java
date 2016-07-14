package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.viewholder.TzjtcyViewHolder;
import cn.deepai.evillage.viewholder.TzzcmxViewHolder;

/**
 * 贫困户家庭成员
 */
public class TzzcmxRecyclerAdapter extends RecyclerView.Adapter {

    private List<TzzcmxBean> mTzzcmxBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<TzzcmxBean> tzjtcyBean) {
        if (isFirstPage) {
            mTzzcmxBeans.clear();
        }
        mTzzcmxBeans.addAll(tzjtcyBean);
        notifyDataSetChanged();
    }

    @Override
    public TzzcmxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TzzcmxViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TzzcmxViewHolder) {
            ((TzzcmxViewHolder) holder).onBindData(mTzzcmxBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mTzzcmxBeans == null?0: mTzzcmxBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
