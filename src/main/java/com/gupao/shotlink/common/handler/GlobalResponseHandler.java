package com.gupao.shotlink.common.handler;

import com.gupao.shotlink.common.annotation.ResponseResult;
import com.gupao.shotlink.common.reponse.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice(annotations = {ResponseResult.class})
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        //避免重复处理已经做过封装处理的返回值
        final String returnTypeName= methodParameter.getParameterType().getName();
        boolean flag=!"com.example.demo.entity.Result".equals(returnTypeName)
                &&!"org.springframework.http.ResponseEntity".equals(returnTypeName);
        log.info("supports:"+flag);
        return flag;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("before");
        final  String returnTypeName = methodParameter.getParameterType().getName();
        if("void".equals(returnTypeName)){
            return Result.success(null);
        }
        if(!mediaType.includes(MediaType.APPLICATION_JSON)){
            return o;  //返回值不是json类型
        }
        if("com.example.demo.entity.Result".equals(returnTypeName)){
            return o;
        }
        /*
         * 这里我们只处理了正常成功的包装，如果方法体报异常怎么办？处理异常也比较简单，只要判断body是否为异常类。
         * o instanceof Exception
         * */
        return Result.success(o);
    }
}

