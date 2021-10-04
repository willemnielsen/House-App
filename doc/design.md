# Add Item Domain Model
```plantuml
@startuml
testdot
hide circle
hide empty methods
' classes
class House{
    name
}
class ShoppingList{ 
}
class Item{
    name
    avalibility
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
class Debt{
    address
    name
}
class Housemate{   
    name
    paymentInfo
    housemateId
}

' associations
House "1" -- "1..*" Housemate : \tContains\t\t
House "1" -- "1" ShoppingList : \tContains\t\t
Housemate "0" - "1..*" Item : \tOwn\t\t
ShoppingList "0" - "0..*" Item : \tContains\t\t
Housemate "0" -- "*" Debt : \tOwes\t\t
Item "*" -- "1...*" Store : \tFrom\t\t
Item "*" -- "1...*" ItemInfo : \tDescribed by \t\t
ItemInfo "*" -left- "1..*"  Store : \tGets info from\t\t
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

class Debt {
    list of (members, amount)
    }

Member <|-- Debt
Item <|-- ItemInfo 

@enduml
```
