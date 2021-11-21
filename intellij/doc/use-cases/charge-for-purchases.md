#Charge for Purchases
+ **Scope:** App -> Shopping list -> Purchase List
+ **Level:** User goal
+ **Primary Actor:** Domain.Housemate or Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants an easy way to manage finances and charge housemates

###Preconditions:
+ The housemate/house leader charging housemates is identified and authenticated
+ System is in the purchase list menu

##Postconditions:
+ The purchased items are removed from the house purchase list
+ The house's and individual housemates' debt list and balance is updated
+ The house's transaction list is updated

##Main success scenario:
1. User selects charge housemates
2. System prompts user with options to
    1. Charge based on interested housemates
    2. Charge household
    3. Charge me
3. User selects charge based on interested housemates
4. System calculates amount owed for each purchased item and adds each new debt to house and each interested housemate's debt to their respective profile
5. System removes charged items from the house purchase list
6. System updates house debt list and transaction list
7. System returns user to house purchase list

##Extensions:
**2a.** If user selects Charge household:
1. Resume at step 4

**2b.** If user selects Charge me:
1. System adds new debt for each item to house and user profile
2. Resume at step 5

##Special requirements:
+ Updates purchase list, house debt list, and individual housemate debt list/balance each within 30 seconds 90% of the time

##Frequency of occurrence:
+ Somewhat frequent

##Technology and data variations list:

