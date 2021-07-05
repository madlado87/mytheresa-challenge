package com.github.impl;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PullRequest {
  @CsvBindByPosition(position = 0)
  String status;

  @CsvBindByPosition(position = 1)
  String name;

  @CsvBindByPosition(position = 2)
  String createdAt;

  @CsvBindByPosition(position = 3)
  String author;

  @CsvBindByPosition(position = 4)
  String comments;
}
