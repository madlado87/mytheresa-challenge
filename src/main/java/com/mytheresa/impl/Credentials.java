package com.mytheresa.impl;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credentials {
  String username, password;
}
