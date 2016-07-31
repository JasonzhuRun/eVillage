package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.PkhjtqkzpBean;
import cn.deepai.evillage.viewholder.PkhjtqkzpViewHolder;

/**
 * 贫困户家庭情况照片适配器
 */
public class PkhjtqkzpRecyclerAdapter extends RecyclerView.Adapter {

    private boolean mEditable;
    private List<PkhjtqkzpBean> mPkhjtqkzpBeans = new ArrayList<>();

    public PkhjtqkzpRecyclerAdapter(boolean editable) {
        super();
        mEditable = editable;
    }

    public void notifyResult(boolean isFirstPage, List<PkhjtqkzpBean> pkhjtqkzpBean) {
        if (isFirstPage) {
            mPkhjtqkzpBeans.clear();
        }
        mPkhjtqkzpBeans.addAll(pkhjtqkzpBean);
        notifyDataSetChanged();
    }

    public void notifyResult(boolean isFirstPage, PkhjtqkzpBean pkhjtqkzpBean) {
        if (isFirstPage) {
            mPkhjtqkzpBeans.clear();
        }
        mPkhjtqkzpBeans.add(pkhjtqkzpBean);
        notifyDataSetChanged();
    }

    @Override
    public PkhjtqkzpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PkhjtqkzpViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mPkhjtqkzpBeans == null||position == mPkhjtqkzpBeans.size()) {
            return;
        }
        if (holder instanceof PkhjtqkzpViewHolder) {
            ((PkhjtqkzpViewHolder) holder).onBindData(mPkhjtqkzpBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = mPkhjtqkzpBeans == null?0: mPkhjtqkzpBeans.size();
        if (mEditable) itemCount++;
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (mEditable&&(mPkhjtqkzpBeans == null||position == mPkhjtqkzpBeans.size())) {
            return ItemType.ADD_MORE;
        }
        return ItemType.NORMAL;
    }

}
