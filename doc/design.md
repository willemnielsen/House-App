# Add Domain.Item Domain Model
```plantuml
@startuml
hide circle

class UI.TerminalController{

}

class Domain.HouseController{
    House house
}

class Domain.House{
    name
}
class Domain.ShoppingList{ 
}
class Domain.LineItem{
    quantity
}
class Store{
    address
    name
}
class ItemInfo{
    price
    image
    quantity
}



class Domain.Housemate{   
    name
    paymentInfo
    housemateId
}

' associations
Domain.HouseController "1" -- "1..*" UI.TerminalController : \tContains\t\t
Domain.House "1" -- "1" Domain.HouseController : \tContains\t\t
Domain.House "1" -- "1..*" Domain.Housemate : \tContains\t\t
Domain.House "1" -right- "1..*" Domain.ShoppingList : \tContains\t\t
Domain.Housemate "1..*" - "0..*" Domain.LineItem : \tOwns\t\t
Domain.ShoppingList "1" - "0..*" Domain.LineItem : \tContains\t\t
Domain.LineItem "*" -- "1...*" Store : \tFrom\t\t
Domain.LineItem "*" -- "1...*" ItemInfo : \tDescribed by \t\t
ItemInfo "*" -left- "1..*"  Store : \tGets info from\t\t
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
participant ": Store" as store


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
    name
    }

class Domain.ShoppingList
class Domain.LineItem {
    name
    quantity 
    }
class ItemInfo {
    name
    quality
    price
    }
class Store
class Housemate {
    name
    id
    }


Domain.LineItem -- ItemInfo 
Domain.House -- Housemate


@enduml
```
