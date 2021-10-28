#Delete Items:
+ **Scope:** Application -> Shopping list
+ **Level:** User Goal
+ **Primary actor:** Domain.Housemate or Domain.House Leader

###Stakeholders and interests:
+ **Domain.House Leader/Domain.Housemate:** Wants an easy way to remove items that the house/housemates no longer desire


###Preconditions:
+ The housemate/house leader removing items to the shopping list is identified and authenticated
+ System is in the shopping list menu

###Postconditions:
+ The house shopping list no longer lists the deleted items
###Main success scenario:
1. System displays house shopping list
2. User selects item from shopping list
3. System displays item description and  option to delete item
4. User selects delete item
5. System asks user to confirm choice
6. User confirms
7. System removes item from house shopping list
8. System notifies all interested housemates in removal of item

###Extensions:

**6a.** If user does not confirm:
1. System takes user back to item description display


###Special requirements:
System must update shopping list within 30 seconds 90% of the time.

###Frequency of occurrence:
Sometimes (less frequent)
