package com.skndan.robin.module.Auth.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthResponse {

  private String profileId;

  private String name;

  private String email;

  private String mobile;
  
  private String url;

  private String social;

}
