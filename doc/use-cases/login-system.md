#Login System:
+ Scope: App
+ Level: User goal
+ Primary Actor: Leader and User
###Preconditions:
+ Have stable connection to server		
###Postconditions:
+ Have access to a house
###Main success scenario:
+ Joining a Domain.House
  + Enter a Domain.House code
  + Enter Name or Alias that will be displayed to others
###Extensions:
+ Creating a Domain.House
  + Name or Alias of Domain.House
  + Name of the User making Domain.House
    + Will be the leader of said Domain.House

+ Already in Domain.House
  + precondition : have joined or created a house
  + login user in to previous house that they were in when last on app
###Special requirements: 
+ Having way to Identify users with in code → ID assignments
###Frequency of occurrence
+ Every time app is opened (very often)
