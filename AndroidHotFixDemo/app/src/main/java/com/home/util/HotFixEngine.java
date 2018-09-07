package com.home.util;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

/**
 * Created by xuguohong on 2018/9/7.
 */

public class HotFixEngine {
    private static final HotFixEngine HOT_FIX_ENGINE = new HotFixEngine();

    private String mParentFieldName = "parent";
    private String mDexPathListFieldName = "pathList";//baseDexClasLoader 中DexPathList属性名称
    private String mElementFieldName = "dexElements";//DexPathList中Element[]的属性


    private HotFixEngine() {
    }

    public static HotFixEngine getInstance() {
        return HotFixEngine.HOT_FIX_ENGINE;
    }

    //根据类加载为父委托加载原理   替换有bug的类放在dexPath中让DexClassLoader优先加载
    //类加载顺序：BootstrapClassLoader---->DexClassLoader----->PathClassLoader
    public void loadPatchDex(Context context, String dexPath, String optimizedDirectory, String librarySearchPath) {
        ClassLoader currentClassLoader = context.getClassLoader();//context加载器(PathClassLoader)
        ClassLoader parentClassLoader = currentClassLoader.getParent();//context的父加载器(BootstrapClassLoader)

        //加载dexPath加载器
        DexClassLoader dexClassLoader = new DexClassLoader(dexPath, optimizedDirectory, librarySearchPath, parentClassLoader);
        this.setField(ClassLoader.class, this.mParentFieldName, currentClassLoader, dexClassLoader);//设置当前类加载器的父加载器为dexClassLoader
    }

    //根据安卓的PathClassLoader加载机制将加载的Dex添加的DexPathList的dexElements属性列表中，将更新的dex加载放在dexElements列表索引之前
    public void loadPatchDex2(Context context, String dexPath, String optimizedDirectory, String librarySearchPath) {
        ClassLoader pathClassLoader = context.getClassLoader();
        //加载dexPath加载器
        DexClassLoader dexClassLoader = new DexClassLoader(dexPath, optimizedDirectory, librarySearchPath, pathClassLoader.getParent());
        //获取BaseDexClassLoader的DexPathList属性 私有的需要通过反射获取
        Object dexPathList1 = this.getFieldValue(BaseDexClassLoader.class, this.mDexPathListFieldName, pathClassLoader);//pathClassLoader的PathList属性
        Object dexPathList2 = this.getFieldValue(BaseDexClassLoader.class, this.mDexPathListFieldName, dexClassLoader);
        if (dexPathList1 != null && dexPathList2 != null) {
            //获取对应pathList中的dexElements属性
            Object dexElements1 = this.getFieldValue(dexPathList1.getClass(), this.mElementFieldName, dexPathList1);//当前PathClassLoader中的Element[]属性
            Object dexElements2 = this.getFieldValue(dexPathList2.getClass(), this.mElementFieldName, dexPathList2);//DexClassLoader中的Element[]属性

            if (dexElements1 != null && dexElements2 != null) {
                //将两个Element[]属性合并
                Object finalElements = combineArray(dexElements2, dexElements1);
                //将合并的值设置给当前的pathClassLoader的Element[]中
                this.setField(dexPathList1.getClass(), this.mElementFieldName, dexPathList1, finalElements);
                Log.e("HotFixEngine", "loadPatchDex2: success");
            }
        }

    }


    /**
     * 获取对应属性值
     * @param clazz
     * @param fieldName 属性名称
     * @param target 要操作的对象
     * @return
     */
    private Object getFieldValue(Class clazz, String fieldName, Object target) {
        Field field = this.getField(clazz, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                return field.get(target);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Field getField(Class clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param clazz
     * @param fieldName 属性名称
     * @param target    要设置属性的对象
     * @param value     设置属性的值
     * @return 设置成功
     */
    private boolean setField(Class clazz, String fieldName, Object target, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (field != null) {
                field.setAccessible(true);
                field.set(target, value);
            }
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return false;//设置失败
    }


    /**
     * 两个数组合并
     *
     * @param arrayLhs
     * @param arrayRhs
     * @return
     */
    private static Object combineArray(Object arrayLhs, Object arrayRhs) {
        Class<?> localClass = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);
        int j = i + Array.getLength(arrayRhs);
        Object result = Array.newInstance(localClass, j);
        for (int k = 0; k < j; ++k) {
            if (k < i) {
                Array.set(result, k, Array.get(arrayLhs, k));
            } else {
                Array.set(result, k, Array.get(arrayRhs, k - i));
            }
        }
        return result;
    }

}
