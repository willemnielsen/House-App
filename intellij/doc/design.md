# Add Domain.Item Domain Model
```plantuml
@startuml
hide circle
hide empty methods

class Domain.House{
    houseName
    houseID
}
class Domain.ShoppingList{ 
}
class Domain.LineItem{
    quantity
}

class Domain.Item{
    price
    name
}

class Domain.Debt{
    owed
}


class Domain.Housemate{   
    name
    housemateId
}
class Domain.Calendar{

}

class Domain.Event{
    name
    date
    startTime
    endTime
}

class Domain.Recurrence{
    frequency
    startDate
    endDate
}

' associations
Domain.House "1" -- "1..*" Domain.Housemate : \tContains\t\t
Domain.House "1" -- "1..*" Domain.ShoppingList : \tContains\t\t
Domain.House "1" -- "1" Domain.LineItem : \tPurchased\t\t
Domain.House "1" -- "1" Domain.Calendar : Contains
Domain.Housemate "1..*" - "0..*" Domain.LineItem : \tOwns\t\t
Domain.ShoppingList "1" -- "0..*" Domain.LineItem : \tContains\t\t
Domain.LineItem "*" - "1...*" Domain.Item : \tDescribed by \t\t
Domain.Housemate "1..*" -down- "0..*" Domain.Debt : \tOwed by\t\t
Domain.Debt "1*" -right- "1" Domain.LineItem : for
Domain.Housemate "1" - "0..*" Domain.LineItem : Purchases
Domain.Calendar "1" - "0..*" Domain.Event : Contains

@enduml




```
# Get Domain.Item Sequence Diagram
```plantuml

@startuml
actor Actor as Actor
participant "Domain.ShoppingList: List" as shoppingList
participant ": Domain.LineItem" as lineItem
participant ": Domain.Item" as item


[o-> shoppingList : add Domain.Item
activate shoppingList

loop while not done
    shoppingList -->> Actor  : get price, name, quantity, and interestedHouseMates
    shoppingList -->> lineItem **: Domain.LineItem(price, name, quantity, interestedHouseMates)
    lineItem -->> item **: Item(price, name)

end

activate shoppingList


@enduml
```

# Charge Domain.Housemate Sequence Diagram
```plantuml

@startuml
actor Domain.Housemate as Actor
participant ": Domain.House" as house
participant ": Domain.Housemate" as housemate


[o-> house : charge housemates
    activate house
    house -->> Actor  : get purchased items
    house -->> Actor  : get charge distribution option
    house -->> housemate : createDebt(purchasedItems, distribution)
@enduml
```

# Add Event to Calendar
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

# Class Diagram for Model
```plantuml
@startuml

class Domain.House {
    - houseName : String {unique}
    - houseID : int = 0
    + addHousemate(housemate : Housemate) : String
    + removeHousemate(housemate : Housemate) : String
    + nameHouse(name : String) : String
    - purchaseItem(lineItem : LineItem) : void
    + createDebtForIHM(lineItem : LineItem) : void
    + createDebtForHH(lineItem : LineItem) : void
    + createDebtForMe(lineItem : LineItem) : void
    + checkout(distribution : String, purchaser : Housemate) : void
    + houseTransactions() : String
    + addLineItemToShoppingList(quantity : int, name : String, price : float,  interestedHouseMates : List<Housemate>) : boolean
    + getShoppingListLineItem(i : int) : LineItem
    + getShoppingListSize() : int
    + getShoppingList() : ShoppingList
    + getPurchasedItems() : List<LineItem>
    + getHousemates() : List<Housemate>
    }
 Domain.House -> "(1) \nshoppingList\n{List}" Domain.ShoppingList : \t\t\t\t
 Domain.House --> "(1) \nhousemates\n{List}" Domain.Housemate : \t\t\t\t
 Domain.House --> "(1..*) \npurchasedItems\n{List}" Domain.LineItem : \t\t\t\t
 Domain.House -> "(1..*) \nhousedebt\n{List}" Domain.Debt : \t\t\t\t

class Domain.ShoppingList{
    + addItem(quantity : int, name : String, price : float, interestedHouseMates : List<Housemate>) : boolean
    + getShoppingListLineItem(i : int) : LineItem
    + size() : int
    + clear() : void
    + toString() : String
}
Domain.House --> "(1..*) \nshoppingList\n{List}" Domain.LineItem : \t\t\t\t
Domain.House --> "(1)" Domain.Calendar 
class Domain.Calendar {
    - currentDate : Date
    + addEvent(name, date, startTime, endTime, interestedHouseMates, recurrence : Recurrence) : boolean
    + remove() : void
    + getThisEvent() : Event
    + setCurrentDate() : boolean
    + getCurrentDate() : Date
    + toString() : String
}

class Domain.Event {
    - name : String
    - date : Date
    - startTime : time
    - endTime : time
    + getName() : String
    + getDate() : Date
    + getStartTime() : Time
    + getEndTime() : Time
    + toString() : String
    }
Domain.Calendar --> "(0..*) \nevents\n{List}" Domain.Event : \t\t\t\t
Domain.Event --> "(0..*) \nhousemates\n{List}" Domain.Housemate : \t\t\t\t
class Domain.Recurrence {
    - frequency : String
    - startDate : Date
    - endDate : Date
    + getFrequency() : String
    + getStartDate() : Date
    + getEndDate() : Date
}

class Domain.LineItem {
    - quantity  : int 
    + getQuantity() : int
    + addPurchaser(purchaser : Housemate) : void
    + setQuantity(quantity : int) : void
    + getInterestedHouseMates() : List<Housemate>
    + getPurchaser() : Housemate
    + setPurchaser() : void
    + getPrice() : float
    + setPrice() : void
    + getName() : String
    + toString() : String
    }   
Domain.LineItem --> "(1) \ninterestedHouseMates\n{List}" Domain.Housemate : \t\t\t\t
Domain.LineItem --> "(1) \nPurchaser\n{List}" Domain.Housemate : \t\t\t\t
Domain.LineItem *-- Domain.Item

class Domain.Item {
    - name : String
    - price : float
    + setPrice(price : float) : void
    + getPrice() : float
    + getName() : String
    + toString() : String
    }

class Domain.Housemate {
    - name : String
    - housemateId : String
    + myTransactions() : String
    + myBalance() : String
    + getName() : String
    + toString() : String
    }
Domain.Housemate --> "(1..*) \ndebtlist\n{List}" Domain.Debt : \t\t\t\t

class Domain.Debt{
    - isPaid : boolean = false
    - owed : float
    + getDebtor() : Housemate
    + getCreditor() : Housemate
    + getItem() : LineItem
    + getOwed() : float
}
Domain.Debt -> "(1) \ndebtor\n" Domain.Housemate : \t\t\t\t
Domain.Debt -> "(1) \ncreditor\n" Domain.Housemate : \t\t\t\t
Domain.Debt -> "(1..*) \nlineItem\n" Domain.LineItem : \t\t\t\t

class Domain.HouseController{
    + addHousemate(housemate : Housemate) : void
    + removeHousemate(housemate : Housemate) : void
    + checkout(distribution : String, purchaser : Housemate) : void
    + getShoppingListLineItem(i : int) : LineItem
    + addLineItemToShoppingList(quantity : int, name : String, price : float, interestedHouseMates : List) : boolean
    + getShoppingListSize() : int
    + shoppingListToString() : String
    + addToPurchase(lineitem : LineItem) : void
    + getHouse() : House
    + getHousemate(name : String) : Housemate
    + houseTransactions() : String
    + {static} convertPurchaseToString(lIL : List<LineItem>) : String
    + {static} convertHouseMatesToString(h : List<Housemate>) : String
    + getHousemates() : List<Housemate>
}
Domain.HouseController --> "(1) \nhouse\n{House}" Domain.House :\t\t\t\t

@enduml
```

# Class Diagram for Controller
```plantuml
@startuml
hide circle
hide empty methods

class Controller.ControllerActivity {
# void onCreate(Bundle)
+ void onCreateHouse(String,String)
+ void openHomeScreen()
+ void onOpenShoppingList()
+ void onOpenCalendar()
+ void onOpenHousemateList()
+ void onOpenPurchasedList()
+ void openShoppingListScreen()
+ void onAddItem()
+ void onPreviousOnShoppingListScreen()
+ void onPurchaseItems(LineItem,IShoppingListScreenView)
+ void openAddItemScreen()
+ void onAddedItem(String,int,float,ArrayList<Housemate>,IAddItemView)
+ void onPreviousInAddItemFragment()
+ void openCalendarScreen()
+ void onAddEvent()
+ void onPreviousOnCalendarScreen()
+ void openAddEventScreen()
+ void onAddedEvent(String,Date,Time,Time,ArrayList<Housemate>,String,IAddEventView)
+ void onSetDate(Date,ICalendarScreenView)
+ void onPreviousInAddEventFragment()
+ void openHousemateListScreen()
+ void onPreviousOnHousemateListScreen()
+ void onAddHousemateOnHousemateListScreen()
+ void onAddHousemate(String)
+ void onPreviousOnAddHousemateScreen()
+ void openPurchasedListScreen()
+ void onPreviousOnPurchasedListScreen()
}
Controller.ControllerActivity --> "(1)" Domain.HouseController :\t\t\t\t

@enduml
```

# Class Diagram for View
```plantuml
@startuml

class View.housemateListScreen.addHousemate.AddHousemateFragment {
~ FragmentAddHousemateBinding binding
~ Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
}

interface View.addEventView.IAddEventView {
~ void getAddedHouseMates(ArrayList<Housemate>)
~ void updateDisplay(Calendar)
}
interface View.addEventView.IAddEventView.Listener {
~ void onAddedEvent(String,Date,Time,Time,ArrayList<Housemate>,String,IAddEventView)
~ void onPreviousInAddEventFragment()
}

class View.housemateListScreen.HousemateListScreenFragment {
- Listener listener
- FragmentHousematesBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void updateDisplay(ArrayList<Housemate>)
}

interface View.housemateListScreen.addHousemate.IAddHousemate {
}
interface View.housemateListScreen.addHousemate.IAddHousemate.Listener {
~ void onAddHousemate(String)
~ void onPreviousOnAddHousemateScreen()
}
interface View.housemateListScreen.IHousemateListScreenFragment {
~ void updateDisplay(ArrayList<Housemate>)
}
interface View.housemateListScreen.IHousemateListScreenFragment.Listener {
~ void onPreviousOnHousemateListScreen()
~ void onAddHousemateOnHousemateListScreen()
~ void onDebtScreenButton()
}
interface View.calendarScreen.ICalendarScreenView {
~ void updateDisplay(Calendar)
}
interface View.calendarScreen.ICalendarScreenView.Listener {
~ void onAddEvent()
~ void onSetDate(Date,ICalendarScreenView)
~ void onPreviousOnCalendarScreen()
}
class View.calendarScreen.CalendarScreenFragment {
- Listener listener
- FragmentCalendarMonthBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void updateDisplay(Calendar)
}
class View.temp {
}


class View.addEventView.AddEventFragment {
- FragmentAddEventBinding binding
- Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void getAddedHouseMates(ArrayList<Housemate>)
- ArrayList<Housemate> CreateDialog(ArrayList<Housemate>,String,Date,Time,Time,String)
+ void onAddedEvent(String,Date,Time,Time,ArrayList<Housemate>,String)
+ void updateDisplay(Calendar)
+ void onItemSelected(AdapterView<?>,View,int,long)
+ void onNothingSelected(AdapterView<?>)
}



View.housemateListScreen.IHousemateListScreenFragment <|.. View.housemateListScreen.HousemateListScreenFragment
View.housemateListScreen.addHousemate.IAddHousemate <|.. View.housemateListScreen.addHousemate.AddHousemateFragment
View.addEventView.IAddEventView +.. View.addEventView.IAddEventView.Listener
View.addEventView.IAddEventView <|.. View.addEventView.AddEventFragment
View.housemateListScreen.addHousemate.IAddHousemate +.. View.housemateListScreen.addHousemate.IAddHousemate.Listener
View.housemateListScreen.IHousemateListScreenFragment +.. View.housemateListScreen.IHousemateListScreenFragment.Listener
View.calendarScreen.ICalendarScreenView +.. View.calendarScreen.ICalendarScreenView.Listener
View.calendarScreen.ICalendarScreenView <|.. View.calendarScreen.CalendarScreenFragment

@enduml
```

# Class Diagram for View Part 2
```plantuml
@startuml

class View.purchasedListScreen.PurchasedListScreenFragment {
- FragmentPurchasedBinding binding
- Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void updatePurchasedList(ArrayList<LineItem>)
+ void onViewCreated(View,Bundle)
+ void CreateDialog()
}
interface View.purchasedListScreen.IPurchasedListScreenFragment {
~ void updatePurchasedList(ArrayList<LineItem>)
}
interface View.purchasedListScreen.IPurchasedListScreenFragment.Listener {
~ void onPreviousOnPurchasedListScreen()
~ void onPurchaseByUser(String,IPurchasedListScreenFragment)
}

class View.shoppingListScreen.ShoppingListScreenFragment {
- Listener listener
- FragmentShoppingListScreenBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
- ArrayList<LineItem> CreateDialog(ShoppingList)
+ void updateDisplay(ShoppingList)
+ void onPurchaseItems(LineItem)
+ void updatePurchasedList(ArrayList<LineItem>)
+ void purchaseItems(ShoppingList)
}
interface View.shoppingListScreen.IShoppingListScreenView {
~ void updateDisplay(ShoppingList)
~ void purchaseItems(ShoppingList)
~ void updatePurchasedList(ArrayList<LineItem>)
}
interface View.shoppingListScreen.IShoppingListScreenView.Listener {
~ void onAddItem()
~ void onPurchaseItems(LineItem,IShoppingListScreenView)
~ void onPreviousOnShoppingListScreen()
}

class View.transactionsScreen.TransactionsScreenFragment {
~ FragmentTransactionsScreenBinding binding
~ Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void updateDisplay(String)
}
interface View.transactionsScreen.ITransactionsScreenFragment {
~ void updateDisplay(String)
}
interface View.transactionsScreen.ITransactionsScreenFragment.Listener {
~ void onPreviousOnTransactionsScreen()
}

class View.addItemView.AddItemFragment {
- FragmentAddItemBinding binding
- Listener listener
- HouseController house
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
- void CreateDialog(ArrayList<Housemate>,String,int,float)
+ void updateDisplay(ShoppingList)
+ void getHouseMates(ArrayList<Housemate>)
}

interface View.addItemView.IAddItemView {
~ void getHouseMates(ArrayList<Housemate>)
~ void updateDisplay(ShoppingList)
}
interface View.addItemView.IAddItemView.Listener {
~ void onAddedItem(String,int,float,ArrayList<Housemate>,IAddItemView)
~ void onPreviousInAddItemFragment()
}



View.purchasedListScreen.IPurchasedListScreenFragment <|.. View.purchasedListScreen.PurchasedListScreenFragment
View.purchasedListScreen.IPurchasedListScreenFragment +.. View.purchasedListScreen.IPurchasedListScreenFragment.Listener
View.shoppingListScreen.IShoppingListScreenView +.. View.shoppingListScreen.IShoppingListScreenView.Listener
View.shoppingListScreen.IShoppingListScreenView <|.. View.shoppingListScreen.ShoppingListScreenFragment
View.transactionsScreen.ITransactionsScreenFragment +.. View.transactionsScreen.ITransactionsScreenFragment.Listener
View.transactionsScreen.ITransactionsScreenFragment <|.. View.transactionsScreen.TransactionsScreenFragment
View.addItemView.IAddItemView +.. View.addItemView.IAddItemView.Listener
View.addItemView.IAddItemView <|.. View.addItemView.AddItemFragment
@enduml
```

# Class Diagram for View Part 3
```plantuml
@startuml

class View.loginScreen.LoginScreenFragment {
- Listener listener
- FragmentLoginScreenBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
}

interface View.loginScreen.ILoginScreenFragment {
}
interface View.loginScreen.ILoginScreenFragment.Listener {
~ void onCreateHouse(String,String)
}

class View.homeScreen.HomeScreenFragment {
- Listener listener
- FragmentHomeScreenBinding binding
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
}

interface View.homeScreen.IHomeScreenFragment {
}
interface View.homeScreen.IHomeScreenFragment.Listener {
+ void onOpenShoppingList()
+ void onOpenCalendar()
+ void onOpenHousemateList()
+ void onOpenPurchasedList()
+ void onOpenTransactions()
}

class View.housemateListScreen.debtScreen.DebtScreenFragment {
~ FragmentDebtScreenBinding binding
~ Listener listener
+ void onCreate(Bundle)
+ View onCreateView(LayoutInflater,ViewGroup,Bundle)
+ void onViewCreated(View,Bundle)
+ void updateDisplay(String)
}

interface View.housemateListScreen.debtScreen.IDebtScreenFragment {
+ void updateDisplay(String)
}
interface View.housemateListScreen.debtScreen.IDebtScreenFragment.Listener {
~ void onPreviousOnDebtScreen()
}

View.housemateListScreen.debtScreen.IDebtScreenFragment +.. View.housemateListScreen.debtScreen.IDebtScreenFragment.Listener
View.housemateListScreen.debtScreen.IDebtScreenFragment <|.. View.housemateListScreen.debtScreen.DebtScreenFragment
View.loginScreen.ILoginScreenFragment <|.. View.loginScreen.LoginScreenFragment
View.loginScreen.ILoginScreenFragment +.. View.loginScreen.ILoginScreenFragment.Listener
View.homeScreen.IHomeScreenFragment +.. View.homeScreen.IHomeScreenFragment.Listener
View.homeScreen.IHomeScreenFragment <|.. View.homeScreen.HomeScreenFragment
@enduml
```


