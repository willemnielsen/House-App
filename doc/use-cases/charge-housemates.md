#Charge Housemates
+ **Scope:** App -> Shopping list -> Purchased Items
+ **Level:** User goal
+ **Primary Actor:** Housemate
###Preconditions: 
+ System is in the purchased items menu
		
##Postconditions: 
+ Each person's debt/credit is adjusted accordingly 
##Main success scenario:
1. User selects option to charge housemates
2. System prompts user with options to
   1. Charge based on owner of items
   2. Charge evenly throughout house
   3. Charge me
   4. Charge custom
3. User selects charge based on owner of items
4. System asks user for confirmation
5. User gives confirmation
6. System notifies each housemate that they owe the user x amount
of dollars for items a, b and c.
7. System updates the credit/debt database for the user and each housemate involved
8. System removes charged items from the purchased items list

##Extensions:
3a. User selects charge evenly throughout the house  
   1. System proceeds the same way except that in step 5, it charges all housemates including user evenly.
   Also, in notification, system says that cost of purchase was distributed evenly. 


3b. User selects charge me
   1. System asks user for confirmation
   2. User gives confirmation
   3. System notifies housemates that owned items that user purchased them
   4. System removes charged items from the purchased items list

3c. User selects charge custom
1. System asks for confirmation
2. User provides confirmation
3. System opens menu with housemates names
4. System prompts user to enter percentage/amount of cost to be distributed to each housemate
5. User inputs values
6. System reads back values for each housemate and asks for confirmation
7. User provides confirmation
8. System proceeds the same way as main success scenario except with the new values

5a. User doesn't confirm
1. System goes back to step 2

##Special requirements:
+ Updates credit database within 30 seconds 90% of the time

##Frequency of occurrence:
+ Somewhat frequent

##Technology and data variations list:

