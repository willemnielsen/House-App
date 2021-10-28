#Purchase
+ **Scope:** App -> Shopping list -> Purchased Items
+ **Level:** User goal
+ **Primary Actor:** Domain.Housemate or Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants an easy way to manage house purchases and finances

###Preconditions: 
+ The housemate/house leader purchasing shopping list items is identified and authenticated
+ System is in the shopping list menu
		
##Postconditions: 
+ The purchased items are removed from the house shopping list
+ The house's and individual housemates' debt list and balance is updated
+ 
##Main success scenario:
1. User selects purchase options
2. System prompts user to select which items from the shopping list are being purchased
3. User selects items
4. System prompts user with options to
   1. Charge based on interested housemates
   2. Charge household
   3. Charge me
5. User selects charge based on interested housemates
6. System calculates amount owed for each purchased item and adds each new debt to house and each interested housemate's debt to their respective profile 
7. System removes purchased items from the house Shopping list
8. System displays updated house debt list
9. System displays updated balance of each housemate
10. System returns user to house shopping list

##Extensions:
**5a.** If user selects Charge household:
6. System calculates amount owed for each purchased item and adds each new debt to house and each housemate's debt to their respective profile
2. Resume at step 7

**5b.** If user selects Charge me:
1. System adds new debt for each item to house and user profile
2. Resume at step 7

##Special requirements:
+ Updates shopping list, house debt list, and individual housemate debt list/balance each within 30 seconds 90% of the time

##Frequency of occurrence:
+ Somewhat frequent

##Technology and data variations list:

