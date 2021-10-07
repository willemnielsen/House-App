#Assign Chores:
+ **Scope:** Housemates App
+ **Level:** User goal
+ **Primary actor:** Domain.Housemate or the Domain.House Leader

###Stakeholders and interests:
+ **Domain.Housemate/Domain.House Leader that's assigning the chore:** Wants to assign a chore quickly and easily at the right time, the
right frequency and to the right person
+ **Other Housemates/Domain.House leader:** Wants to be notified when they are assigned a chore
and not notified when they aren't involved in a chore

###Preconditions:
+ User is logged into their house

###Postconditions:
+ Chore is added to house calendar/chore list
+ Domain.House members involved are notified

###Main success scenario:
1. User selects option to add chore
2. User selects chore to be added from the list given by the system
   1. **Extension:** User chooses to input their own chore
3. User selects who will do the chore
4. User selects what time the chore will be done
5. User selects the frequency chore will be done(i.e. daily, weekly). System distributes chore evenly (i.e. alternates who does it. ). This doesn't create a time-conflict from calendar data.
   1. **Extension:** User chooses custom timing for the chore
   2. **Extension:** User chooses timing that conflicts with an event on the calendar. System notifies user of conflict and asks if user wants to keep the chore time or change it.
      1. If user selects keep, system continues to happy path step #6
      2. If user selects change, system gives user two options: To change only the conflicting chore times or change them all
      3. If user selects change them all, system goes back to step 4. If user selects change conflicting, then system provides user with a way to change the chore times.
6. System provides user with summary (chore, chore people, chore time, chore frequency) and asks for confirmation, user confirms
   1. Extension: User rejects. System gives option to edit or delete chore
      1. If user selects delete chore, System asks for confirmation and then returns to house home screen
      2. If user selects edit chore, system prompts user to select what they want to edit (chore, chore people, chore time, chore frequency)
      3. User selects option and system provides a way to edit this option after which the system returns to beginning of step 6.
7. After user confirmation system requests calendar system to add event, with the given time, people and frequency attached. System also adds chore to chore list. System requests notification system to send notification to involved people.

###Frequency of occurrence:
+ Somewhat often
