package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.viewholder.AddMoreViewHolder;
import cn.deepai.evillage.viewholder.BaseViewHolder;
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
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.ADD_MORE) return new AddMoreViewHolder(parent);
        else return new TzzcmxViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TzzcmxViewHolder) {
            ((TzzcmxViewHolder) holder).onBindData(mTzzcmxBeans.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        int count = mTzzcmxBeans == null?0: mTzzcmxBeans.size();
        return count + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return ItemType.ADD_MORE;
        return ItemType.NORMAL;
    }

}
