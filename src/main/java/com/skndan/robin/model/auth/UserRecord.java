package com.skndan.robin.model.auth;

public record UserRecord(
  String id,
  String username,
  String email,
  String firstName,
  String lastName,
  String password) {
}
