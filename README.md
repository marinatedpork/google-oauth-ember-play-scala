## Scala Play OAuth 2.0 Google Ember.js Ember-CLI Liquid Fire

This repository is meant to be an example of a few features and packages I find useful and interesting.
Within it you will find a single page web application that demonstrates the following:

##### Backend:

  - Implicits values, higher order functions, and Options for cleaner and safer code.
  - Utilizing Java packages in Scala with Java interoperability.
  - Google OAuth 2.0 in Scala Play.
  - Scala wrapper for the GoogleSpreadsheet API.

##### Frontend:

  - ES6 features and syntax.
  - Ember-CLI automated rebuild into a Scala application.
  - Liquid Fire data bindings.
  - WOW.js / Animate.css transitions.

- - -

#### Setup:

 * Install Activator: https://www.playframework.com/download.
 * Install Ember CLI: http://ember-cli.com/ (optional)
 * Go your Google Developers Console: https://console.developers.google.com/project.
 * Create a new project.
 * Go to that project's dashboard.
 * Go to 'Use Google APIs' or the API Manager.
 * Go to Drive.
 * Enable the Drive API.
 * Go to the Credentials page in the API Manager.
 * Go to your project.
 * Add your JavaScript origins (if you're doing this locally: http://localhost:9000).
 * Add a redirect URL (if you're doing this locally: http://localhost:9000/authenticate).
 * Save and download the credentials as JSON.
 * Add the credentials object to your configuration file, and make sure to name the key as it is below:

        \# conf/application.conf
    
        google-oauth-2 : {
          web : {
            client_id     : "YOUR VALUE",
            auth_uri      : "YOUR VALUE",
            token_uri     : "YOUR VALUE",
            auth_provider_x509_cert_url : "YOUR VALUE",
            client_secret : "YOUR VALUE",
            redirect_uris : [
              "YOUR VALUE"
            ],
            javascript_origins : [
              "YOUR VALUE"
            ]
          }
        }


 Once you have Ember CLI and Activator installed, run the application:

        activator run

        # visit localhost:9000

  If you want to run the Ember Server too, leave that process running and:

        cd ember/oauth-google-ember/
        npm install
        bower install (press the third option if it gives you trouble on the packages)
        ember server

**At this point both the Activator and Ember CLI servers are running, so any source changes in either will result in an automatic rebuild.**

If you get a error regarding **FSEventStream**, quit any IDE's or Text Editors you have open, shut down the servers, and then run

    activator
    ~ run
    
    cd ember/oauth-google-ember/
    ember server
    
    # visit localhost:9000

- - -

#### Testing:

  The Scala SpecHelper needs a **TEST_REFRESH** System ENV variable for testing all the end points as well as a **TEST_WORKSHEET** and **TEST_WORKBOOK** ids as System ENV variables. If you don't have those variables some of the tests won't pass since their testing actual data. To run the Scala tests:

    activator test

  To run the ember tests:

    cd ember/oauth-google-ember/
    ember test

- - -

#### Thanks for checking it out!