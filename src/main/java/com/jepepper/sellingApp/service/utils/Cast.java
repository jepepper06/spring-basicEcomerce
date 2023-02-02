package com.jepepper.sellingApp.service.utils;

import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cast {
    public static <T> List<T> castList(Class<? extends  T> clazz, Page<?> rawPage) throws ClassCastException{
        List<T> result = new ArrayList<>(rawPage.getSize());
        for(Object o: rawPage){
            result.add(clazz.cast(o));
        }
        return result;
    }
    public static <T,E> Map<T,E> castMap(Class<? extends T> clazz1,Class<? extends  E> clazz2,Map<?,?> rawMap)
    throws ClassCastException{

        Map<T,E> result = new HashMap<>(rawMap.size());
        rawMap.entrySet().stream().collect(Collectors.toMap(
                e -> clazz1.cast(e.getKey()),
                e -> clazz2.cast(e.getValue())));
        return result;
    }
}
