package cn.smile.jt.common.exceptions;

import cn.smile.jt.common.response.ResponseCode;

/**
 * <p>
 * 全局业务异常
 * </p>
 *
 * @author longjuntao
 * @since 2020/8/25 15:32
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4546779323359670022L;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = -1;
    }

    public BusinessException(ResponseCode status) {
        super(status.message());
        this.code = status.code();
    }
}
