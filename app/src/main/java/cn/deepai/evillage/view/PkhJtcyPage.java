package cn.deepai.evillage.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhRecyclerAdapter;
import cn.deepai.evillage.adapter.PkhjtcyRecyclerAdapter;
import cn.deepai.evillage.model.NewsBean;
import cn.deepai.evillage.model.PkhjtcyBean;
import cn.deepai.evillage.viewholder.BaseViewHolder;

/**
 * @author GaoYixuan
 */
public class PkhJtcyPage extends PkhBasePage{

    public PkhJtcyPage(Context context) {
        this(context,null);
    }
    public PkhJtcyPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJtcyPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjtcy, this);
        initView();
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtcy);
    }

    private void initView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pkh_jtcy);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new PkhjtcyRecyclerAdapter());
    }
}
