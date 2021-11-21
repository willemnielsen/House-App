#Edit Spaces:
+ **Scope:** App -> House Spaces
+ **Level:** User goal
+ **Primary actor:** Domain.Housemate or the Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants an easy and efficient way to coordinate house spaces

###Preconditions:
+ The housemate editing a space is identified and authenticated
+ The user is in the house spaces menu

###Postconditions:
+ Space changes are made to House Spaces

###Main success scenario:
1. System displays all house spaces
2. User selects edit house spaces option
3. System provides user with the option to:
   1. Add a space
   2. Edit a space
   3. Delete a space
4. User selects add a space
5. System prompts user to enter space name and select times/days space is available for
6. User provides info
7. System adds space to house spaces
8. Loops to step 3

###Extensions:
**3a.** If user edit a space:
1. System provides list of house spaces
2. User selects space they would like to edit
3. System displays space information (name, available days/times)
4. User edits information
5. System edits space in house spaces
6. System notifies all housemates of changes
7. Loops back to step 3

**3b.** If user delete a space:
1. System provides list of house spaces
2. User selects space they would like to delete
3. System asks user for confirmation: lists all current reservations in the space
4. User confirms
5. System asks for confirmation from all users with current reservations in the space
   1. If all users give confirmation, system removes space from house spaces
   2. Else, system does not remove space and notifies original user of failed deletion
7. Loops back to step 3


###Special requirements:
+ Space edited within 30 seconds 90% of the time.

###Technology and data variations list:

###Frequency of occurrence:
+ Very often
