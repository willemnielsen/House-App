#Test Report
To test our code, we used the command terminal to make sure each function works as needed. We used the TerminalController to generate this code.
## Testing creating housemate
First, we started by making sure a housemate could be added to a house, here we made sure that a user can type any String and it would tell the user that their account was successfully created. Since our assumption is that the housemate would add their unique name, we did not have to check to see if the name was a real name. 
## Testing creating ShoppingList / adding items
Afterwards, we tested the ShoppingList and our ability to add items. Similar to name, we made sure that a user can type any String to represent an item. For price and quantity, we created a try and catch scenerio to address type differences for price and quantity. Therefore, we tested different types to make sure our system worked as it should.Here, items can also be associated to a housemate / interested housemate, we tested that this work, by typing in the name of a housemate (small spelling and capitilization' and the name of a housemate that does not exit. If the housemate doesn't exist or 'done' is not typed, the program asked for the user input again. Once we finished adding items, we tested that a list was produced by displaying the list by typing 'ShoppingList' and 'list'. Here the list was successfully displayed, so we knew that function was working. 

## Testing Purchase
Here we focused on displaying each item in the ShoppingList and giving the user the option to buy the given time. If the item was purchased, it is removed from the ShoppingList, and if it wasn't, it will still be in the list. We checked this, by displaying the ShoppingList.
