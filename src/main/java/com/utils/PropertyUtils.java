package com.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

  public static void loadPropertiesFile(Properties properties) throws IOException {
    properties.forEach(
        (k, v) ->
            com.github.webdriverextensions.internal.utils.PropertyUtils.setPropertyIfNotExists(
                String.valueOf(k), String.valueOf(v)));
  }
}
