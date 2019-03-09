package com.investiss.jwt;

/**
 * @author Rado RALAISOA
 */
public enum JWTHeader {

  HS256("eyJhbGciOiJIUzI1NiJ9");

  final String base64Value;

  private JWTHeader(String base64Value) {
    this.base64Value = base64Value;
  }

  public String toBase64URL() {
    return base64Value;
  }

}
