package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhshtjBean;

/**
 * 生活条件
 */
public class PkhShtjPage extends PkhBasePage{

    public PkhShtjPage(Context context) {
        this(context,null);
    }
    public PkhShtjPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhShtjPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.page_pkhshtj, this);
        initView();
    }

    @Override
    public void requestData() {

    }

    @Override
    public boolean hasData() {
        return false;
    }

    @Override
    public void bindData(Object dataJson) {

    }

    public void bindData(PkhshtjBean pkhshtjBean) {

    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_shtj);
    }

    private void initView() {

    }
}
