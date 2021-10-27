# Team 2E
## Prototype Implemention 1
Our prototype implements the following classes: Debt, House, HouseController, Housemate, Item, LineItem, ShoppingList and TerminalController. Together these classes implement a single House, which contains housemates, which are hard coded. At the moment, one housemate can be added. Housemates are identified by their name and they have a debt list (ArrayList) connected to them, this list is displayed at the end of the application. Furthermore, we implemented LineItem which allows a housemate to provide a quantity of the Item they are going to add to the shared ShoppingList for the given House. The ShoppingList is an ArrayList, which you can be cleared and have items added to it. A housemate can add any item, they just have to provide the name, quantity and price in the command terminal. The application will continue to ask for quantity and price, if they are not in the correct format. The list of items can be displayed, if the housemate wants to see it. To add on, the housemate has the option to make items as purchased, which updates the Shopping List, here the prices can be split, charged to the whole household, or the items can be paid by one person. When purchasing items, the name of the purchaser must be provided. The TerminalController is how the user connects to the domain code, its also where the main() lives, and where the code is runs. The HouseController helps connect the other classes with the UI.TerminalController to avoid conflict when updating the UI.

## Limitations / Simplifying assumptions
* Housemates are identified by there name which we are assuming are unique
* The quantity attribute does not have a set range / cap, so housemates can input high quantities, which are not normal in everyday life
* There is only one house that can be created at a time
* Other housemates must be hard coded in the TerminalController
* We are assuming that the user will not create a mistake when adding items, and purchasing them. Therefore, the price and quantity are assumed to be int and floats that are accurate and do not need to be change.



## Run Project
* The main() method is inside the UI.TerminalController class. 
* Hit run, then you are asked to provide your name, which you may enter in the command terminal. Afterwards, you are presented with a menu, here you will have the option to type "ShoppingList", which allows you to add items to the shopping list, "Purchase" to identify the items you've bought or "Done" to exit. If you chose ShoppingList, you'll be presented with the options to 'add item' (makes item to add), 'list' (displays the list) or 'Done' to return. When adding an item, you are asked to add an item name(String), price (float), quantity(int), you will also be given the option to add more items. When you are done, you can type "Done" to exit. If you type 'Purchase' you'll have the options to: split the cost, charge the whole household, or change the interested housemate. Afterwards, the debts are calcuated and displayed to the User. The options from the first menu are displayed once more.
