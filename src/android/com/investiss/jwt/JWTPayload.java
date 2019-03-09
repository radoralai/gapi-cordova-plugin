package com.investiss.jwt;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Rado RALAISOA
 */
public class JWTPayload {
  private static final String CLAIM_ISSUER          = "iss";

  private static final String CLAIM_SUBJECT         = "sub";

  private static final String CLAIM_AUDIENCE        = "aud";

  private static final String CLAIM_EXPIRATION_TIME = "exp";

  private static final String CLAIM_NOT_BEFORE      = "nbf";

  private static final String CLAIM_ISSUED_AT       = "iat";

  private static final String CLAIM_JWT_ID          = "jti";

  private JSONObject jso = new JSONObject();

  public JWTPayload() {
    super();
    jso = new JSONObject();
  }

  /**
   * Valorise l'issuer ({@code iss}).
   *
   * @param iss l'issuer.
   * @return this.
   */
  public JWTPayload issuer(final String iss) {
    try {
      jso.put(CLAIM_ISSUER, iss);
    }
    catch (JSONException e) {
      // ne peut arriver
    }
    return this;
  }

  /**
   * Valorise le subject ({@code sub}).
   *
   * @param sub le subject.
   * @return this.
   */
  public JWTPayload subject(final String sub) {
    try {
      jso.put(CLAIM_SUBJECT, sub);
    }
    catch (JSONException e) {
      // ne peut arriver
    }
    return this;
  }

  /**
   * Valorise l'audience ({@code aud}).
   *
   * @param aud l'audience.
   * @return this.
   */
  public JWTPayload audience(final String aud) {
    try {
      jso.put(CLAIM_AUDIENCE, aud);
    }
    catch (JSONException e) {
      // ne peut arriver
    }
    return this;
  }

  /**
   * Valorise la date expiration ({@code exp})
   *
   * @param exp la date expiration.
   * @return this.
   */
  public JWTPayload expirationTime(final Date exp) {
    try {
      claim(CLAIM_EXPIRATION_TIME, exp);
    }
    catch (JSONException e) {
      // ne peut arriver
    }
    return this;
  }

  /**
   * Valorise la date not-before {@code nbf})
   *
   * @param nbf la date not-before .
   * @return this.
   */
  public JWTPayload notBeforeTime(final Date nbf) {
    try {
      claim(CLAIM_NOT_BEFORE, nbf);
    }
    catch (JSONException e) {
      // ne peut arriver
    }
    return this;
  }

  /**
   * Valorise issued-at ({@code iat}) {@code iat})
   *
   * @param iat la date issued-at.
   * @return this.
   */
  public JWTPayload issueTime(final Date iat) {
    try {
      claim(CLAIM_ISSUED_AT, iat);
    }
    catch (JSONException e) {
      // ne peut arriver
    }
    return this;
  }

  /**
   * Valorise jti ({@code jti}).
   *
   * @param jti jti.
   * @return this.
   */
  public JWTPayload jwtID(final String jti) {
    try {
      jso.put(CLAIM_JWT_ID, jti);
    }
    catch (JSONException e) {
      // ne peut arriver
    }
    return this;
  }

  /**
   * Valorise un claim non standard ({@code jti}).
   *
   * @param claim nom du claim.
   * @param value valeur du claim.
   * @return this.
   */
  public JWTPayload claim(final String claim, final String value) throws
                                                                  JSONException {
    jso.put(claim, value);
    return this;
  }

  /**
   * Valorise un claim non standard ({@code jti}).
   *
   * @param claim nom du claim.
   * @param value valeur du claim.
   * @return this.
   */
  public JWTPayload claim(final String claim, final Date value) throws
                                                                JSONException {
    jso.put(claim, toSecondsSinceEpoch(value));
    return this;
  }

  private long toSecondsSinceEpoch(final Date date) {
    return date.getTime() / 1000L;
  }

  @Override
  public String toString() {
    return jso.toString();
  }

  /**
   * Encodes a String with Base64Url and no padding
   *
   * @return Encoded result from input
   */
  public String toBase64URL() {
    String result = null;
    byte[] encodeBytes = toString().getBytes(StandardCharsets.UTF_8);
    result = Base64.encodeToString(encodeBytes,
                                   Base64.NO_PADDING | Base64.NO_WRAP |
                                   Base64.URL_SAFE);
    return result;
  }
}
