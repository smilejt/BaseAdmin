package cn.smile.jt.common.exceptions;

import cn.smile.jt.common.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 全局业务异常处理
 * </>
 *
 * @author 龙逸
 * @since: 2020-09-15 21:10:17
 **/
@Slf4j
@ControllerAdvice
public class BusinessExceptionHandler {

    private static final String CODE_STRING = "code";
    private static final String MESSAGE_STRING = "message";

    @ExceptionHandler({BusinessException.class, Exception.class})
    public ResponseEntity<?> handlerException(Exception ex) {
        Map<String, Object> error = new HashMap<>(2);

        // 业务异常
        if (ex instanceof BusinessException) {
            error.put(CODE_STRING, ((BusinessException) ex).getCode());
            error.put(MESSAGE_STRING, ex.getMessage());
            log.warn("[全局业务异常]\r\n业务编码：{}\r\n异常记录：{}", error.get("code"), error.get("message"));
        }

        // 统一处理 JSON 参数验证
        else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            String msg = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .distinct()
                    .collect(Collectors.joining(","));
            error.put(CODE_STRING, HttpStatus.BAD_REQUEST.value());
            error.put(MESSAGE_STRING, msg);
        }

        // 统一处理表单绑定验证
        else if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            BindingResult bindingResult = bindException.getBindingResult();
            String msg = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .distinct()
                    .collect(Collectors.joining(","));
            error.put(CODE_STRING, HttpStatus.BAD_REQUEST.value());
            error.put(MESSAGE_STRING, msg);
        }

        // 未知错误
        else {
            error.put(CODE_STRING, ResponseCode.UNKNOWN.code());
            error.put(MESSAGE_STRING, ResponseCode.UNKNOWN.message());
            log.error(ex.getMessage());
        }

        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
