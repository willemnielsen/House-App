#Delete Items:
+ **Scope:** Application -> Shopping list
+ **Level:** User Goal
+ **Primary actor:** Domain.Housemate or Domain.House Leader

###Stakeholders and interests:
+ **Domain.House Leader/Domain.Housemate:** Wants an easy way to edit shopping list items and remove items that the house/housemates no longer desire


###Preconditions:
+ The housemate/house leader editing items is identified and authenticated
+ System is in the shopping list menu

###Postconditions:
+ The house shopping list is updated with new item info/deletions

###Main success scenario:
1. User selects item
2. System displays item information and option to delete item
3. User changes item information
4. System asks user to confirm choice
5. User confirms
6. System updates item information in shopping list
7. System notifies all added housemates of shopping list change
8. System displays house shopping list

###Extensions:
**2a.** If user chooses to delete item:
1. System asks user to confirm choice
3. User confirms
4. System removes item from house shopping list
5. System notifies all interested housemates of deletion of item

**6a.** If user does not confirm:
1. System takes user back to item description display

###Special requirements:
System must update shopping list within 30 seconds 90% of the time.

###Frequency of occurrence:
+ Sometimes (less frequent)
