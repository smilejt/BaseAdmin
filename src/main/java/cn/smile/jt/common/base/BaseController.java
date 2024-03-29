package cn.smile.jt.common.base;

import cn.smile.jt.common.response.ResponseCode;
import cn.smile.jt.common.response.ResponseResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 通用请求处理
 * </>
 *
 * @author 龙逸
 * @since: 2020-09-15 23:33:57
 **/
@SuppressWarnings("all")
public abstract class BaseController<T extends BaseDomain, S extends IBaseService<T>> {
    @Resource
    protected HttpServletRequest request;

    @Autowired
    protected S service;

    private final static String CREAT_SUCCESS = "创建成功";
    private final static String DELETE_SUCCESS = "删除成功";
    private final static String UPDATE_SUCCESS = "编辑成功";

    /**
     * 新增
     *
     * @param domain 领域模型
     * @return {@link ResponseResult}
     */
    @PostMapping("create")
    public ResponseResult create(@Valid @RequestBody T domain) {
        // 业务逻辑
        boolean created = service.create(domain);
        if (created) {
            return ResponseResult.success(CREAT_SUCCESS);
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 删除
     *
     * @param id {@code Long}
     * @return {@link ResponseResult}
     */
    @DeleteMapping("remove/{id}")
    public ResponseResult remove(@PathVariable Long id) {
        // 业务逻辑
        boolean deleted = service.remove(id);
        if (deleted) {
            return ResponseResult.success(DELETE_SUCCESS);
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 修改
     *
     * @param domain 领域模型
     * @return {@link ResponseResult}
     */
    @PutMapping("update")
    public ResponseResult update(@Valid @RequestBody T domain) {
        // 业务逻辑
        boolean updated = service.update(domain);
        if (updated) {
            return ResponseResult.success(UPDATE_SUCCESS);
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 获取
     *
     * @param id {@code Long}
     * @return {@link ResponseResult}
     */
    @GetMapping("get/{id}")
    public ResponseResult get(@PathVariable Long id) {
        T domain = service.get(id);
        return ResponseResult.success(domain);
    }

    /**
     * 分页
     *
     * @param current {@code int} 页码
     * @param size    {@code int} 笔数
     * @return {@link ResponseResult}
     */
    @GetMapping("page")
    public ResponseResult page(
            @RequestParam int current, @RequestParam int size, @ModelAttribute T domain) {
        IPage<?> page = service.page(current, size, domain);
        return ResponseResult.success(page);
    }
}
