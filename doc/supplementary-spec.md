# Supplemantary Specs

## Usability:
* Work on android devices such as tablets and smartphones
* Touch screen - responsive to different devices such as tablets and different smartphone sizes
* Control contrast (account for color blind users)
* Text readable on an average phone screen size

## Reliability - Recoverability:
* Server - connect to a main server; if it fails, we can store information locally
* If an API fails, let's say for the shopping list, we can have users manually add items to their list and add dummy prices, or have a backup api with dummy prices and they can change the values once they have a receipt

## System constraint
* Camera - access to the phone’s camera or camera roll to get receipt
* If camera doesn’t work, the items from the receipt can be added manually
* Notification - access notifications system of phone (vibrator, banner, etc)
* Android system application 
