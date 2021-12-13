# Team 2E
# Prototype Implementation 1
Our prototype implements the following classes: Debt, House, HouseController, Housemate, Item, LineItem, ShoppingList and TerminalController. Together these classes implement a single House, which contains housemates, which are hard coded. At the moment, one housemate can be added. Housemates are identified by their name and they have a debt list (ArrayList) connected to them, this list is displayed at the end of the application. Furthermore, we implemented LineItem which allows a housemate to provide a quantity of the Item they are going to add to the shared ShoppingList for the given House. The ShoppingList is an ArrayList, which you can be cleared and have items added to it. A housemate can add any item, they just have to provide the name, quantity and price in the command terminal. The application will continue to ask for quantity and price, if they are not in the correct format. The list of items can be displayed, if the housemate wants to see it. To add on, the housemate has the option to make items as purchased, which updates the Shopping List, here the prices can be split, charged to the whole household, or the items can be paid by one person. When purchasing items, the name of the purchaser must be provided. The TerminalController is how the user connects to the domain code, its also where the main() lives, and where the code is runs. The HouseController helps connect the other classes with the UI.TerminalController to avoid conflict when updating the UI.

## Limitations / Simplifying assumptions
* Housemates are identified by there name which we are assuming are unique
* The quantity attribute does not have a set range / cap, so housemates can input high quantities, which are not normal in everyday life
* There is only one house that can be created at a time
* Other housemates must be hard coded in the TerminalController
* We are assuming that the user will not create a mistake when adding items, and purchasing them. Therefore, the price and quantity are assumed to be int and floats that are accurate and do not need to be change.



## Run Project
* The main() method is inside the UI.TerminalController class. 
* Hit run, then you are asked to provide your name, which you may enter in the command terminal. Afterwards, you are presented with a menu, here you will have the option to type "ShoppingList", which allows you to add items to the shopping list, "Purchase" to identify the items you've bought or "Done" to exit. If you chose ShoppingList, you'll be presented with the options to 'add item' (makes item to add), 'list' (displays the list) or 'Done' to return. When adding an item, you are asked to add an item name(String), price (float), quantity(int), you will also be given the option to add more items. When you are done, you can type "Done" to exit. If you type 'Purchase' you'll have the options to: split the cost, charge the whole household, or change the interested housemate. Afterwards, the debts are calculated and displayed to the User. The options from the first menu are displayed once more.

# Prototype Implementation 2
Our prototype implements the following class: Calendar and Add Events on top of the classes implemented in Implementation 1. The calender method allows users add events to the house, essentially reserving space in the house and making housemates of what is happening. Additionally, this Implementation allows the user to add housemates to their house, through a PlainText field that can be opened through the add button on the housemate list screen, so housemates do not need to be hard coded in. This implementation is also in Android Studio, where we utilized fragments to display, the ShoppingList and the purchased list. Furthermore, we have an add item fragment that allows the user to add a line item to the shoppingList. The Controller allows this interaction to go smoothly without having direct interaction with each fragment. To add on, we created a simple login screen that allows a user to select a house and declare their housemate name.


## Limitations / Simplifying assumptions
* Housemates are identified by there name which we are assuming are unique
* The quantity attribute does not have a set range / cap, so housemates can input high quantities, which are not normal in everyday life
* Only one house can be created at a time
* Data is not being stored

## Run Project
When running the app, you are presented with a screen to log in, here you will put a house name and a housemate of your choice. Then you will be meet with options to view the housemate, shopping list, calendar, purchase list and transactions. If you open housemate you have the option to add a new housemate. If you open shopping list, you will have the option to purchase items with the top button in the lower right corner and add items to the list with the bottom button. The add button opens up a fragment where you add the item information and before adding the item a dialog pops up that asks the user to add the interested housemates. Furthermore, there are buttons to go back on each screen. For the calendar, you can add events by pressing the '+' button, this will open a screen where you will input a  a name, a date on the calendar, start and end time, and a reoccurrence type for the event. For the purchase list, the items purchased is displayed and you will have the option to split the payment in three different methods, when you press the add person button. Finally you can see all the transactions on the transactions page.


# Final Implementation
Our functioning app gives users the ability to create a ShoppingList, a purchased item list, and a debt list for a group of housemates. Users are also able to create events for their house, which are described in more detail in the previous iterations. All this data is saved to a firebase, which allows for items to be saved and repopulated when reopening the app. Users can log in by entering a house, giving the password, then doing the same for their housemate name. On the first screen, the user has the option to log in to a house or create a house, which they can then log in on the same screen. Once logged in into a house, the user can create a username and password and log in with those credentials. Finally, the app uses Authentication to make sure that the person logging in is who they really are.

## Limitations / Simplifying assumptions
* The quantity attribute does not have a set range / cap, so housemates can input high quantities, which are not normal in everyday life
* no option to log out or change password if you forget it

## Run Project
When running the app, you are presented with a screen to log in to a house, here you can create a house by providing a house name that hasn't been used and a password. If your house already exists, you can log in with the credentials. In the next screen,  you are presented with a screen to log in with your username and password or create a username and password. Then you will be meet with options to view the housemate, shopping list, calendar, purchase list and transactions. If you open housemate, you'll see the list of housemates. If you open shopping list, you will have the option to purchase items with the top button in the lower right corner and add items to the list with the bottom button. The add button opens up a fragment where you add the item information and before adding the item a dialog pops up that asks the user to add the interested housemates. Furthermore, there are buttons to go back on each screen. For the calendar, you can add events by pressing the '+' button, this will open a screen where you will input a  a name, a date on the calendar, start and end time, and a reoccurrence type for the event. For the purchase list, the items purchased is displayed and you will have the option to split the payment in three different methods, when you press the add person button. Finally you can see all the transactions on the transactions page.

