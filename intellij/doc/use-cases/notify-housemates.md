#Notify Housemates:
+ Scope: App -> Notification list
+ Level: User goal
+ Primary Actor: Leader and User
+
###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants a way notify/communicate with housemates of any house changes they are involved in; wants to be reminded of house commitments

###Preconditions:
+ Have stable connection to server
+ Have access to a house

###Postconditions:
+ User notification list is updated

###Main success scenario:
1. System creates a new notification including type of notification, sub-system requiring notification (ie calendar, chores system, shopping list), description, and housemates involved
2. System adds notification to house/housemate notification list
3. System sends notification to housemates involved
4. User receives a notification and clicks on it
5. The system opens the notification menu

###Extensions:
**4a.** If user does not click on notification:
1. System stores notification on user device

###Special requirements:
+ Having access to all house sub-systems

###Frequency of occurrence
+ Every time a change involving the user occurs in app sub-systems (very often)
+ Every time an event is coming up (very often)
