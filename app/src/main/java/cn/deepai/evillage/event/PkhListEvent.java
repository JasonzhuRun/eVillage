package cn.deepai.evillage.event;

import java.util.List;

import cn.deepai.evillage.bean.PkhjbxxBean;
import cn.deepai.evillage.bean.ResponseHeaderBean;

/**
 * 贫困户列表响应参数
 */
public class PkhListEvent {
    public ResponseHeaderBean rspHeader;
    public List<PkhjbxxBean> data;
}
