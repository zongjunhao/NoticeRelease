package com.zjh.notice.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zongjunhao
 * Controller请求日志
 */
@Aspect
@Component
public class WebLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 切点为controller的方法
     */
    @Pointcut("execution(* com.zjh.notice.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 打印请求相关参数
        logger.info("========================================== Start ==========================================");
        // 打印请求 url
        logger.info("URL            : {} {}", request.getMethod(), request.getRequestURL().toString());
        // 打印调用 controller 的全路径以及执行方法
        logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        // logger.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        Map<String, String[]> parameterMap =  request.getParameterMap();
        logger.info("Parameter      : {}", getParameterMap(parameterMap));
    }

    @After("webLog()")
    public void doAfter() {
        logger.info("=========================================== End ===========================================");
        // 每个请求之间空一行
        logger.info("");
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        logger.info("Response Args  : {}", JSON.toJSON(result));
        // 执行耗时
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    private String getParameterMap(Map<String, String[]> parameterMap){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            sb.append(entry.getKey()).append(arrayToString(entry.getValue())).append("  ");
        }
        return sb.toString();
    }

    private String arrayToString(String[] args){
        if (args == null) {
            return "=null";
        }

        int iMax = args.length - 1;
        if (iMax == -1) {
            return "=[]";
        }

        if (iMax == 0){
            return "="+args[0];
        }

        StringBuilder b = new StringBuilder();
        b.append("[]={");
        for (int i = 0; ; i++) {
            b.append(args[i]);
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(",");
        }
    }
}
