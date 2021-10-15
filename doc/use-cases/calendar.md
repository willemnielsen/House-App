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
1. System displays a color-coordinated calendar by week (Each person has an assigned color)
2. System displays options of display by month, week, or day
3. System displays housemates names along calendar
4. User selects which housemate calendars they want to display
5. User can choose to:
    1. Add a personal event
    2. Reserve a space
    3. Cancel an event
    4. Edit an event
6. User selects Add personal event
7. System asks for event name, date, time, location (optional), description (optional), recurring (how many times a week, how many weeks, what additional days), who to add to event (optional)
8. User provides information
9. System adds event to user's personal calendar
10. System notifies user of upcoming events

###Extensions:
**2a.** If user can select to display by month:
1. System modifies display to show calendar by month

**2b.** If user can select to display by day:
1. System modifies display to show calendar by day

**5a.** If user selects Reserve a space:
1. System asks user to select a space
2. User selects space
3. System shows which dates/times the space is available (but user can still select already reserved times in case housemates want multiple events in same space)
4. User chooses a time/date
5. System asks for event name, date, time, description (optional), recurring (how many times a week, how many weeks, what additional days), who to add to event (optional)
6. Resume step 8

**5b.** If user chooses to Cancel an event:
1. System shows event information
2. If event is recurring:
   1. System will ask if user would like to cancel single instance or all
      instances of recurring event
3. System asks for confirmation
4. If user confirms:
   1. Event removed from personal calendar and any participating housemates' calendars
   2. System notifies participating housemates of cancellation
   3. System returns to calendar
5. If user declines confirmation:
   1. System returns to calendar

**5c.** If user chooses to Edit an event:
1. System shows event information fields
2. User edits information
3. If event is recurring:
   1. System will ask if user would like to edit single instance or all instances
      of recurring event
4. System shows new event information 
5. If edit leads to conflict:
   1. Follow 9a.
6. and asks for confirmation
7. If user confirms:
   1. Event edited on all participating housemates' calendars
   2. System returns to calendar
8. If user declines confirmation:
    1. Event not edited
    2. System returns to calendar

**8a.** If user added housemate to personal event:
1. System adds event to added housemate's personal calendar


**9a.** If there are overlapping events:
1. If adding event to personal calendar:
   1. System notifies user of conflict
   2. User can choose to accept conflict or choose a different time or location
       1. If user selects “choose a different time or location”, system will provide suggestions (not mandatory to select one of the suggestions)
2. If reserving a space:
   1. System notifies user of conflict
   2. User can choose to contact housemate with conflicting event, accept conflict, choose a different time or location
       1. If user selects “choose a different time or location”, system will provide suggestions (not mandatory to select one of the suggestions)
       2. If user selects "accept conflict", housemate with conflicting event will be notified and also asked to accept conflict
           1. If housemate with conflicting even does not accept, user will be notified and user event will not be scheduled
           

###Special requirements:
+ Calendar must be legible from 6 in. away
+ Calendar change response within 30 seconds 90% of the time.

###Technology and data variations list:

###Frequency of occurrence:
+ Very often
