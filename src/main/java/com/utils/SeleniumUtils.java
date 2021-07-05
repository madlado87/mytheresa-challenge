package com.utils;

import com.github.webdriverextensions.Bot;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SeleniumUtils {

  /***
   *
   * @return List<String> all href values from element list
   */
  public static List<String> getPageLinks(List<WebElement> elementList) {
    return elementList.parallelStream()
        .distinct()
        .filter(
            element ->
                Bot.hasAttribute("href", element)
                    && !Bot.attributeIn("href", element).isEmpty()
                    && !Bot.attributeStartsWith("href", "javascript", element)
                    && !Bot.attributeStartsWith("href", "mailto", element))
        .map(element -> Bot.attributeIn("href", element))
        .collect(Collectors.toList());
  }
}
