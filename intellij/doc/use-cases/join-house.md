#Join House:
+ Scope: App -> Main Screen
+ Level: User goal
+ Primary Actor: User
+
###Stakeholders and interests:
+ **Domain.Housemate:** Wants to connect with housemates to coordinate events, finances, and chores
+ **Domain.Housemate Leader:** Wants a secure way to vet housemates/only add desired users

###Preconditions:
+ Have stable connection to server
+ User is in main screen

###Postconditions:
+ User is a registered member of a house

###Main success scenario:
1. User enters main screen
2. System provides user with a join house option
3. User selects join house
4. System prompts user for name/unique houseID of the house they want to join
5. User enters name/houseID of house
6. System displays list of matching names/houseIDs
7. User selects house they wish to join
8. System asks user to confirm
9. User confirms
10. System sends join request/notification to House Leader of house
11. House Leader confirms join request
12. System adds user to system
13. System notifies all other housemates of housemate addition
14. System displays house menu

###Extensions:
**9a.** If user does not confirm:
1. Return to step 4

**11a** If House Leader does not confirm request:
1. User is notified and system does not add user to the house

###Special requirements:
+ Having way to Identify houses with with a code → ID assignments

###Frequency of occurrence
+ Infrequent
