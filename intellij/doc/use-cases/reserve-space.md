#Reserve Space:
+ **Scope:** App -> House Spaces
+ **Level:** User goal
+ **Primary actor:** Domain.Housemate or the Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants an easy and efficient way to coordinate house spaces

###Preconditions:
+ The housemate reserving a space is identified and authenticated
+ The user is in the house spaces menu

###Postconditions:
+ All reservations are added to the calendar
+ House spaces are updated

###Main success scenario:
1. System displays all house spaces
2. User selects a space to reserve
3. System shows user available and current reservations for the space/prompts user to enter reservation time
4. User enters time they would like to reserve space for
5. System prompts user to select housemates they would like to add to the reservation
6. User selects housemates
7. System adds reservation to house space and reservation event to house calender
8. System loops back to step 1

###Extensions:
**4a.** If user selects time that is unavailable:
1. System notifies user of conflict
2. User can choose to contact housemate with conflicting event, accept conflict, choose a different time or location
        1. If user selects “choose a different time or location”, system will provide suggestions (not mandatory to select one of the suggestions)
        2. If user selects "accept conflict", housemate with conflicting event will be notified and also asked to accept conflict
            1. If housemate with conflicting even does not accept, user will be notified and user event will not be scheduled


###Special requirements:
+ Reservation/events added within 30 seconds 90% of the time.

###Technology and data variations list:

###Frequency of occurrence:
+ Sometimes
