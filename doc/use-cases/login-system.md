#Login System:
+ Scope: App
+ Level: User goal
+ Primary Actor: Leader and User
+ 
###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants a way to keep personal/house data private and secure

###Preconditions:
+ Have stable connection to server		
###Postconditions:
+ Have access to a house
###Main success scenario:
1. User opens application
2. System prompts user to login or create an account
3. User selects login
4. System prompts for username(or housemateID) of user and password
5. User enters username(or housemateID) and password
6. System verifies login
7. System logs in user and connects user with their house database
8. System displays main house menu
###Extensions:
**2a.** If user selects create an account:
1. System prompts user to create a username and password 
2. User enters desired username and password
   1. If username is taken, system re-prompts user
   2. If password does not meet requirements, re-prompts user
3. System generates and displays unique housemateID for user
4. System displays join house option

**7a** If user is not in a house:
1. System displays join house option

###Special requirements: 
+ Having way to Identify users with in code → ID assignments
###Frequency of occurrence
+ Every time app is opened (very often)
