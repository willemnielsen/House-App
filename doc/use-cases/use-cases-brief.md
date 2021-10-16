##Brief Use Cases
1. Notify housemate
   1. System creates a new notification including type of notification, sub-system requiring notification (ie calendar, chores system, shopping list), description, and housemates involved
   2. System adds notification to house/housemate notification list
   3. System sends notification to housemates involved
   4. User receives a notification and clicks on it
   5. The system opens the notification menu
2. Edit chore 
   1. User enters the chore list interface
   2. System displays chore list
   3. User selects chore from chore list
   4. System prompts user with options, including edit chore
   5. System prompts user on what part of the chore they want to edit:
      1. Time
      2. Frequency
      3. People responsible for chore
      4. Chore type
   6. User selects edit option from list
   7. System provides screen where the option can be edited
   8. User edits option
   9. System displays changes and asks user to confirm
   10. User confirms
   11. System changes chore in the household chore list/chore list of housemates responsible
   12. System notifies all participating housemates of changes
   13. System returns to user chore list
3. Delete chore 
   1. User enters the chore list interface
   2. System displays chore list
   3. User selects chore from chore list
   4. System prompts user with options, including delete chore
   5. System displays selected chore/chore description and asks user to confirm
   6. User confirms
   7. System removes chore from house chore list/chore list of participating housemates
   8. System notifies all participating housemates of deletion
   9. System returns to user chore list
4. Create house
   1. User opens application
   2. User selects option to create a house
   3. System prompts user to enter a house name
   4. User enters house name
   5. System creates house/generates unique houseID in database with user as the House Leader
   6. System displays main menu of newly generated house
5. Add housemate 
   1. User selects house menu
   2. System displays house menu screen
   3. System provides option to add housemate
   4. User selects option to add housemate
   5. System prompts user to enter the unique housemateID of housemate
   6. User enters housemateID of housemate
   7. System asks user to confirm addition of housemate to house
   8. User confirms
   9. System adds housemate to house database
   10. System notifies all other housemates of housemate addition
   11. System returns user to house menu
6. Remove housemate
   1. User selects house menu
   2. System displays house menu screen
   3. System provides option to remove housemate
   4. User selects option to remove housemate
   5. System displays a list of housemates
   6. User selects housemate to remove
   7. System asks user to confirm removal of housemate from house
   8. User confirms
   9. System removes housemate from house database
   10. System notifies all other housemates of housemate removal
   11. System returns user to house menu
7. Join house
   1. User enters main screen
   2. System provides user with a join house option
   3. User selects join house
   4. System prompts user for name/unique houseID of the house they want to join
   5. User enters name/houseID of house
   6. System displays list of matching names/houseIDs
   7. User selects house they wish to join
   8. System asks user to confirm
   9. User confirms
   10. System sends join request/notification to House Leader of house
   11. House Leader confirms join request
   12. System adds user to system
   13. System notifies all other housemates of housemate addition
   14. System displays house menu