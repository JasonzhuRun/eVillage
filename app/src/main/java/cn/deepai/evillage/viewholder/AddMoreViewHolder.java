package cn.deepai.evillage.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.deepai.evillage.R;
import cn.deepai.evillage.model.event.PagexjItemEvent;
import cn.deepai.evillage.model.event.TzxjtzEvent;
import de.greenrobot.event.EventBus;

/**
 * 添加条目
 */
public class AddMoreViewHolder extends BaseViewHolder {

    private View mParent;
    public AddMoreViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_add_more,parent,false));
        this.mParent = parent;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (mParent.getId()) {
            case R.id.recyclerview_tz:
                EventBus.getDefault().post(new TzxjtzEvent());
                break;
            case R.id.recyclerview_page:
                EventBus.getDefault().post(new PagexjItemEvent());
                break;
            default:
                break;
        }
    }
}
