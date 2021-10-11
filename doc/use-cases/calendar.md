#Calendar:
+ **Scope:** App
+ **Level:** User goal
+ **Primary actor:** Domain.Housemate or the Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader:** Wants an easy and efficient way to plan events and coordinate calendars among housemates

###Preconditions:
+ The housemate viewing/changing the calendar is identified and authenticated

###Postconditions:
+ All calendar changes are stored on the server
+ Calendar changes are applied to every housemate’s calendar

###Main success scenario:
1. Displays a color-coordinated calendar
    1. Each person has an assigned color
    2. User can select to display by month, week, or day
    3. User can select which housemate calendars they want to display
2. User can choose to:
    1. Add a personal event
        1. System will ask for event name, date, time, location (optional), description (optional), recurring (how many times a week, how many weeks, what additional days), who to add to event (optional; adds event to housemate's personal calendar)
    2. Reserve a space
        1. System will ask to select a space, show which dates/times the space is available (but user can still select already reserved times in case housemates want multiple events in same space)
        2. Once chosen, system will ask for event name, date, time, description (optional), recurring (how many times a week, how many weeks, what additional days), who to add to event (optional; adds event to housemate's personal calendar)
    3. Cancel an event
    4. Edit an event
3. System will notify user of upcoming events
    1. Users can select when they want to be notified before an event ( 1hr, 30min, 15min, 5min, etc.)

###Extensions:

**2.i.b.** If there are overlapping events:
1. System notifies user of conflict
2. User can choose to accept conflict or choose a different time or location
    1. If user selects “choose a different time or location”, system will provide suggestions (not mandatory to select one of the suggestions)

**2.ii.c.** If there are overlapping events:
1. System notifies user of conflict
2. User can choose to contact housemate with conflicting event, accept conflict, choose a different time or location
    1. If user selects “choose a different time or location”, system will provide suggestions (not mandatory to select one of the suggestions)
    2. If user selects "accept conflict", housemate with conflicting event will be notified and also asked to accept conflict
        1. If housemate with conflicting even does not accept, user will be notified and user event will not be scheduled

**2.iii.a.** If event is recurring:
1. System will ask if user would like to cancel single instance or all
   instances of recurring event

**2.iv.a.** If event is recurring:
2. System will ask if user would like to edit single instance or all instances
   of recurring event

**2.iv.b.** If edit leads to conflict:
1. If a personal event: follow **2.i.b.**
2. If a reserved space: follow **2.ii.c.**

###Special requirements:
+ Calendar must be legible from 6 in. away
+ Calendar change response within 30 seconds 90% of the time.

###Technology and data variations list:

###Frequency of occurrence:
+ Very often
