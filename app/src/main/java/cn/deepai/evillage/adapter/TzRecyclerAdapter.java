package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.manager.SettingManager;
import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.TzjbxxBean;
import cn.deepai.evillage.view.BasePage;
import cn.deepai.evillage.viewholder.AddMoreViewHolder;
import cn.deepai.evillage.viewholder.BaseViewHolder;
import cn.deepai.evillage.viewholder.TzViewHolder;

/**
 * @author GaoYixuan
 */
public class TzRecyclerAdapter extends RecyclerView.Adapter {

    private List<TzjbxxBean> mTzjbxxList = new ArrayList<>();
    private boolean isMute;
    public void notifyResult(boolean isFirstPage, List<TzjbxxBean> tzjbxxList) {
        if (isFirstPage) {
            mTzjbxxList.clear();
        }
        mTzjbxxList.addAll(tzjbxxList);
        notifyDataSetChanged();
    }

    public void beMute(boolean isMute) {
        this.isMute = isMute;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.ADD_MORE) return new AddMoreViewHolder(parent);
        return new TzViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TzViewHolder) {
            ((TzViewHolder) holder).onBindData(mTzjbxxList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (isMute) return 0;
        return mTzjbxxList == null?1: mTzjbxxList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mTzjbxxList.size()) return ItemType.ADD_MORE;
        else return ItemType.NORMAL;
    }

}
