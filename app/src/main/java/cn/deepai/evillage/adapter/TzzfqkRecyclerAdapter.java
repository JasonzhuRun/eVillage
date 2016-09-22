package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.TzzcmxBean;
import cn.deepai.evillage.model.bean.TzzfqkBean;
import cn.deepai.evillage.viewholder.AddMoreViewHolder;
import cn.deepai.evillage.viewholder.BaseViewHolder;
import cn.deepai.evillage.viewholder.TzzcmxViewHolder;
import cn.deepai.evillage.viewholder.TzzfqkViewHolder;

/**
 * 贫困户走访情况
 */
public class TzzfqkRecyclerAdapter extends RecyclerView.Adapter {

    private List<TzzfqkBean> mTzzfqkBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<TzzfqkBean> tzzfqkBeans) {
        if (isFirstPage) {
            mTzzfqkBeans.clear();
        }
        mTzzfqkBeans.addAll(tzzfqkBeans);
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.ADD_MORE) return new AddMoreViewHolder(parent);
        else return new TzzfqkViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TzzfqkViewHolder) {
            ((TzzfqkViewHolder) holder).onBindData(mTzzfqkBeans.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        int count = mTzzfqkBeans == null?0: mTzzfqkBeans.size();
        return count + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return ItemType.ADD_MORE;
        return ItemType.NORMAL;
    }

}
