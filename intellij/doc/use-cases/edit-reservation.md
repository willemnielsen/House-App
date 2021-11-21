#Edit Reservation:
+ **Scope:** App -> House Spaces
+ **Level:** User goal
+ **Primary actor:** Domain.Housemate or the Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants an easy and efficient way to coordinate house spaces

###Preconditions:
+ The housemate editing a reservation is identified and authenticated

###Postconditions:
+ Reservation calendar events are updated
+ House spaces available times are updated

###Main success scenario:
1. User selects reservation
2. System displays reservation information and option to delete reservation
3. User changes reservation information
4. System asks user to confirm choice
5. User confirms
6. System updates reservation information in house spaces and events in house calendar
7. System notifies all added housemates of reservation change

###Extensions:
**2a.** If user chooses to delete reservation:
1. System asks user to confirm choice
3. User confirms
4. System removes reservation from house space/event from house calendar
5. System notifies all interested housemates of deletion of reservation

**3a.** If user selects time that is unavailable:
1. System notifies user of conflict
2. User can choose to contact housemate with conflicting event, accept conflict, choose a different time or location
   1. If user selects “choose a different time or location”, system will provide suggestions (not mandatory to select one of the suggestions)
   2. If user selects "accept conflict", housemate with conflicting event will be notified and also asked to accept conflict
   1. If housemate with conflicting even does not accept, user will be notified and user event will not be scheduled
   
**5a.** If user does not confirm:
1. System takes user back to reservation description display

###Special requirements:
+ Reservation/events updated within 30 seconds 90% of the time.

###Technology and data variations list:

###Frequency of occurrence:
+ Sometimes (less frequent)
