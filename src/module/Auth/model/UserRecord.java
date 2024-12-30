package com.skndan.robin.module.Auth.model;

public record UserRecord(
  String id,
  String username,
  String email,
  String firstName,
  String lastName,
  String password) {
}
