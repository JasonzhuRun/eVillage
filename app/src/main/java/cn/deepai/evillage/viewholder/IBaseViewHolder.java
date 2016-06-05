package cn.deepai.evillage.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.deepai.evillage.items.BaseItem;

/**
 * @author GaoYixuan
 */

public interface IBaseViewHolder<T extends BaseItem>{

    View creatView(LayoutInflater inflater, ViewGroup viewGroup);

    void onBindViewHolder(int position, T item);
}
