package cn.deepai.evillage.model.bean;

import java.util.List;

/**
 * 贫困户详情内容列表模版类
 * @param <T>
 */
public class PkhxqList<T> {
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
