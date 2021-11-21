#Add Housemate:
+ Scope: App -> Housemate List
+ Level: User goal
+ Primary Actor: House Leader
+
###Stakeholders and interests:
+ **Domain.House Leader:** Wants a secure way to add desired users to house
+ **Domain.Housemate:** Wants a secure way to be added to a house

###Preconditions:
+ Have stable connection to server
+ House Leader is in the Housemate List

###Postconditions:
+ New user added to house

###Main success scenario:
1. System displays housemate list screen
2. User selects option to add housemate
3. System prompts user to enter the unique housemateID of housemate
4. User enters housemateID of housemate
5. System asks user to confirm addition of housemate to house
6. User confirms
7. System notifies added housemate of add request
8. System prompts housemate to accept add request
9. Housemate accepts add request
10. System adds housemate to house database
11. System notifies all other housemates of housemate addition 
12. Loop to step 3

###Extensions:
**4a.** If user does not enter valid housemateID:
1. System notifies user of invalid ID
2. Return to step 3

**6a.** If user does not confirm:
1. Return to step 3

**9a.** If Housemate does not accept request:
1. System notifies user of housemate request declined
2. System does not add housemate to house

###Special requirements:
+ Having way to Identify users with in code → ID assignments

###Frequency of occurrence
+ Infrequent
