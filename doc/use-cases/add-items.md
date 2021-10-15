#Add Items:
+ **Scope:** Application -> Shopping list
+ **Level:** User Goal
+ **Primary actor:** Domain.Housemate or Domain.House Leader

###Stakeholders and interests:
+ **Domain.House Leader/Domain.Housemate:** Wants an easy and efficient way to keep track of household needs, purchases, and finances


###Preconditions:
+ The housemate/house leader adding items to the shopping list is identified and authenticated
+ System is in the shopping list menu
+ 
###Postconditions:
+ The house shopping list with the newly added items are available for all housemates to view
###Main success scenario:

1. User selects option to add items to the Domain.Housemate shopping list
2. System asks user for name, price, and quantity of item
3. User enters name, price, and quantity
4. System asks user for housemates interested in purchasing the item and provides list of housemates
5. User selects interested housemates
6. System adds item object to house shopping list
7. System asks user if they would like to add more items
8. System displays shopping list to user

###Extensions:

**3a.** If user does not provide valid input:
1. System will not proceed until valid inputs are provided

**4a.** If user does not select any housemates:
1. System will not proceed until at least 1 housemate is selected

**6a.** If user selects to add more items:
1. System will loop back to Step 2


###Special requirements:
System must update shopping list within 30 seconds 90% of the time.

###Frequency of occurrence:
+ Often