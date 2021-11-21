#Create House:
+ Scope: App -> Main Screen
+ Level: User goal
+ Primary Actor: House Leader
+
###Stakeholders and interests:
+ **Domain.House Leader:** Wants to create a way to connect with housemates to coordinate events, finances, and chores

###Preconditions:
+ Have stable connection to server
+ User is in main screen

###Postconditions:
+ User is a registered house leader of a house

###Main success scenario:
1. User enters main screen
2. User selects option to create a house
3. System prompts user to enter a house name
4. User enters house name
5. System asks user to confirm house generation with name
6. System creates house/generates unique houseID in database with user as the House Leader
7. System displays main menu of newly generated house

###Extensions:
**5a.** If user does not confirm:
1. Return to step 3

###Special requirements:
+ Having way to Identify houses with with a code → ID assignments

###Frequency of occurrence
+ Infrequent