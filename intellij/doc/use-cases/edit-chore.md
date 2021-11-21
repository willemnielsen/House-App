#Edit Chore:
+ **Scope:** App -> Chore list
+ **Level:** User goal
+ **Primary actor:** Domain.Housemate or the Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader that's editing the chore:** Wants to coordinate chores quickly and efficiently
+ **Other Housemates/Domain.House leader:** Wants to be notified when chores/responsibilities are changed

###Preconditions:
+ User is identified and authenticated
+ System is in house chore list

###Postconditions:
+ Chore is edited in the calendar/chore list
+ Domain.Housemate members involved are notified

###Main success scenario:
1. User selects chore they would like to edit
2. System displays chore information and option to delete chore
3. User edits chore information
4. System asks for confirmation
5. User confirms
6. System notifies all housemates linked to the chore of changes
7. System updates chore events in house calendar
8. System displays chore list

###Extensions:
**2a.** If user chooses to delete a chore:
1. System asks user to confirm choice
3. User confirms
4. System removes item from house chore list
5. System notifies all interested housemates of deletion of chore

**6a.** If user does not confirm:
1. System takes user back to chore information display

###Special requirements:
+ Chore list/house calendar updated within 30 seconds 90% of the time.

###Technology and data variations list:

###Frequency of occurrence:
+ Sometimes (less frequent)
