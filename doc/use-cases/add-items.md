#Add Items:
+ **Scope:** Application
+ **Level:** User Goal
+ **Primary actor:** Domain.Housemate or the housemate leader

###Stakeholders and interests:
+ **Domain.Housemate leader:** wants to make it easy to keep track of what needs to be purchased and keep the home stacked with essential items.
+ **Domain.Housemate:** wants to have everything stocked and available


###Preconditions:
+ The housemate adding items to the shopping list is identified and authenticated

###Postconditions:
+ The checklist is restarted once everything is purchased
+ The total costs are available for housemates to see

###Main success scenario:

1. User selects option to add items to the Domain.Housemate shopping list
2. User manually adds the item to the list
   1. User provides the name of the item, quantity, and price.
      1. **Extension**: If a price is not given, an average price will be calculated.
      2. **Extension**: Another housemate can add to the quantity if needed.  
   2. Domain.Item is connected to the person who added the item
      1.  **Extension**: If an item has been added before, it will be recommended when added items in the future.
   3. User has option to add more people to the item
      1. **Extension**: Another housemate can add themselves to the item
   4. System asks user if the information provided is correct
      1. If not, the user is prompted to provide the information again.
      2. If it is correct, the item is stored.
3. The shopping list is updated with the added item and the shopping list is displayed to the user
4. User has the option to add more items 
   1. If the housemate decides to add an item they'll repeat steps 1 - 4.
   2. If the housemate decides to not add more items, they can exit or take other actions.




###Special requirements:
Adding items manually should work quick and effortlessly

###Frequency of occurrence:
+ Often