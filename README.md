# Project 2 - *Flixter APP*

**Flixter** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **13** hours spent in total
* Time spent in requirements and bonuses: **6** hours
* Time spent in UI design (mostly learning and searching): **7** hours

## User Stories

The following **required** functionality is completed:

* [X] User can **scroll through current movies** from the Movie Database API
* [X] Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
* [X] For each movie displayed, user can see the following details:
  * [X] Title, Poster Image, Overview (Portrait mode)
  * [X] Title, Backdrop Image, Overview (Landscape mode)
* [X] Allow user to view details of the movie including ratings within a separate activity

The following **stretch** features are implemented:

* [X] Improved the user interface by experimenting with styling and coloring.
* [X] Apply rounded corners for the poster or background images using [Glide transformations](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#transformations)
* [X] Apply the popular [View Binding annotation library](http://guides.codepath.org/android/Reducing-View-Boilerplate-with-ViewBinding) to reduce boilerplate code.
* [X] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following **additional** features are implemented:

* [X] Created style class that extends RecyclerView.ItemDecoration
* [X] Resizable TextViews that adjust to users screen and reescalates. If something goes grong it has a ellipsize attribute

* List anything else that you can get done to improve the app functionality!
* Filter API queries
* Fragments

## Video Walkthrough

Here's a walkthrough of implemented user stories:

### FOR TEST PURPOSES OF REQUIREMENTS (IMPLEMENTING A VIEWHOLDER) THE INTERNET CONNECTION IS SET TO POOR RECEPTION AND POOR DATA SPEED 
### AFTER WE SET THE CONNECTION TO "FULL" WE HAVE THE REAL SPEED

<img src='gif/file1por.gif' title='Video Walkthrough portrait' width='' alt='Video Walkthrough' />
<img src='gif/file1lan.gif' title='Video Walkthrough landscape' width='' alt='Video Walkthrough' />

GIF created with [Kap](https://getkap.co/).

## Notes

Describe any challenges encountered while building the app.
* RecyclerView correct understanding. The RecyclerView component obligates the programmer to use good coding practices, and is hard to understand at first.
* Learn about styles and drawables in Android Studio, IMPROVED significantly compared of when I started.
* The video didn't run in the first tap, I needed to double check my async method, realized I wasn't waiting for the method to run.
* Find a way to scroll thru a ListView and make it scroll up and down automatically.
* Assign a margin at the bottom of each ViewHolder item.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2021] [Carlo Angel Lujan Garcia]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
