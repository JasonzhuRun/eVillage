package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.PkhszqkBean;
import cn.deepai.evillage.viewholder.AddMoreViewHolder;
import cn.deepai.evillage.viewholder.BaseViewHolder;
import cn.deepai.evillage.viewholder.PkhszqkViewHolder;

/**
 * 贫困户家庭成员
 */
public class PkhszqkRecyclerAdapter extends RecyclerView.Adapter {

    private List<PkhszqkBean> mPkhszqkBeans = new ArrayList<>();
    private boolean mEditable;

    public PkhszqkRecyclerAdapter() {
        this(false);
    }

    public PkhszqkRecyclerAdapter(boolean editable) {
        super();
        this.mEditable = editable;
    }

    public void notifyResult(boolean isFirstPage, List<PkhszqkBean> pkhszqkBean) {
        if (isFirstPage) {
            mPkhszqkBeans.clear();
        }
        mPkhszqkBeans.addAll(pkhszqkBean);
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.ADD_MORE) return new AddMoreViewHolder(parent);
        return new PkhszqkViewHolder(parent,mEditable);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PkhszqkViewHolder) {
            if (mEditable) position--;
            ((PkhszqkViewHolder) holder).onBindData(mPkhszqkBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = mPkhszqkBeans == null?0: mPkhszqkBeans.size();
        if (mEditable) itemCount++;
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (mEditable&&position == 0) return ItemType.ADD_MORE;
        return ItemType.NORMAL;
    }

}
