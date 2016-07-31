package cn.deepai.evillage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.deepai.evillage.model.bean.ItemType;
import cn.deepai.evillage.model.bean.TzjtcyBean;
import cn.deepai.evillage.model.bean.TzsrmxBean;
import cn.deepai.evillage.viewholder.AddMoreViewHolder;
import cn.deepai.evillage.viewholder.BaseViewHolder;
import cn.deepai.evillage.viewholder.TzjtcyViewHolder;
import cn.deepai.evillage.viewholder.TzsrmxViewHolder;

/**
 * 贫困户家庭成员
 */
public class TzsrmxRecyclerAdapter extends RecyclerView.Adapter {

    private List<TzsrmxBean> mTzsrmxBeans = new ArrayList<>();

    public void notifyResult(boolean isFirstPage, List<TzsrmxBean> tzsrmxBean) {
        if (isFirstPage) {
            mTzsrmxBeans.clear();
        }
        mTzsrmxBeans.addAll(tzsrmxBean);
        notifyDataSetChanged();
    }

    public void notifyResult(boolean isFirstPage, TzsrmxBean tzsrmxBean) {
        if (isFirstPage) {
            mTzsrmxBeans.clear();
        }
        mTzsrmxBeans.add(tzsrmxBean);
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.ADD_MORE) return new AddMoreViewHolder(parent);
        return new TzsrmxViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TzsrmxViewHolder) {
            ((TzsrmxViewHolder) holder).onBindData(mTzsrmxBeans.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        int count = mTzsrmxBeans == null?0: mTzsrmxBeans.size();
        return count + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return ItemType.ADD_MORE;
        return ItemType.NORMAL;
    }

}
