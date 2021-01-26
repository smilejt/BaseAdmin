package cn.smile.jt.common.controller;

import cn.smile.jt.annotation.Decrypt;
import cn.smile.jt.annotation.Encrypt;
import cn.smile.jt.common.pojo.PageInfo;
import cn.smile.jt.common.pojo.Result;
import cn.smile.jt.common.service.CommonService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通用Controller
 *
 * @author longjuntao
 * @param <V> 实体类Vo
 * @param <E> 实体类
 * @param <T> id主键类型
 */
public class CommonController<V, E, T> {

    @Resource
    private CommonService<V, E, T> commonService;

    /**
     * CRUD、分页、排序测试
     */
    @PostMapping("page")
    @Decrypt
    @Encrypt
    public Result<PageInfo<V>> page(V entityVo) {
        return commonService.page(entityVo);
    }

    @PostMapping("list")
    @Decrypt
    @Encrypt
    public Result<List<V>> list(V entityVo) {
        return commonService.list(entityVo);
    }

    @GetMapping("get/{id}")
    public Result<V> get(@PathVariable("id") T id) {
        return commonService.get(id);
    }

    @PostMapping("save")
    @Decrypt
    @Encrypt
    public Result<V> save(V entityVo) {
        return commonService.save(entityVo);
    }

    @DeleteMapping("delete/{id}")
    public Result<T> delete(@PathVariable("id") T id) {
        return commonService.delete(id);
    }
}
