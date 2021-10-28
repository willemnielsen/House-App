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
+ Chore is added to house/housemate calendar/chore list
+ Domain.Housemate members involved are notified

###Main success scenario:
1. User selects option to add chore
2. System displays list of chores user can add (including custom chore)
3. User selects chore to be added from the list given by the system
4. System displays list of housemates in house
5. User selects who will do the chore
6. System prompts user to enter time and date of chore; includes recommended times based on user calendars
7. User enters time and date of chore
8. System displays frequency options for chore
9. User selects the frequency chore will be done(i.e. daily, weekly)
10. System distributes chore evenly (i.e. alternates who does it. )
11. System provides user with summary (chore, chore people, chore time, chore frequency) and asks for confirmation
12. User confirms
13. System requests calendar system to add event, with the given time, people and frequency attached.
14. System also adds chore to house chore list and involved housemate chore lists. 
15. System requests notification system to send notification to involved people.

###Extensions:
**7a.** If user chooses timing that conflicts with an event on the calendar:
1. System notifies user of conflict and asks if user wants to keep the chore time or change it.
   1. If user selects keep, system continues to happy path step #10
   2. If user selects change, system gives user two options: To change only the conflicting chore times or change them all
      1. If user selects change them all, system goes back to step 8. If user selects change conflicting, then system provides user with a way to change the chore times.

**9a.** If user chooses custom timing for the chore:
1. System displays generic calendar
2. User selects which days they want the chore to be assigned

**12a.** If user rejects:
1. System gives option to edit or delete chore
   1. If user selects delete chore:
      1. System asks for confirmation 
      2. User confirms
      3. System returns to chore list
   2. If user selects edit chore:
      1. System prompts user to select what they want to edit (chore, chore people, chore time, chore frequency)
      2. User edits information accordingly
         1. If time edit leads to conflict, follow 7a.
      3. System resumes at step 11.

###Frequency of occurrence:
+ Somewhat often
