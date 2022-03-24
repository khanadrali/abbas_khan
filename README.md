# Project Explaination
## Architecture Used : 
Clean Architecture + MVVM

## Language Used :
Kotlin

## Libraries Used : 
1.	Retrofit library for Api Call
2.	Hilt for dependency injection
3.	Kotlin Coroutine 
4.	Viewmodel lifecycle scope library for viewmodel
5.	Scalar and Gson library for response conversion
Number of Activities: Single 

## Flow of the application:

This application will launch on the main activity screen which have three textviews each for three task result and single button which will send three api requests to the server when the request is in progress the app will show progress dialog as well.

## Task 1)
After the response is taken the ist task will show the 10th character in the response string which is at 9th index starting from zero 
If the character is an empty space it will show “Empty space ” else it will show the 10th character as shown in the picture

## Task 2)
This task will show every 10th word starting from the 9th index in the string which is the 10th character and then show the 19th which is the 20th character and the rest with 10th character gap with the index as well.
Here if the character is “new line” it will show new line if the character is “empty space” it will show empty space else it will show the exact character

## Task 3)
In this task i have split the response with the “space”, “new line” or “tab” and print the word with its number of counts. 

## ScreenShot

![truecaller_image](https://user-images.githubusercontent.com/50039040/159865515-4bb4d461-b8c3-4f95-ac87-05ad2ff58196.png)

