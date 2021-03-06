package cn.modesty.suanfa.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用泛型类定义
 * @param <T>
 */
public class AiFruitPlate<T> implements Plate<T> {

    private List<T> fruits = new ArrayList<T>(6);

    @Override
    public void set(T t) {
        fruits.add(t);
    }

    @Override
    public T get() {
        int index = fruits.size() - 1;
        if(index >= 0) return fruits.get(index);
        return null;
    }

    /**
     * 泛型方法
     * @param e
     * @param <E>
     * @return
     */
    public <E> E getList(E e){
        return e;
    }
}


