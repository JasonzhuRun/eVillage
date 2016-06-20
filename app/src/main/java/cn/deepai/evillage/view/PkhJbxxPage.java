package cn.deepai.evillage.view;

import android.content.Context;
import android.util.AttributeSet;

import cn.deepai.evillage.R;

/**
 * @author GaoYixuan
 * 基本信息
 */
public class PkhJbxxPage extends PkhBasePage{

    public PkhJbxxPage(Context context) {
        this(context,null);
    }
    public PkhJbxxPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhJbxxPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public String getPageName() {
        return getResources().getString(R.string.pkh_jbxx);
    }
}
