#Add events:
+ **Scope:** App -> Calendar
+ **Level:** User goal
+ **Primary actor:** Domain.Housemate or the Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants an easy and efficient way to plan events and coordinate calendars among housemates

###Preconditions:
+ The housemate adding events is identified and authenticated
+ The user is in the calendar

###Postconditions:
+ Events are added to the house calendar

###Main success scenario:
1. User can choose to Add event in calendar
2. System asks for event name, date, time, recurrence
3. User provides information
4. User selects to add event
5. System provides list of housemates
6. User selects which housemates to add to the event
7. System notifies all added housemates, loops back to step 2

###Extensions:
**3a.** If user does not provide valid input:
1. System will not proceed until valid inputs are provided

**6a.** If user does not select any housemates:
1. System will not proceed until at least 1 housemate is selected

###Special requirements:
+ Event added to calendar within 30 seconds 90% of the time.

###Technology and data variations list:

###Frequency of occurrence:
+ Very often
