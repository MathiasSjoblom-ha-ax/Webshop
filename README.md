# Webshop backend application

## Installation of the webshop-web submodule

Run the following commands from inside the webshop-web directory:

* Install the Angular CLI `npm install -g @angular/cli`. Npm should be included in above installation
* Install all dependencies for this particular application with `npm install`.

## Useful commands
 * `gradlew webshop-api:bootRun` Will start a local API server on http://localhost:5000
 
 * `ng serve`  Will start a local development server on http://localhost:4200. 
 NOTE: This must be executed while standing in webshop-web folder.
 
 * `ng build --outputPath=build/dist --configuration=production` Will build static 'production' artifacts from you web application. 
 NOTE: This must be executed while standing in webshop-web folder.
 
 * `cdk deploy` Will deploy the infrastructure of this application into the cloud
 NOTE: This must be executed while standing in webshop-infra folder.