package com.sanislo.movieapp.domain.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<T1, T2> {
    public abstract T1 map1(T2 data);
    public abstract T2 map2(T1 data);

    public List<T1> map1(List<T2> t2List) {
        List<T1> t1List = null;
        if (t2List != null) {
            t1List = new ArrayList<>();
            T1 t1;
            for (T2 t2 : t2List) {
                t1 = map1(t2);
                t1List.add(t1);
            }
        }
        return t1List;
    }

    public List<T2> map2(List<T1> t1List) {
        List<T2> t2List = null;
        if (t1List != null) {
            t2List = new ArrayList<>();
            T2 t2;
            for (T1 t1 : t1List) {
                t2 = map2(t1);
                t2List.add(t2);
            }
        }
        return t2List;
    }

    public List<T1> map1(Iterable<T2> data) {
        List<T1> t1List = new ArrayList<T1>();
        for (T2 t2 : data) {
            T1 t1 = map1(t2);
            t1List.add(t1);
        }
        return t1List;
    }

    public List<T2> map2(Iterable<T1> data) {
        List<T2> t1List = new ArrayList<T2>();
        for (T1 t1 : data) {
            T2 t2 = map2(t1);
            t1List.add(t2);
        }
        return t1List;
    }
}