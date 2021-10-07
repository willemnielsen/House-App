# Add Item Domain Model
```plantuml
@startuml

class House{
    name
}
class ShoppingList{ 
    calcuateCharge
    
}
class LineItem{
    quantity
}

class ItemInfo{
    name
    price
}

class Debt{
    isPaid
}
class Housemate{   
    name
    paymentInfo
    housemateId
}

' associations
House "1" -- "1..*" Housemate : \tContains\t\t
House "1" -right- "1..*" ShoppingList : \tContains\t\t
Housemate "*" - "1..*" LineItem : \tOwns\t\t
ShoppingList "1" - "0..*" LineItem : \tContains\t\t
LineItem "*" -- "1...*" ItemInfo : \tDescribed by\t\t
ItemInfo "*" -left- "1..*"  Store : \tGets info from\t\t
Debt "*" -- "*" Housemate : \tAmount-Owed-Between\t\t


@enduml




```
# Get Item Sequence Diagram
```plantuml

@startuml
actor Actor as Actor
participant "ShoppingList: List" as shoppingList
participant ": Item" as item
participant ": ItemInfo" as itemInfo
participant ": Store" as store


[o-> shoppingList : add Item
activate shoppingList

loop while not done
    shoppingList -->> Actor  : get name and quality
    shoppingList -->> item **: Item(name, quality)
    item -->> itemInfo **: ItemInfo(name, quality)
    item -->> Actor : get Housemate/s info
    item -->> Actor : get store/s info
    item -->> store : assoicate Store (null --> default)

end

activate shoppingList


@enduml
```

# Charge Housemate Sequence Diagram
```plantuml

@startuml
actor Housemate as Actor
participant "Shopping List: List" as shoppingList
participant ": Charge" as charge
participant ": Record" as record


[o-> shoppingList : charge housemates
activate shoppingList

    shoppingList -->> Actor  : get items to be charged
    shoppingList -->> Actor  : get charge distribution option
    shoppingList -->> Actor  : get confirmation
    shoppingList -->> charge** : create(Items, distribution)
    charge -->> record : calculate(charge)
    charge -->> shoppingList : remove(Items)

activate shoppingList


@enduml
```

# Class Diagram
```plantuml
@startuml

class House {
    name
    }

class ShoppingList
class Item {
    name
    quantity 
    }
class ItemInfo {
    name
    quality
    price
    }
class Store
class Member {
    name
    id
    }

class Charge {
    distribution
    }
class Record { 
    houseid
}

class Debt{
    amountOwed
}
Item <|-- ItemInfo 

Record <|-- Debt
@enduml
```
