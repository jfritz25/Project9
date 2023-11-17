# Project_09 - Camera App
<span style="font-size: smaller;"><strong>Ashley Steitz and Jacob Fritz worked on this as partners</strong></span>

---
<span style="font-size: smaller;"><strong> Description </strong> </span>
In our Project 8 we called the IMDB API to search for Movies and retrieve the data (if there is any), then populates an XML file. We also allow the user to share the icon, visit the IMDP page themselves, and send us feedback.
<br>

This app allows for a user to:
- Log into their account
- Shake their device
- Take a photo
- See it display in the recycler view

To begin we utilized a Firestore database in order to store the URI link to the images using Jetpack CameraX Library
<br>
<br>
When taken to the main page, you are able to see all the images in your database. When you shake the device vertically or horizontally
you will be taken to a new fragment that then asks if you wan this to be a one time, multi, or only in camera prompt. Once selected you can take a photo of
your surroundings and then the app takes you back to the recycler view page where your image is added to the bottom. 
<br>
<br>



## Functionality
'*' indicates tested in GIF  
The following **required** functionality is completed:
<br>
**Demonstrated**
<br>
**START**
* [LogIn] -> [EMAIL: jamfritz@iu.edu , PASSWORD: ilove323]
* [Phone was shaken horizontally] 
* [Selected top only while using]
* [PRESSED TAKE PHOTO] 
* [Navigated back to the Main screen] 
* [Previous inserts into the firestore database were above the new photo ]
  <br>
**END**


---
## Video Walkthrough
Watch a demonstration of the different options when working with the notes app in the gif available on Github
Here's a walkthrough of a few translations:
<br>
![Recording in GIF of Walk Through](app/src/main/java/com/example/project9/Project9Recording.gif)

GIF created with [CloudConvert](https://cloudconvert.com/).

UI Challenges:
- Connecting the URI like to the firestore database
- UI was relatively simple so not many happened in the construction

Backend Challenges:
- Connecting to the FIREBASE instead of using the Realtime Database

## License

    Copyright [2023] [Ashley Steitz, Jacob Fritz]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
