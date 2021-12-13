package com.news.Utils;


import com.news.orm.Annotation.Column;
import com.news.orm.Annotation.Entity;
import com.news.orm.Annotation.Id;
import exception.OrmException;
public class AnnotationUtil {
    private AnnotationUtil(){}

    public static <T> String getClassName(Class<T> tClass){
        String name = tClass.getAnnotation(Entity.class).name();
        if(StringUtil.isEmpty(name)){
            return StringUtil.convertToRuleNameDatabase(tClass.getSimpleName());
        }
        return name;
    }

    public static <T> String getColumnName(Class<T> tClass,String fieldName){
        try {
            String name = tClass.getDeclaredField(fieldName).getAnnotation(Column.class).name();
            if(StringUtil.isEmpty(name)){
                return StringUtil.convertToRuleNameDatabase(fieldName);
            }
            return name;
        } catch (NoSuchFieldException e) {
           throw new OrmException(e.getMessage());
        }
    }

    public static <T> boolean isAutoIncrement(Class<T> tClass,String fieldName){
        try {
            return tClass.getDeclaredField(fieldName).getAnnotation(Id.class).autoIncrement();
        } catch (NoSuchFieldException e) {
            throw new OrmException(e.getMessage());
        }
    }

    public  static <T> String primaryColumn(Class<T> tClass,String fieldName){
        try {
            String name = tClass.getDeclaredField(fieldName).getAnnotation(Id.class).name();
            if(StringUtil.isEmpty(name)){
                return StringUtil.convertToRuleNameDatabase(fieldName);
            }
            return name;
        } catch (NoSuchFieldException e) {
            throw new OrmException(e.getMessage());
        }
    }
}
