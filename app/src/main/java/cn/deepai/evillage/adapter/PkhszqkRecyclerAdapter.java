package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.viewholder.PkhszqkViewHolder;

/**
 * 贫困户家庭成员
 */
public class PkhszqkRecyclerAdapter extends RecyclerView.Adapter {

    private List<PkhszqkBean> mPkhszqkBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<PkhszqkBean> pkhszqkBean) {
        if (isFirstPage) {
            mPkhszqkBeans.clear();
        }
        mPkhszqkBeans.addAll(pkhszqkBean);
        notifyDataSetChanged();
    }

    @Override
    public PkhszqkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PkhszqkViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PkhszqkViewHolder) {
            ((PkhszqkViewHolder) holder).onBindData(mPkhszqkBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPkhszqkBeans == null?0: mPkhszqkBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
