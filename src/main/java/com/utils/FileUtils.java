package com.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.var;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
  private static final String CSV_SEPARATOR = ",";

  public static void generateCsvFile(String csvFileName, List list, List<String> header)
      throws IOException {
    String fileName = String.format("./target/%s", csvFileName);
    Path myPath = Paths.get(fileName);
    try (var writer = Files.newBufferedWriter(myPath, StandardCharsets.UTF_8)) {
      writer.write(String.join(",", header));
      writer.newLine();
      StatefulBeanToCsv<?> beanToCsv =
          new StatefulBeanToCsvBuilder<>(writer)
              .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
              .build();
      beanToCsv.write(list);
    } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException ex) {
    }
  }

  public static String readFromInputStream(InputStream inputStream) throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }
}
