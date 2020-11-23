package com.lc.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.common.annotation.RequiredLog;
import com.lc.pojo.LogEntity;
import com.lc.pojo.SysUser;
import com.lc.service.LogService;
import com.lc.utils.IPUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLogAspect {

    private Logger log = LoggerFactory.getLogger(SysLogAspect.class);

    @Autowired
    private LogService logServiceImpl;

    @Pointcut("@annotation(com.lc.common.annotation.RequiredLog)")
    //@RequiredLog注解加在哪个方法上就标志改方法是切点方法，即被增强
    public void logPointCut() {   //用来承载切点
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint //切点方法的代理方法
                                 jointPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        //执行目标方法(result为目标方法的执行结果)
        Object result = jointPoint.proceed();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        log.info("方法执行的总时长为:" + totalTime);
        saveSysLog(jointPoint, totalTime);  //保存到数据库
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint point,
                            long totleTime) throws NoSuchMethodException, SecurityException, JsonProcessingException {

        //1.获取日志信息
        MethodSignature ms = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        String className = targetClass.getName();

        //获取接口声明的方法
        String methodName = ms.getMethod().getName();
        Class<?>[] parameterTypes = ms.getMethod().getParameterTypes();
        //获取目标对象方法(AOP版本不同,可能获取方法对象方式也不同)
        Method targetMethod = targetClass.getDeclaredMethod(
                methodName, parameterTypes);

        //获取用户名,学完shiro再进行自定义实现,没有就先给固定值
//        String username = ShiroUtils.getPrincipal().getUsername();
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();

        //获取方法参数
        Object[] paramsObj = point.getArgs();
        System.out.println("paramsObj=" + paramsObj);

        //将参数转换为字符串
        String params = new ObjectMapper()
                .writeValueAsString(paramsObj);
        //2.封装日志信息
        LogEntity log = new LogEntity();
        log.setUsername(user.getUsername());//登陆的用户

        //假如目标方法对象上有注解,我们获取注解定义的操作值
        RequiredLog requestLog =
                targetMethod.getDeclaredAnnotation(RequiredLog.class);
        if (requestLog != null) {
            log.setOperation(requestLog.operation());
        }

        log.setMethod(className + "." + methodName);//className.methodName()
        log.setParams(params);//method params
        log.setIp(IPUtils.getIpAddr());//ip 地址
        log.setTime(totleTime);//
        log.setCreatedTime(new Date());
        //3.保存日志信息
        logServiceImpl.saveObject(log);

    }
}