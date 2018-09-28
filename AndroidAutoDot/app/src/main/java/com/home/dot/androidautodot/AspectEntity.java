package com.home.dot.androidautodot;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Created by home on 2018/9/27.
 */

@Aspect
public class AspectEntity {
    public static final String TAG = "AspectEntity";
    private boolean isInvokeProceed = true;

    @Before("execution(* android.app.Activity.on*(..))")
    public void onStartBefore(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        Log.e(TAG, "onStartBefore:" + key);
    }

    /**
     * 第一个*表示返回值，*表示返回值为任意类型，后面这个就是典型的包名路径，其中可以包含 * 来进行通配，几个 * 没区别。
     * 同时，这里可以通过&&、||、!来进行条件组合。()代表这个方法的参数，你可以指定类型，
     * 例如android.os.Bundle，或者(..)这样来代表任意类型、任意个数的参数
     */
    /**
     * Around呢，从字面含义上来讲，也就是在方法前后各插入代码，是的，他包含了Before和After的全部功能
     */
    @Around("call(* com.home.dot.androidautodot.MainActivity.testBefore(..))")
    public void aroundTest(ProceedingJoinPoint joinPoint) throws Throwable {
        if (isInvokeProceed) {//isInvokeProceed为false则不执行原始方法（也就是不执行testBefore方法的内部代码）
            joinPoint.proceed();//代表执行原始的方法 在这之前、之后，都可以进行各种逻辑处理
            Log.e(TAG, "aroundTest: test");
        }
        Log.e(TAG, "aroundTest end");
    }

    @Before("execution(* android.view.View.OnClickListener.onClick(..))")
    public void beforeAction() {
        Log.e(TAG, "beforeAction: ");
    }

    @After("execution(* android.view.View.OnClickListener.onClick(..))")
    public void afterAction() {
        Log.e(TAG, "afterAction: ");
    }


    /**
     * withincode的使用
     * eg:aspectJ1()，aspectJ2()，aspectJ3()都调用了aspectJTest方法，
     * 但只想在aspectJ2调用aspectJTest时插入代码
     */

    @Pointcut("(call(* *..aspectJTest()))&&withincode(* *..aspectJ2())")
    public void invokeAspectJTestInAspectJ2() {
    }

    @Before("invokeAspectJTestInAspectJ2()")
    public void beforeInvokeaspectJTestInAspectJ2(JoinPoint joinPoint) throws Throwable {
        if (joinPoint != null) {
            Log.e(TAG, "method:" + joinPoint.getSignature());
        }
    }
}
