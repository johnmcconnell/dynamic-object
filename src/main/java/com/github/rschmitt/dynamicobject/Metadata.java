package com.github.rschmitt.dynamicobject;

import static com.github.rschmitt.dynamicobject.ClojureStuff.*;

public class Metadata {
    static Class<?> getTypeMetadata(Object obj) {
        Object meta = META.invoke(obj);
        if (meta == null) return null;
        Object tag = GET.invoke(meta, TYPE);
        if (tag == null) return null;
        try {
            return Class.forName((String) NAME.invoke(tag));
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    static Object withTypeMetadata(Object obj, Class<?> type) {
        return VARY_META.invoke(obj, ASSOC, TYPE, cachedRead(":" + type.getTypeName()));
    }
}