package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import cn.deepai.evillage.R;
import cn.deepai.evillage.adapter.PkhjtqkzpRecyclerAdapter;

/**
 * @author GaoYixuan
 */
public class PkhJtqkzpPage extends PkhBasePage{

    public PkhJtqkzpPage(Context context) {
        this(context, null);
    }

    public PkhJtqkzpPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJtqkzpPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhjtqkzp, this);
        initView();
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jtqkzp);
    }

    private void initView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pkh_jtqkzp);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new PkhjtqkzpRecyclerAdapter());
    }
}
