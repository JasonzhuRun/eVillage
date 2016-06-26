package cn.deepai.evillage.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.List;

import cn.deepai.evillage.R;
import cn.deepai.evillage.bean.PkhjtcyBean;
import cn.deepai.evillage.bean.PkhxqBean;
import cn.deepai.evillage.event.RspCode;
import cn.deepai.evillage.utils.ToastUtil;
import de.greenrobot.event.EventBus;

/**
 * @author GaoYixuan
 */
public abstract class PkhBasePage  extends FrameLayout {

    protected boolean isSelected;
    protected Context mContext;
    protected boolean mHasData = false;

    public PkhBasePage(Context context) {
        this(context, null);
    }

    public PkhBasePage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PkhBasePage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public abstract String getPageName();

    public abstract void requestData();

    public boolean hasData() {
        return mHasData;
    }

    public abstract void registeEventBus();
    public abstract void unRegisteEventBus();
}