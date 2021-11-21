#Edit event:
+ **Scope:** App -> Calendar
+ **Level:** User goal
+ **Primary actor:** Domain.Housemate or the Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants an easy and efficient way to plan and coordinate events among housemates

###Preconditions:
+ The housemate editing events is identified and authenticated
+ The user is in the calendar

###Postconditions:
+ House calendar is updated

###Main success scenario:
1. User selects event
2. System displays event information and option to delete item
3. User changes event information
4. System asks user to confirm choice
5. User confirms
6. System updates event information in calendar
7. System notifies all event housemates of event change
8. System displays calendar

###Extensions:
**2a.** If user chooses to Delete an event:
1. If event is recurring:
    1. System will ask if user would like to delete single instance or all
       instances of recurring event
2. System asks for confirmation
3. If user confirms:
    1. Event removed from personal calendar and any participating housemates' calendars
    2. System notifies participating housemates of cancellation
    3. System returns to calendar
4. If user declines confirmation:
    1. System returns to event information

**4a.** If event is recurring:
1. System will ask if user would like to edit single instance or all instances
       of recurring event
2. User selects single instance or all instances
3. Continue from step 4

**5a.** If user does not confirm:
1. System takes user back to event info display

###Special requirements:
System must update calendar within 30 seconds 90% of the time.

###Frequency of occurrence:
+ Sometimes (less frequent)
    