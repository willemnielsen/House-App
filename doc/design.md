# Add Domain.Item Domain Model
```plantuml
@startuml
hide circle
class Domain.House{
    name
}
class Domain.ShoppingList{ 
}
class Domain.Item{
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
class Record{
    houseID
}
class Charge{
    debt
}
class Domain.Housemate{   
    name
    paymentInfo
    housemateId
}
class Transaction{
    distribution
}

' associations
Domain.House "1" -- "1..*" Domain.Housemate : \tContains\t\t
Domain.House "1" -right- "1..*" Domain.ShoppingList : \tContains\t\t
Domain.House "1" -- "1" Record : Contains
Domain.Housemate "0" - "1..*" Domain.Item : \tOwn\t\t
Domain.ShoppingList "1" - "0..*" Domain.Item : \tContains\t\t
Domain.Item "*" -- "1...*" Store : \tFrom\t\t
Domain.Item "*" -- "1...*" ItemInfo : \tDescribed by \t\t
ItemInfo "*" -left- "1..*"  Store : \tGets info from\t\t
Record "1" -- "*" Charge : Contains
Charge "1" --  "1" Domain.Item : Contains
Charge "*" -- "2" Domain.Housemate : Amount-Owed-Between
Transaction "1" -- Domain.Item : Contains
Transaction "1" -- Domain.Housemate : Contains
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
participant ": Transaction" as transaction
participant ": Record" as record


[o-> shoppingList : charge housemates
activate shoppingList

    shoppingList -->> Actor  : get items to be charged
    shoppingList -->> Actor  : get charge distribution option
    shoppingList -->> Actor  : get confirmation
    shoppingList -->> transaction** : create(Items, distribution)
    transaction -->> record : addCharge(charge)
    transaction -->> shoppingList : remove(Items)

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
class Domain.Item {
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

class Transaction {
    distribution
    }
class Record { 
    houseid
}

class Charge{
    debt
}
Domain.Item <|-- ItemInfo 

Record <|-- Charge
@enduml
```
