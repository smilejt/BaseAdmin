package cn.smile.jt.common.pojo;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 分页条件（参考JqGrid插件）
 * @author longjuntao
 */
@Data
public class PageCondition {
    /**
     * 当前页码
     */
    private int page = 1;
    /**
     * 页面大小
     */
    private int rows = 10;
    /**
     * 排序字段
     */
    private String sidx;
    /**
     * 排序方式
     */
    private String sord;

    /**
     * 获取JPA的分页查询对象
     */
    public Pageable getPageable() {
        //处理非法页码
        if (page < 0) {
            page = 1;
        }
        //处理非法页面大小
        if (rows < 0) {
            rows = 10;
        }
        return PageRequest.of(page - 1, rows);
    }
}
