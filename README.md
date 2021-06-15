# NearBy Restaurant

## Introduction
An App that shows the nearby restaurants using google map.

## Preview

<!-- ![ScreenShot](https://github.com/shittu33/Restaurant/blob/master/screen.png?raw=true?width=10) -->

<img width="164" alt="jarray reverse exampl" src="https://github.com/shittu33/Restaurant/blob/master/screen.png">

## Technologies

**This project fetch its data with this url:**
```dart
const val BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/"

const val ALL_RESTAURANTS =
    "json?location=47.6204,-122.3491&radius=2500&type=restaurant&key=AIzaSyDkGIvqAXuuOE5TUoDedazelbPdKtQxb1E";

const val SEARCH_RESTAURANTS =
    "json?location=47.6204,-122.3491&radius=2500" +
            "&type=restaurant&key=AIzaSyDkGIvqAXuuOE5TUoDedazelbPdKtQxb1E";
```
