#Charge Housemates
+ **Scope:** App -> Shopping list -> Purchased Items
+ **Level:** User goal
+ **Primary Actor:** Housemate
###Preconditions: 
+ System is in the purchased items menu
		
##Postconditions: 
+ Each person's debt is adjusted accordingly 
##Main success scenario:
1. User selects option to charge housemates
2. System prompts user with options to
   1. Charge based on owner of items
   2. Charge evenly throughout house
   3. Charge me
   4. Charge custom
3. User selects charge based on owner of items
4. System notifies each housemate that they owe the user x amount
of dollars for items a, b and c.
5. System updates the debt database for each housemate

##Extensions:
	+ Even split
	+ **Divide bill evenly with subset of members who are liable for items purchaser
	+ **Select items that were purchased (as not to charge for items not purchased)
	+ **Delete Item

	+ Exact split
	+ **Divide bill with subset of members who are liable for items purchaser
	+ 	**If an item has no member associated with it call item Owner
	
	+ Receipt Path
	+ **Get receipt/s of purchases
	+ **Manual assignment Path
	+ **Each item on receipt/s promets Item Owner
	
	+ Auto assignment Path
	+ **Match shopping list items with receipt items 
	+ **Items on receipt that can’t be match call on Item Owner

##Special requirements: 
+ **For Receipt Path needs camera access

##Frequency of occurrence:
+ **Main Feature



#Item Owner:
+ **Scope: shopping list
+ **Level: system
+ **Primary Actor: Leader and User

##Stakeholders and interest: 
+	**Actor who made the payments needs to charge the current members
+ 	**Ask to choose a member to assign item in shopping list 

##Preconditions:
+	**Item in shopping list has not associated member

##Postconditions: 
+ **item in shopping list has a associated member

##Main success scenario:
+	**Actor selects member to charge for an item 

##Extensions:

##Special requirements: 
+ **Item’s will need members associated with it

##Technology and data variations list:

##Frequency of occurrence:
+ **Good amount
