package cn.deepai.evillage.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import cn.deepai.evillage.R;

/**
 * @author GaoYixuan
 */
public class PkhSctjPage extends PkhBasePage{

    public PkhSctjPage(Context context) {
        this(context,null);
    }
    public PkhSctjPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhSctjPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhsctj, this);
        initView();
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_sctj);
    }

    private void initView() {

    }
}
