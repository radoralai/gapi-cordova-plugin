package com.investiss.jwt;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;

/**
 * @author Rado RALAISOA
 */
public class SignedJWT {
  private JWTHeader  header;

  private JWTPayload payload;

  private String     base64URLSignature;

  private final String signInput;

  /**
   * Construit un token JWT.
   *
   * @param header  le header JWT.
   * @param payload le payload JWT.
   */
  public SignedJWT(JWTHeader header, JWTPayload payload) {
    if (header == null) {
      throw new IllegalArgumentException("JWTHeader est null");
    }
    if (payload == null) {
      throw new IllegalArgumentException("JWTPayload est null");
    }
    this.header = header;
    this.payload = payload;

    signInput = header.toBase64URL() + '.' + payload.toBase64URL();
  }

  /**
   * @return Restitue les données pour calculs de la signature.
   */
  public byte[] getSignInput() {
    return signInput.getBytes(StandardCharsets.UTF_8);
  }

  /**
   * Valorise la signature.
   *
   * @param key la clé pour signature.
   */
  public void sign(Key key) throws
                            NoSuchAlgorithmException,
                            InvalidKeyException {
    // Compute the HMAC protection
    Mac mac = Mac.getInstance(key.getAlgorithm());
    mac.init(key);
    mac.update(getSignInput());
    final byte[] signature = mac.doFinal();

    base64URLSignature = Base64.encodeToString(signature,
                                               Base64.NO_PADDING |
                                               Base64.NO_WRAP |
                                               Base64.URL_SAFE);
  }

  public String getToken() {
    if (base64URLSignature == null) {
      throw new IllegalStateException("La signature n'est pas renseignée");
    }
    return signInput + '.' + base64URLSignature;
  }

}
