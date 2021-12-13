# Add Item Domain Model
```plantuml
@startuml
hide circle
hide empty methods

class House{
    houseName
    houseID
}
class ShoppingList{ 
}
class LineItem{
    quantity
}

class Item{
    price
    name
}

class Debt{
    owed
}


class Housemate{   
    name
    housemateId
}
class Calendar{

}

class Event{
    name
    date
    startTime
    endTime
}

class Recurrence{
    frequency
    startDate
    endDate
}

' associations
House "1" -- "1..*" Housemate : \tContains\t\t
House "1" -- "1..*" ShoppingList : \tContains\t\t
House "1" -- "1" LineItem : \tPurchased\t\t
House "1" -- "1" Calendar : Contains
Housemate "1..*" - "0..*" LineItem : \tOwns\t\t
ShoppingList "1" -- "0..*" LineItem : \tContains\t\t
LineItem "*" - "1...*" Item : \tDescribed by \t\t
Housemate "1..*" -down- "0..*" Debt : \tOwed by\t\t
Debt "1*" -right- "1" LineItem : for
Housemate "1" - "0..*" LineItem : Purchases
Calendar "1" - "0..*" Event : Contains

@enduml
```

# Add Event to Calendar Sequence Diagram
```plantuml

@startuml
actor Housemate as Actor

    addEventView -->> Actor  : get name, date, time, interestedHouseMates and recurrence
    addEventView -->> ControllerActivity  : onAddedEvent(name, date, startTime, endTime, interestedHouseMates, recurrence)
    ControllerActivity -->> HouseController : addEventToCalendar(name, date, startTime, endTime, getInterestedHouseMates, recurrence)
    HouseController -->> Calendar : addEvent(name, date, startTime, endTime, getInterestedHouseMates, recurrence)
    loop until recurrence.endDate
        Calendar -->> Event ** : Event(name, date, stratTime, endTime)
    end
@enduml
```

# Add Item to Shopping List Sequence Diagram
```plantuml

@startuml
actor Housemate as Actor

    addItemFragment -->> Actor  : get name, date, time, interestedHouseMates and recurrence
    addItemFragment -->> ControllerActivity  : onAddedItem(name, quantity, price, interestedHMs)
    ControllerActivity -->> HouseController : addLineItemToShoppingList(quantity, name, price, interestedHouseMates)
    HouseController -->> House : addLineItemToShoppingList(quantity, name, price, interestedHouseMates)
    House -->> ShoppingList : addItem(quantity, name, price, interestedHouseMates)
    
@enduml
```


# Create House Sequence Diagram
```plantuml
@startuml
actor User as Actor
    loginScreenFragment -->> Actor  : get House Name, House Password
    loginScreenFragment -->> ControllerActivity  : onRegisterHouse(houseName, housePassword)
    ControllerActivity -->> IPersistenceFacade: createHouseIfNotExists()
    IPersistenceFacade -->> ControllerActivity : onYesResult()
    ControllerActivity -->> loginScreenFragment : onRegisterHouseSuccess()
@enduml
```

# Log in to House Sequence Diagram
```plantuml
@startuml
actor User as Actor
    loginScreenFragment -->> Actor  : get House Name, House Password
    loginScreenFragment -->> ControllerActivity  : onHouseSigninAttempt(houseName, housePassword)
    ControllerActivity -->> IPersistenceFacade: retrieveHouse()
    IPersistenceFacade -->> ControllerActivity : onDataRecieved() 
    ControllerActivity -->> HouseController : getHouse()
    HouseController -->> House : validatePassword()
    HouseController -->> ControllerActivity : passwordValidated()
    ControllerActivity -->> HouseController : getHouse()
    HouseController -->> House : setAuthKey()
    ControllerActivity --> AuthFragment **
@enduml
``` 

# Join House Sequence Diagram
```plantuml
@startuml
actor User as Actor
    loginScreenFragment -->> Actor  : get Housemate name, Housemate password
    loginScreenFragment -->> ControllerActivity  : onRegister(name, password)
    ControllerActivity -->> IPersistenceFacade: createUserIfNotExists()
    IPersistenceFacade -->> ControllerActivity : onYesResult()
    ControllerActivity -->> loginScreenFragment : onRegisterHouseSuccess()
@enduml
```

# Log in as User Sequence Diagram
```plantuml
@startuml
actor User as Actor
    loginScreenFragment -->> Actor  : get Name, Password
    loginScreenFragment -->> ControllerActivity  : onSigninAttempt(name, password)
    ControllerActivity -->> IPersistenceFacade: retrieveUser()
    IPersistenceFacade -->> ControllerActivity : onDataRecieved() 
    ControllerActivity -->> HouseController : getHousemate()
    HouseController -->> Housemate : validatePassword()
    HouseController -->> ControllerActivity : passwordValidated()
    ControllerActivity -->> HouseController : getHousemate()
    HouseController -->> Housemate : setAuthKey()
    ControllerActivity --> HousemateFragment **
@enduml
``` 
# Purchase Item Sequence Diagram
```plantuml
@startuml
actor Housemate as Actor
    loop until done
        ShoppingListScreenFragment -->> Actor  : get lineItem
        ShoppingListScreenFragment -->> ControllerActivity  : onPurchaseItems(lineItem)
        ControllerActivity -->> HouseController : addToPurchase(lineItem)
        HouseController -->> House : addToPurchase(lineItem)
        
        ControllerActivity --> ShoppingListScreenFragment : updateDisplay(houseController.getHouse().getShoppingList())
        ControllerActivity --> ShoppingListScreenFragment : updatePurchasedList(houseController.getHouse().getPurchasedItems())

    end
    
@enduml
```

# Checkout Sequence Diagram
```plantuml
@startuml
actor Housemate as Actor
    PurchasedListScreenFragment -->> Actor  : get distribution
    PurchasedListScreenFragment -->> ControllerActivity  : onPurchaseByUser(distribution)
    ControllerActivity -->> HouseController : checkout(distribution, houseController.getLoggedInUser())
    HouseController -->> House : checkout(distribution, houseController.getLoggedInUser())
    ControllerActivity -->> PurchasedListScreenFragment : updatePurchasedList(houseController.getHouse().getPurchasedItems())
@enduml
```


# Class Diagram for Domain
```plantuml
@startuml
class ShoppingList {
+ boolean addItem(int,String,float,List<Housemate>)
+ void addLineItem(LineItem)
+ LineItem getShoppingListLineItem(int)
+ void remove(LineItem)
+ int size()
+ void clear()
+ String toString()
+ List<LineItem> getShoppingList()
+ void setShoppingList(List<LineItem>)
}
class AuthKey {
+ void setSalt(String)
+ String getSalt()
+ void setKey(String)
+ String getKey()
+ boolean validatePassword(String)
+ String toString()
+ boolean equals(Object)
}
class Item {
+ void setPrice(float)
+ float getPrice()
+ String getName()
+ void setName(String)
+ String toString()
}
class Housemate {
+ List<Debt> debtlist
+ String getUsername()
+ void setUsername(String)
+ AuthKey getAuthKey()
+ void setAuthKey(AuthKey)
+ boolean validatePassword(String)
+ String myBalance()
+ String getName()
+ void setName(String)
+ List<Debt> getDebtlist()
+ void setDebtlist(List<Debt>)
+ long getHousemateId()
+ void setHousemateId(long)
+ String toString()
+ boolean equals(Object)
}
class Recurrence {
+ String getFrequency()
+ Date getEndDate()
+ Date getStartDate()
+ void setFrequency(String)
+ void setEndDate(Date)
+ void setStartDate(Date)
}
class HouseController {
+ void addHousemate(Housemate)
+ void setUser(Housemate)
+ void setHouse(House)
+ Housemate getLoggedInUser()
+ void removeHousemate(Housemate)
+ void checkout(String,Housemate)
+ LineItem getShoppingListLineItem(int)
+ boolean addLineItemToShoppingList(int,String,float,List<Housemate>)
+ int getShoppingListSize()
+ String shoppingListToString()
+ void loadShoppList(ShoppingList)
+ void loadDebtList(List<Debt>)
+ void addToPurchase(LineItem)
+ House getHouse()
+ Housemate getHousemate(String)
+ String houseTransactions()
+ String houseBalance()
+ List<Housemate> getHousemates()
+ Event getThisEvent(Event)
+ boolean addEventToCalendar(String,Date,Time,Time,List<Housemate>,Recurrence)
+ void loadCalendar(Calendar)
}
class Event {
+ String getName()
+ void setName(String)
+ Date getDate()
+ void setDate(Date)
+ Time getStartTime()
+ void setStartTime(Date)
+ Time getEndTime()
+ void setEndTime(Date)
+ List<Housemate> getHousemates()
+ void setHousemates(List<Housemate>)
+ String toString()
}
class Debt {
+ void setDebtorAuthKey(AuthKey)
+ AuthKey getDebtorAuthKey()
+ void setCreditorAuthKey(AuthKey)
+ AuthKey getCreditorAuthKey()
+ LineItem getLineItem()
+ void setLineItem(LineItem)
+ void setOwed(float)
+ void setPaid(boolean)
+ float getOwed()
+ boolean equals(Object)
}
class Calendar {
+ boolean addEvent(String,Date,Time,Time,List<Housemate>,Recurrence)
+ void addThisEvent(Event)
+ void remove(Event)
+ void setEvents(List<Event>)
+ List<Event> getEvents()
+ Event getThisEvent(Event)
+ boolean setCurrentDate(Date)
+ {static} void sort(List<Event>)
+ String toString()
}
class House {
~ int houseID
+ AuthKey getAuthKey()
+ void setAuthKey(AuthKey)
+ boolean validatePassword(String)
+ String getName()
+ boolean addLineItemToShoppingList(int,String,float,List<Housemate>)
+ LineItem getShoppingListLineItem(int)
+ void loadShoppingList(ShoppingList)
+ void loadHousemates(List<Housemate>)
+ void loadPurchasedList(List<LineItem>)
+ int getShoppingListSize()
+ ShoppingList getShoppingList()
+ List<LineItem> getPurchasedItems()
+ String addHousemate(Housemate)
+ String removeHousemate(Housemate)
+ void createDebtForIHM(LineItem)
+ void createDebtForHH(LineItem)
+ void createDebtForMe(LineItem)
+ void checkout(String,Housemate)
+ String houseTransactions()
+ String houseBalance()
+ List<Housemate> getHousemates()
+ Calendar getCalendar()
+ void loadCalendar(Calendar)
+ Housemate getHousemate(AuthKey)
+ List<Housemate> getHousemateList(List<AuthKey>)
+ void loadDebtList(List<Debt>)
+ List<Debt> getHousedebt()
+ void setHousedebt(List<Debt>)
}
class LineItem {
+ int getQuantity()
+ void addPurchaser(Housemate)
+ void setQuantity(int)
+ List<AuthKey> getInterestedHouseMatesAuthKet()
+ void setInterestedHouseMatesAuthKet(List<AuthKey>)
+ AuthKey getPurchaserAuthKey()
+ void setPurchaserAuthKey(AuthKey)
+ float getPrice()
+ void setPrice(float)
+ String getName()
+ void setName(String)
+ String toString()
}
House -> "(1) \nshoppingList\n{List}" ShoppingList : \t\t\t\t
 House --> "(1) \nhousemates\n{List}" Housemate : \t\t\t\t
 House --> "(1..*) \npurchasedItems\n{List}" LineItem : \t\t\t\t
 House -> "(1..*) \nhousedebt\n{List}" Debt : \t\t\t\t
House --> "(1..*) \nshoppingList\n{List}" LineItem : \t\t\t\t
House --> "(1)" Calendar 
Calendar --> "(0..*) \nevents\n{List}" Event : \t\t\t\t
Event --> "(0..*) \nhousemates\n{List}" Housemate : \t\t\t\t
LineItem --> "(1) \ninterestedHouseMates\n{List}" Housemate : \t\t\t\t
LineItem --> "(1) \nPurchaser\n{List}" Housemate : \t\t\t\t
LineItem *-- Item
Housemate --> "(1..*) \ndebtlist\n{List}" Debt : \t\t\t\t
Housemate --> "(1)" AuthKey 
Debt -> "(1) \ndebtor\n" Housemate : \t\t\t\t
Debt -> "(1) \ncreditor\n" Housemate : \t\t\t\t
Debt -> "(1..*) \nlineItem\n" LineItem : \t\t\t\t
HouseController --> "(1) \nhouse\n{House}" House :\t\t\t\t
Serializable <|.. ShoppingList
Serializable <|.. AuthKey
Serializable <|.. Item
Serializable <|.. Housemate
Serializable <|.. Recurrence
Serializable <|.. Event
Serializable <|.. Debt
Serializable <|.. Calendar
Serializable <|.. House
Serializable <|.. LineItem
@enduml
```

# Class Diagram for Controller
```plantuml
@startuml
class edu.vassar.cmpu.test.ControllerActivity {
# void onCreate(Bundle)
+ void openHomeScreen()
+ void onRegisterHouse(String,String,ILoginScreenFragment)
+ void onHouseSigninAttempt(String,String,ILoginScreenFragment)
+ void onRegister(String,String,IAuthView)
+ void onSigninAttempt(String,String,IAuthView)
+ void onOpenShoppingList()
+ void onOpenCalendar()
+ void onOpenHousemateList()
+ void onOpenPurchasedList()
+ void onOpenTransactions()
+ void onPreviousOnTransactionsScreen()
+ void openShoppingListScreen()
+ void onAddItem()
+ void onPreviousOnShoppingListScreen()
+ void onPurchaseItems(LineItem,IShoppingListScreenView)
+ void openAddItemScreen()
+ void onAddedItem(String,int,float,List<Housemate>,IAddItemView)
+ void onPreviousInAddItemFragment()
+ void openCalendarScreen()
+ void onAddEvent()
+ void onPreviousOnCalendarScreen()
+ void openAddEventScreen()
+ void onAddedEvent(String,Date,Time,Time,List<Housemate>,String,Date,IAddEventView)
+ void onSetDate(Date,ICalendarScreenView)
+ void onPreviousInAddEventFragment()
+ void openHousemateListScreen()
+ void onPreviousOnHousemateListScreen()
+ void onAddHousemateOnHousemateListScreen()
+ void onDebtScreenButton()
+ void onPreviousOnDebtScreen()
+ void onAddHousemate(String)
+ void onPreviousOnAddHousemateScreen()
+ void openPurchasedListScreen()
+ void onPreviousOnPurchasedListScreen()
+ void onPurchaseByUser(String,IPurchasedListScreenFragment)
+ void onShoppingListReceived(ShoppingList)
+ void onCalendarReceived(Calendar)
+ void addEventToDatabase(String,Date,Time,Time,List<Housemate>,Recurrence)
+ void onDebtListReceived(List<Debt>)
}


edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.ShoppingListListener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.CalendarListener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.DebtListListener <|.. edu.vassar.cmpu.test.ControllerActivity
edu.vassar.cmpu.test.Listener <|.. edu.vassar.cmpu.test.ControllerActivity
androidx.appcompat.app.AppCompatActivity <|-- edu.vassar.cmpu.test.ControllerActivity
@enduml
```

# Class Diagram for View
```plantuml
@startuml
interface edu.vassar.cmpu.test.view.calendarScreen.ICalendarScreenView {
~ void updateDisplay(Calendar)
}
interface edu.vassar.cmpu.test.view.calendarScreen.ICalendarScreenView.Listener {
~ void onAddEvent()
~ void onSetDate(Date,ICalendarScreenView)
~ void onPreviousOnCalendarScreen()
}
class edu.vassar.cmpu.test.view.calendarScreen.CalendarScreenFragment {
- Listener listener
- FragmentCalendarMonthBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void updateDisplay(Calendar)
}
interface edu.vassar.cmpu.test.view.addEventView.IAddEventView {
~ void getAddedHouseMates(List<Housemate>)
~ void updateDisplay(Calendar)
}
interface edu.vassar.cmpu.test.view.addEventView.IAddEventView.Listener {
~ void onAddedEvent(String,Date,Time,Time,List<Housemate>,String,Date,IAddEventView)
~ void onPreviousInAddEventFragment()
}
class edu.vassar.cmpu.test.view.addEventView.AddEventFragment {
- FragmentAddEventBinding binding
- Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void getAddedHouseMates(List<Housemate>)
- List<Housemate> CreateDialog(List<Housemate>,String,Date,Time,Time,String,Date)
+ void onAddedEvent(String,Date,Time,Time,ArrayList<Housemate>,String,Date)
+ void updateDisplay(Calendar)
+ void onItemSelected(AdapterView<?>,View,int,long)
+ void onNothingSelected(AdapterView<?>)
}


edu.vassar.cmpu.test.view.calendarScreen.ICalendarScreenView +.. edu.vassar.cmpu.test.view.calendarScreen.ICalendarScreenView.Listener
edu.vassar.cmpu.test.view.calendarScreen.ICalendarScreenView <|.. edu.vassar.cmpu.test.view.calendarScreen.CalendarScreenFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.calendarScreen.CalendarScreenFragment
edu.vassar.cmpu.test.view.addEventView.IAddEventView +.. edu.vassar.cmpu.test.view.addEventView.IAddEventView.Listener
edu.vassar.cmpu.test.view.addEventView.IAddEventView <|.. edu.vassar.cmpu.test.view.addEventView.AddEventFragment
edu.vassar.cmpu.test.view.addEventView.OnItemSelectedListener <|.. edu.vassar.cmpu.test.view.addEventView.AddEventFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.addEventView.AddEventFragment
@enduml
```

# Class Diagram for View Part 2
```plantuml
@startuml
class edu.vassar.cmpu.test.view.purchasedListScreen.PurchasedListScreenFragment {
- FragmentPurchasedBinding binding
- Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void updatePurchasedList(List<LineItem>)
+ void onViewCreated(View,Bundle)
+ void CreateDialog()
}
interface edu.vassar.cmpu.test.view.addItemView.IAddItemView {
~ void getHouseMates(List<Housemate>)
~ void updateDisplay(ShoppingList)
}
interface edu.vassar.cmpu.test.view.addItemView.IAddItemView.Listener {
~ void onAddedItem(String,int,float,List<Housemate>,IAddItemView)
~ void onPreviousInAddItemFragment()
}
interface edu.vassar.cmpu.test.view.purchasedListScreen.IPurchasedListScreenFragment {
~ void updatePurchasedList(List<LineItem>)
}
interface edu.vassar.cmpu.test.view.purchasedListScreen.IPurchasedListScreenFragment.Listener {
~ void onPreviousOnPurchasedListScreen()
~ void onPurchaseByUser(String,IPurchasedListScreenFragment)
}
interface edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView {
~ void updateDisplay(ShoppingList)
~ void purchaseItems(ShoppingList)
~ void updatePurchasedList(List<LineItem>)
}
interface edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView.Listener {
~ void onAddItem()
~ void onPurchaseItems(LineItem,IShoppingListScreenView)
~ void onPreviousOnShoppingListScreen()
}
class edu.vassar.cmpu.test.view.addItemView.AddItemFragment {
- {static} String ITEM_NAME
- {static} String ITEM_QUANTITY
- {static} String ITEM_PRICE
- FragmentAddItemBinding binding
- Listener listener
- HouseController house
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
- void CreateDialog(List<Housemate>,String,int,float)
+ void updateDisplay(ShoppingList)
+ void getHouseMates(List<Housemate>)
+ void onSaveInstanceState(Bundle)
+ void onViewStateRestored(Bundle)
}
class edu.vassar.cmpu.test.view.shoppingListScreen.ShoppingListScreenFragment {
- Listener listener
- FragmentShoppingListScreenBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
- ArrayList<LineItem> CreateDialog(ShoppingList)
+ void updateDisplay(ShoppingList)
+ void onPurchaseItems(LineItem)
+ void updatePurchasedList(List<LineItem>)
+ void purchaseItems(ShoppingList)
}


edu.vassar.cmpu.test.view.purchasedListScreen.IPurchasedListScreenFragment <|.. edu.vassar.cmpu.test.view.purchasedListScreen.PurchasedListScreenFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.purchasedListScreen.PurchasedListScreenFragment
edu.vassar.cmpu.test.view.addItemView.IAddItemView +.. edu.vassar.cmpu.test.view.addItemView.IAddItemView.Listener
edu.vassar.cmpu.test.view.purchasedListScreen.IPurchasedListScreenFragment +.. edu.vassar.cmpu.test.view.purchasedListScreen.IPurchasedListScreenFragment.Listener
edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView +.. edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView.Listener
edu.vassar.cmpu.test.view.addItemView.IAddItemView <|.. edu.vassar.cmpu.test.view.addItemView.AddItemFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.addItemView.AddItemFragment
edu.vassar.cmpu.test.view.shoppingListScreen.IShoppingListScreenView <|.. edu.vassar.cmpu.test.view.shoppingListScreen.ShoppingListScreenFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.shoppingListScreen.ShoppingListScreenFragment
@enduml
```

# Class Diagram for View Part 3
```plantuml
@startuml
class edu.vassar.cmpu.test.view.homeScreen.HomeScreenFragment {
- Listener listener
- FragmentHomeScreenBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
}
interface edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment {
~ void onRegisterHouseSuccess()
~ void onInvalidHouseCredentials()
~ void onHouseAlreadyExists()
}
interface edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment.Listener {
~ void onRegisterHouse(String,String,ILoginScreenFragment)
~ void onHouseSigninAttempt(String,String,ILoginScreenFragment)
}
interface edu.vassar.cmpu.test.view.homeScreen.IHomeScreenFragment {
}
interface edu.vassar.cmpu.test.view.homeScreen.IHomeScreenFragment.Listener {
+ void onOpenShoppingList()
+ void onOpenCalendar()
+ void onOpenHousemateList()
+ void onOpenPurchasedList()
+ void onOpenTransactions()
}
class edu.vassar.cmpu.test.view.authScreen.AuthFragment {
- {static} String IS_REGISTERED
- Listener listener
- FragmentAuthBinding binding
- boolean isRegistered
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void onSaveInstanceState(Bundle)
+ void onRegisterSuccess()
- void activateRegisteredConfig()
+ void onInvalidCredentials()
+ void onUserAlreadyExists()
- void displayMessage(int)
}
class edu.vassar.cmpu.test.view.loginScreen.LoginScreenFragment {
- {static} String IS_REGISTERED
- ILoginScreenFragment.Listener listener
- FragmentLoginScreenBinding binding
- boolean isRegistered
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void onSaveInstanceState(Bundle)
+ void onRegisterHouseSuccess()
- void activateRegisteredConfig()
+ void onInvalidHouseCredentials()
+ void onHouseAlreadyExists()
- void displayMessage(int)
}
interface edu.vassar.cmpu.test.view.authScreen.IAuthView {
~ void onRegisterSuccess()
~ void onInvalidCredentials()
~ void onUserAlreadyExists()
}
interface edu.vassar.cmpu.test.view.authScreen.IAuthView.Listener {
~ void onRegister(String,String,IAuthView)
~ void onSigninAttempt(String,String,IAuthView)
}


edu.vassar.cmpu.test.view.homeScreen.IHomeScreenFragment <|.. edu.vassar.cmpu.test.view.homeScreen.HomeScreenFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.homeScreen.HomeScreenFragment
edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment +.. edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment.Listener
edu.vassar.cmpu.test.view.homeScreen.IHomeScreenFragment +.. edu.vassar.cmpu.test.view.homeScreen.IHomeScreenFragment.Listener
edu.vassar.cmpu.test.view.authScreen.IAuthView <|.. edu.vassar.cmpu.test.view.authScreen.AuthFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.authScreen.AuthFragment
edu.vassar.cmpu.test.view.loginScreen.ILoginScreenFragment <|.. edu.vassar.cmpu.test.view.loginScreen.LoginScreenFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.loginScreen.LoginScreenFragment
edu.vassar.cmpu.test.view.authScreen.IAuthView +.. edu.vassar.cmpu.test.view.authScreen.IAuthView.Listener
@enduml
```

# Class Diagram for View Part 4
```plantuml
@startuml
class edu.vassar.cmpu.test.view.housemateListScreen.HousemateListScreenFragment {
- Listener listener
- FragmentHousematesBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void updateDisplay(List<Housemate>)
}
interface edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.IAddHousemate {
}
interface edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.IAddHousemate.Listener {
~ void onAddHousemate(String)
~ void onPreviousOnAddHousemateScreen()
}
interface edu.vassar.cmpu.test.view.housemateListScreen.IHousemateListScreenFragment {
~ void updateDisplay(List<Housemate>)
}
interface edu.vassar.cmpu.test.view.housemateListScreen.IHousemateListScreenFragment.Listener {
~ void onPreviousOnHousemateListScreen()
~ void onAddHousemateOnHousemateListScreen()
~ void onDebtScreenButton()
}
interface edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.IDebtScreenFragment {
+ void updateDisplay(String)
}
interface edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.IDebtScreenFragment.Listener {
~ void onPreviousOnDebtScreen()
}
class edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.AddHousemateFragment {
~ FragmentAddHousemateBinding binding
~ Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
}
class edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.DebtScreenFragment {
~ FragmentDebtScreenBinding binding
~ Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void updateDisplay(String)
}
interface edu.vassar.cmpu.test.view.transactionsScreen.ITransactionsScreenFragment {
~ void updateDisplay(String)
}
interface edu.vassar.cmpu.test.view.transactionsScreen.ITransactionsScreenFragment.Listener {
~ void onPreviousOnTransactionsScreen()
}
class edu.vassar.cmpu.test.view.transactionsScreen.TransactionsScreenFragment {
~ FragmentTransactionsScreenBinding binding
~ Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void updateDisplay(String)
}


edu.vassar.cmpu.test.view.housemateListScreen.IHousemateListScreenFragment <|.. edu.vassar.cmpu.test.view.housemateListScreen.HousemateListScreenFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.housemateListScreen.HousemateListScreenFragment
edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.IAddHousemate +.. edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.IAddHousemate.Listener
edu.vassar.cmpu.test.view.housemateListScreen.IHousemateListScreenFragment +.. edu.vassar.cmpu.test.view.housemateListScreen.IHousemateListScreenFragment.Listener
edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.IDebtScreenFragment +.. edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.IDebtScreenFragment.Listener
edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.IAddHousemate <|.. edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.AddHousemateFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.housemateListScreen.addHousemate.AddHousemateFragment
edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.IDebtScreenFragment <|.. edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.DebtScreenFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.housemateListScreen.debtScreen.DebtScreenFragment
edu.vassar.cmpu.test.view.transactionsScreen.ITransactionsScreenFragment +.. edu.vassar.cmpu.test.view.transactionsScreen.ITransactionsScreenFragment.Listener
edu.vassar.cmpu.test.view.transactionsScreen.ITransactionsScreenFragment <|.. edu.vassar.cmpu.test.view.transactionsScreen.TransactionsScreenFragment
androidx.fragment.app.Fragment <|-- edu.vassar.cmpu.test.view.transactionsScreen.TransactionsScreenFragment
@enduml
```

# Class Diagram for Persistence
```plantuml
@startuml
class edu.vassar.cmpu.test.persistence.FirestoreFacade {
~ FirebaseFirestore db
+ String HOUSE_NAME
+ void setHouseName(String)
+ void saveHousemate(Housemate)
+ void retrieveHousemateList(HousematesListListener)
+ void saveLineItem(LineItem)
+ void retrieveShoppingList(ShoppingListListener)
+ void saveEvent(Event)
+ void retrieveCalendar(CalendarListener)
+ void saveLineItemPL(LineItem)
+ void onCheckOut()
+ void updateHousemateDebt(List<Housemate>)
+ void retrieveDebtList(DebtListListener)
+ void retrievePurchaseList(PurchaseListListener)
+ void saveDebtList(List<Debt>,int)
+ void createUserIfNotExists(Housemate,BinaryResultListener)
+ void retrieveUser(String,DataListener<Housemate>)
+ void createHouseIfNotExists(House,BinaryResultListener)
+ void retrieveHouse(String,DataListener<House>)
}
interface edu.vassar.cmpu.test.persistence.IPersistenceFacade {
~ void setHouseName(String)
~ void saveLineItem(LineItem)
~ void retrieveShoppingList(ShoppingListListener)
~ void saveEvent(Event)
~ void retrieveCalendar(CalendarListener)
~ void retrievePurchaseList(PurchaseListListener)
~ void saveHousemate(Housemate)
~ void retrieveHousemateList(HousematesListListener)
~ void saveLineItemPL(LineItem)
~ void onCheckOut()
~ void updateHousemateDebt(List<Housemate>)
~ void retrieveDebtList(DebtListListener)
~ void saveDebtList(List<Debt>,int)
~ void createUserIfNotExists(Housemate,BinaryResultListener)
~ void retrieveUser(String,DataListener<Housemate>)
~ void createHouseIfNotExists(House,BinaryResultListener)
~ void retrieveHouse(String,DataListener<House>)
}
interface edu.vassar.cmpu.test.persistence.IPersistenceFacade.DataListener {
~ void onDataReceived(T)
~ void onNoDataFound()
}
interface edu.vassar.cmpu.test.persistence.IPersistenceFacade.BinaryResultListener {
~ void onYesResult()
~ void onNoResult()
}
interface edu.vassar.cmpu.test.persistence.IPersistenceFacade.ShoppingListListener {
~ void onShoppingListReceived(ShoppingList)
}
interface edu.vassar.cmpu.test.persistence.IPersistenceFacade.CalendarListener {
~ void onCalendarReceived(Calendar)
}
interface edu.vassar.cmpu.test.persistence.IPersistenceFacade.PurchaseListListener {
~ void onPurchaseListReceived(List<LineItem>)
}
interface edu.vassar.cmpu.test.persistence.IPersistenceFacade.HousematesListListener {
~ void onHousemateListReceived(List<Housemate>)
}
interface edu.vassar.cmpu.test.persistence.IPersistenceFacade.DebtListListener {
~ void onDebtListReceived(List<Debt>)
}


edu.vassar.cmpu.test.persistence.IPersistenceFacade <|.. edu.vassar.cmpu.test.persistence.FirestoreFacade
edu.vassar.cmpu.test.persistence.IPersistenceFacade +.. edu.vassar.cmpu.test.persistence.IPersistenceFacade.DataListener
edu.vassar.cmpu.test.persistence.IPersistenceFacade +.. edu.vassar.cmpu.test.persistence.IPersistenceFacade.BinaryResultListener
edu.vassar.cmpu.test.persistence.IPersistenceFacade +.. edu.vassar.cmpu.test.persistence.IPersistenceFacade.ShoppingListListener
edu.vassar.cmpu.test.persistence.IPersistenceFacade +.. edu.vassar.cmpu.test.persistence.IPersistenceFacade.CalendarListener
edu.vassar.cmpu.test.persistence.IPersistenceFacade +.. edu.vassar.cmpu.test.persistence.IPersistenceFacade.PurchaseListListener
edu.vassar.cmpu.test.persistence.IPersistenceFacade +.. edu.vassar.cmpu.test.persistence.IPersistenceFacade.HousematesListListener
edu.vassar.cmpu.test.persistence.IPersistenceFacade +.. edu.vassar.cmpu.test.persistence.IPersistenceFacade.DebtListListener
@enduml
```

# Class Diagram Connections
```plantuml
@startuml

class Controller.ControllerActivity{

}
class View.IFragments{

}
class HouseController{

}

Controller.ControllerActivity --> HouseController  

@enduml
```
