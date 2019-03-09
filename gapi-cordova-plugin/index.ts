import { Injectable } from '@angular/core';
import { Plugin, Cordova, CordovaProperty, CordovaInstance, InstanceProperty, IonicNativePlugin } from '@ionic-native/core';
import { Observable } from 'rxjs';

/**
 * @name Gapi Cordova Plugin
 * @description
 * This plugin generates a JWT for Google Service Account
 *
 * @usage
 * ```typescript
 * import { GapiCordovaPlugin } from '@ionic-native/gapi-cordova-plugin';
 *
 *
 * constructor(private gapiCordovaPlugin: GapiCordovaPlugin) { }
 *
 * ...
 *
 *
 * this.gapiCordovaPlugin.generateToken({"private_key_id": "58c6f536febe0b0e6546161d4e92997e1dc106e5"})
 *   .then((res: any) => console.log(res))
 *   .catch((error: any) => console.error(error));
 *
 * ```
 */
@Plugin({
  pluginName: 'GapiCordovaPlugin',
  plugin: 'gapi-cordova-plugin',
  pluginRef: 'cordova.plugins.GapiCordovaPlugin',
  repo: 'https://github.com/radoralai/gapi-cordova-plugin.git',
  install: '',
  installVariables: [],
  platforms: ['Android', 'iOS']
})
@Injectable({
  providedIn: 'root'
})
export class GapiCordovaPlugin extends IonicNativePlugin {

  /**
   * This function does something
   * @param arg1 {any} credential from Service Account json
   * @return {Promise<any>} Returns a promise that resolves when something happens
   */
  @Cordova()
  generateToken(arg1: any): Promise<any> {
    return;
  }

}
