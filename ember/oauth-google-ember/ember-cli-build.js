/* global require, module */
var EmberApp = require('ember-cli/lib/broccoli/ember-app');

module.exports = function(defaults) {
  var app = new EmberApp(defaults, {
    fingerprint: {
      enabled: false
    },
    outputPaths: {
      vendor: {
        css: '/style/vendor.css',
        js: '/script/vendor.js'
      },
      app: {
        js: '/script/client.js',
        css: {
          app: '/style/client.css'
        },
      }
    }
  });
  app.import(app.bowerDirectory + '/bootstrap/dist/css/bootstrap.css');
  app.import(app.bowerDirectory + '/bootstrap/dist/js/bootstrap.js');
  app.import(app.bowerDirectory + '/wow.js/dist/wow.js');
  app.import(app.bowerDirectory + '/animate.css/animate.css');
  return app.toTree();
};
