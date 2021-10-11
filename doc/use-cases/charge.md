#Charge
+ **Scope:** App -> Shopping list -> Purchased Items
+ **Level:** User goal
+ **Primary Actor:** Domain.Housemate
###Preconditions: 
+ System is in the shopping list menu
		
##Postconditions: 
+ Each person's debt/credit is adjusted accordingly 
##Main success scenario:
1. User selects option to charge housemates
2. System prompts user to select which items
3. User selects items
4. System prompts user with options to
   1. Charge based on owner of items
   2. Charge evenly throughout house
   3. Charge me
   4. Charge custom
5. User selects charge based on owner of items
6. System asks user for confirmation
7. User gives confirmation
8. System calculates amount each housemate owes
9. System updates the credit/debt database for the user and each housemate involved
11. System removes charged items from the Shopping list

##Extensions:
4a. User selects charge evenly throughout the house  
   1. System proceeds the same way except that in step 5, it charges all housemates including user evenly.


4b. User selects charge me
   1. System asks user for confirmation
   2. User gives confirmation
   4. System removes charged items from the purchased items list

4c. User selects charge custom
1. System asks for confirmation
2. User provides confirmation
3. System opens menu with housemates names
4. System prompts user to enter percentage/amount of cost to be distributed to each housemate
5. User inputs values
6. System reads back values for each housemate and asks for confirmation
7. User provides confirmation
8. System proceeds the same way as main success scenario except with the new values

7a. User doesn't confirm
1. System goes back to step 2

##Special requirements:
+ Updates credit database within 30 seconds 90% of the time

##Frequency of occurrence:
+ Somewhat frequent

##Technology and data variations list:

