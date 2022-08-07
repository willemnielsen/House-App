
# Final Implementation
Our functioning app gives users the ability to create a ShoppingList, a purchased item list, and a debt list for a group of housemates. Users are also able to create events for their house, which are described in more detail in the previous iterations. All this data is saved to a firebase, which allows for items to be saved and repopulated when reopening the app. Users can log in by entering a house, giving the password, then doing the same for their housemate name. On the first screen, the user has the option to log in to a house or create a house, which they can then log in on the same screen. Once logged in into a house, the user can create a username and password and log in with those credentials. Finally, the app uses Authentication to make sure that the person logging in is who they really are.

## Limitations / Simplifying assumptions
* The quantity attribute does not have a set range / cap, so housemates can input high quantities, which are not normal in everyday life
* no option to log out or change password if you forget it

## Run Project
When running the app, you are presented with a screen to log in to a house, here you can create a house by providing a house name that hasn't been used and a password. If your house already exists, you can log in with the credentials. In the next screen,  you are presented with a screen to log in with your username and password or create a username and password. Then you will be meet with options to view the housemate, shopping list, calendar, purchase list and transactions. If you open housemate, you'll see the list of housemates. If you open shopping list, you will have the option to purchase items with the top button in the lower right corner and add items to the list with the bottom button. The add button opens up a fragment where you add the item information and before adding the item a dialog pops up that asks the user to add the interested housemates. Furthermore, there are buttons to go back on each screen. For the calendar, you can add events by pressing the '+' button, this will open a screen where you will input a  a name, a date on the calendar, start and end time, and a reoccurrence type for the event. For the purchase list, the items purchased is displayed and you will have the option to split the payment in three different methods, when you press the add person button. Finally you can see all the transactions on the transactions page.

### Note:
The database for this app is no longer active.

