/**
 * It provides the JWT Token used to authenticates to Google Service
 */
package com.investiss;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import java.util.Date;

public class GapiCordovaPlugin extends CordovaPlugin {
  private static final String TAG = "GapiCordovaPlugin";

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing GapiCordovaPlugin");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals("getToken")) {
      Log.d(TAG, "getToken");
      final PluginResult result = new PluginResult(PluginResult.Status.OK, "");
      callbackContext.sendPluginResult(result);
    }
    return true;
  }

  private String tokenJWT(Context context,
                         String alias,
                         JWTPayload jwtPayLoad) throws
                                                GeneralSecurityException,
                                                IOException {
    LOG.d(TAG, ">>tokenJWT");

    Key key = getSecretKey(context, alias);
    if (key == null) {
      throw new KeyException("key not found");
    }

    // Create HMAC signer
    String tokenJWT = null;
    try {
      LOG.d(TAG, ">>alias " + alias);

      SignedJWT signedJWT = new SignedJWT(JWTHeader.HS256, jwtPayLoad);

      // Compute the HMAC protection
      signedJWT.sign(key);
      tokenJWT = signedJWT.getToken();
      LOG.d(TAG, "<<tokenJWT");
    }
    catch (Throwable e) {
      throw new GeneralSecurityException(e);
    }

    return tokenJWT;
  }

}
