#Remove Housemate:
+ Scope: App -> Housemate List
+ Level: User goal
+ Primary Actor: House Leader
+
###Stakeholders and interests:
+ **Domain.House Leader:** Wants a secure way to remove desired users from house

###Preconditions:
+ Have stable connection to server
+ House Leader is in the Housemate List

###Postconditions:
+ Housemate removed from house/house data

###Main success scenario:
1. System displays housemate list
2. User selects option to remove housemate
3. System displays a list of housemates
4. User selects housemate to remove
5. System asks user to confirm removal of housemate from house
6. User confirms
7. System removes housemate from house database
8. System notifies all other housemates of housemate removal
9. System returns user to housemate list

###Extensions:
**6a.** If user does not confirm:
1. Return to step 3

###Special requirements:
+ Having way to Identify users with in code → ID assignments

###Frequency of occurrence
+ Infrequent
