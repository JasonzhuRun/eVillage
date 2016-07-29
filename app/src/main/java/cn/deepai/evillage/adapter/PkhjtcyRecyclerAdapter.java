package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.PkhjtcyBean;
import cn.deepai.evillage.viewholder.BaseViewHolder;
import cn.deepai.evillage.viewholder.PkhjtcyViewHolder;

/**
 * 贫困户家庭成员
 */
public class PkhjtcyRecyclerAdapter extends RecyclerView.Adapter {

    private List<PkhjtcyBean> mPkhjtcyBeans = new ArrayList<>();
    private boolean mEditable = false;

    public PkhjtcyRecyclerAdapter() {
        this(false);
    }

    public PkhjtcyRecyclerAdapter(boolean editable) {
        super();
//        this.mEditable = editable;
    }

    public void notifyResult(boolean isFirstPage, List<PkhjtcyBean> pkhjtcyBean) {
        if (isFirstPage) {
            mPkhjtcyBeans.clear();
        }
        mPkhjtcyBeans.addAll(pkhjtcyBean);
        notifyDataSetChanged();
    }

    @Override
    public PkhjtcyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PkhjtcyViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PkhjtcyViewHolder) {
            ((PkhjtcyViewHolder) holder).onBindData(mPkhjtcyBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = mPkhjtcyBeans == null?0: mPkhjtcyBeans.size();
        if (mEditable) itemCount++;
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (mEditable&&position == 0) return ItemType.ADD_MORE;
        return ItemType.NORMAL;
    }
}
