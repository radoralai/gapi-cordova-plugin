
var exec = require('cordova/exec');

var PLUGIN_NAME = 'GapiCordovaPlugin';

var GapiCordovaPlugin = {
  echo: function(phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  },
  getDate: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'getDate', []);
  },
  getToken: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'getToken', [])
  }
};

module.exports = GapiCordovaPlugin;
