# Add Domain.Item Domain Model
```plantuml
@startuml
hide circle
hide empty methods

class UI.TerminalController{

}

class Domain.HouseController{
    house
}

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
    image
    quantity
}

class Domain.Debt{
    isPaid
    owed
}


class Domain.Housemate{   
    name
    housemateId
}

' associations
Domain.HouseController "1" - "1..*" UI.TerminalController : \tContains\t\t
Domain.House "1" - "1" Domain.HouseController : \tContains\t\t
Domain.House "1" -- "1..*" Domain.Housemate : \tContains\t\t
Domain.House "1" -- "1..*" Domain.ShoppingList : \tContains\t\t
Domain.House "1" -- "1" Domain.LineItem : \tPurchased\t\t
Domain.Housemate "1..*" - "0..*" Domain.LineItem : \tOwns\t\t
Domain.ShoppingList "1" -- "0..*" Domain.LineItem : \tContains\t\t
Domain.LineItem "*" - "1...*" Domain.Item : \tDescribed by \t\t
Domain.Housemate "1..*" -left- "0..*" Domain.Debt : \tOwes\t\t
Domain.Housemate "1" - "0..*" Domain.LineItem : Purchases
@enduml




```
# Get Domain.Item Sequence Diagram
```plantuml

@startuml
actor Actor as Actor
participant "Domain.ShoppingList: List" as shoppingList
participant ": Domain.Item" as item
participant ": ItemInfo" as itemInfo


[o-> shoppingList : add Domain.Item
activate shoppingList

loop while not done
    shoppingList -->> Actor  : get name and quality
    shoppingList -->> item **: Domain.Item(name, quality)
    item -->> itemInfo **: ItemInfo(name, quality)
    item -->> Actor : get Domain.Housemate/s info
    item -->> Actor : get store/s info
    item -->> store : assoicate Store (null --> default)

end

activate shoppingList


@enduml
```

# Charge Domain.Housemate Sequence Diagram
```plantuml

@startuml
actor Domain.Housemate as Actor
participant "Shopping List: List" as shoppingList
participant ": LineItem" as LineItem
participant ": House" as house


[o-> shoppingList : charge housemates
activate shoppingList

    shoppingList -->> Actor  : get items to be charged
    shoppingList -->> Actor  : get charge distribution option
    shoppingList -->> Actor  : get confirmation
    shoppingList -->> LineItem** : addPurchaser(purchaser, LineItem)
    shoppingList -->> house : addCharge(LineItem)
    house -->> shoppingList : removePurchasedItems(LineItem)

activate shoppingList


@enduml
```

# Class Diagram
```plantuml
@startuml

class Domain.House {
    - houseName : String {unique}
    - houseID : int = 0
    + addHousemate(Housemate housemate) : String
    + removeHousemate(Housemate housemate) : String
    + nameHouse(String name). : String
    - purchaseItem(LineItem lineItem) : void
    + createDebtForIHM(LineItem lineItem) : void
    + createDebtForHH(LineItem lineItem) : void
    + createDebtForMe(LineItem lineItem) : void
    + checkout(String distribution, Housemate purchaser) : void
    + houseTransactions() : String
    + addLineItemToShoppingList(int quantity, String name, float price, ArrayList<Housemate> interestedHouseMates) : boolean
    + getShoppingListLineItem(int i) : LineItem
    + getShoppingListSize() : int
    + getShoppingList() : ShoppingList
    + getPurchasedItems() : ArrayList<LineItem>
    + getHousemates() : ArrayList<Housemate>
    }
 Domain.House -> "(1) \nshoppingList\n{ArrayList}" Domain.ShoppingList : \t\t\t\t
 Domain.House --> "(1) \nhousemates\n{ArrayList}" Domain.Housemate : \t\t\t\t
 Domain.House --> "(1..*) \npurchasedItems\n{ArrayList}" Domain.LineItem : \t\t\t\t
 Domain.House -> "(1..*) \nhousedebt\n{ArrayList}" Domain.Debt : \t\t\t\t

class Domain.ShoppingList{
}
Domain.House --> "(1..*) \nshoppingList\n{ArrayList}" Domain.LineItem : \t\t\t\t

class Domain.LineItem {
    - quantity  : int 
    }   
Domain.LineItem --> "(1) \ninterestedHouseMates\n{ArrayList}" Domain.Housemate : \t\t\t\t
Domain.LineItem *-- Domain.Item

class Domain.Item {
    - name
    - quality
    - price
    }

class Domain.Housemate {
    - name : String
    - housemateId : String
    }
Domain.Housemate --> "(1..*) \ndebtlist\n{ArrayList}" Domain.Debt : \t\t\t\t

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
    + addHousemate(Housemate housemate) : void
    + removeHousemate(Housemate housemate) : void
    + checkout(String distribution, Housemate purchaser) : void
    + getShoppingListLineItem(int i) : LineItem
    + addLineItemToShoppingList(int quantity, String name, float price, ArrayList<Housemate> interestedHouseMates) : boolean
    + getShoppingListSize() : int
    + shoppingListToString() : String
    + addToPurchase(LineItem lineitem) : void
    + getHouse() : House
    + getHousemate(String name) : Housemate
    + houseTransactions() : String
    + {static} convertPurchaseToString(ArrayList<LineItem> lIL) : String
    + {static} convertHouseMatesToString(ArrayList<Housemate> h) : String
    + getHousemates() : ArrayList<Housemate>
}
Domain.HouseController --> "(1) \nhouse\n{House}" Domain.House :\t\t\t\t
class UI.TerminalController{

}

@enduml
```
