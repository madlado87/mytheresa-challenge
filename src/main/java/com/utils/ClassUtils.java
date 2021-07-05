package com.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassUtils {

  public static List<String> getDeclaredFields(Class<?> cl) {
    return Arrays.stream(cl.getDeclaredFields())
        .map(Field::getName)
        .collect(Collectors.toList());
  }
}
