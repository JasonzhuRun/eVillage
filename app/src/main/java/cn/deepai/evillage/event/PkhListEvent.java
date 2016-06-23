package cn.deepai.evillage.event;

import java.util.List;

import cn.deepai.evillage.bean.PkhjbxxBean;

/**
 * 贫困户列表响应参数
 */
public class PkhListEvent {
    public ResponseHeaderEvent rspHeader;
    public List<PkhjbxxBean> data;
}
