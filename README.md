# Ludum Dare 49

This project was created for the Ludum Dare 49.

## Local Setup
To run this project on your local machine (using IntelliJ) you go to the `editConfigurations` in the top right corner. 
In the new window, you add a new configuration by clicking on the plus sign in the top left corner and select Gradle from the dropdown.
You can give the configuration a name and then type in *runJvm* beneath the Run headline with the placeholder *tasks and arguments*.
This executable will open a local window whenever you start the application with this config. 
To open the game in the browser you will need to add a second config only this time typing in *runJs*. 

Note that it might take a while to build the application when you start it for the first time

## Deploying to Itch.io
Executing the following command in the terminal `./gradlew jsBrowserDistribution` will generate the files for export. They can be found under following path: `build/distributions`.
By compressing all files in the folder, the game can now be uploaded to itch.io in the project upload section.
