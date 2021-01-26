package cn.smile.jt.common.pojo;

import cn.smile.jt.util.CopyUtil;
import lombok.Data;
import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * 分页对象（参考JqGrid插件）
 *
 * @author longjuntao
 */
@Data
public class PageInfo<M> {

    /**
     * 当前页码
     */
    private int page;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 排序字段
     */
    private String sidx;
    /**
     * 排序方式
     */
    private String sord;

    /**
     * 分页结果
     */
    private List<M> rows;
    /**
     * 总记录数
     */
    private int records;
    /**
     * 总页数
     */
    private int total;

    /**
     * 获取统一分页结果
     */
    public static <M> PageInfo<M> of(Page<?> page, Class<M> entityModelClass) {
        int records = (int) page.getTotalElements();
        int pageSize = page.getSize();
        int total = records % pageSize == 0 ? records / pageSize : records / pageSize + 1;

        PageInfo<M> pageInfo = new PageInfo<>();
        //页码
        pageInfo.setPage(page.getNumber() + 1);
        //页面大小
        pageInfo.setPageSize(pageSize);
        //分页结果
        pageInfo.setRows(CopyUtil.copyList(page.getContent(), entityModelClass));
        //总记录数
        pageInfo.setRecords(records);
        //总页数
        pageInfo.setTotal(total);
        return pageInfo;
    }

    /**
     * 获取JPA的分页对象
     */
    public static Page<?> getJPAPage(Query query, PageRequest pageRequest, EntityManager em) {
        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        //获取分页结果
        return PageableExecutionUtils.getPage(query.getResultList(), pageRequest, () -> {
            //设置countQuerySQL语句
            Query countQuery = em.createNativeQuery("select count(*) from ( " + ((NativeQueryImpl) query).getQueryString() + " ) count_table");
            //设置countQuerySQL参数
            query.getParameters().forEach(parameter -> countQuery.setParameter(parameter.getName(), query.getParameterValue(parameter.getName())));
            //返回一个总数
            return Long.parseLong(countQuery.getResultList().get(0).toString());
        });
    }
}
