# LendingLibrary


Used Spring Boot with H2 db. Data is stored as a file

TO BUILD: mvn clean install
TO run: mvn spring-boot:run

Use cases:

- Register new user
- Track customer using customer id
- Display list of items customer has rented so far.
- Display list of overdue items for a customer and total amount due to be paid by
the customer
- Check no. of items available in the library for a given item using item id. EX.
Item ID 100 is Harry Potter and Chamber of Secrets book. Total count: 5.
Available: 2
- Option for taking a new item from the library by the customer. Check
availability and grant/deny
- Option for returning an item back to the library.




- #API DOCUUMENTATION: https://documenter.getpostman.com/view/8531496/Uz59MePa

Please use the postman documentstion for API's





- Sample responses:

Get all users:
```

[
    {
        "id": "43da5cc2-f9be-4116-85bb-07f13a2f6933",
        "name": "customer3",
        "phone": "9629722797",
        "customerId": "3feb208e49455d01",
        "balance": 36
    }
]

```

Create Item:

```

{
    "id": "67353c2e-1fc2-450a-a12a-2efe0fe6d769",
    "itemName": "Python",
    "itemType": "BOOK",
    "description": "Book on python",
    "costToRent": 12,
    "count_available": 15,
    "total_count": 15
}

```


rent:

```

{
    "id": "17a95c0b-01b3-4281-a04b-c1c659293666",
    "userId": "3feb208e49455d01",
    "item": "833d3ebd-dc5f-4ec6-aca5-be78cfa994c6",
    "rented_on": "2022-05-24T17:05:13.140Z",
    "returned_on": null,
    "isReturned": false,
    "isPaid": false
}

```

find all overdue:

```

[
    {
        "id": "1d04d364-b06a-4ead-9012-202c52bf6587",
        "userId": "3feb208e49455d01",
        "item": "833d3ebd-dc5f-4ec6-aca5-be78cfa994c6",
        "rented_on": "2022-04-24T15:10:34.715Z",
        "returned_on": null,
        "isReturned": true,
        "isPaid": false
    }
  ]
  
```



Models: https://github.com/navinprasath18/LendingLibrary/tree/main/src/main/java/com/lendinglib/app/entities

- User
- Item
- ItemType
- Transaction
- Payment
- PayementType
- TransactionType

-------

- User class has user details and balance
- Item class has Item details, count , rent of the item ,type
- Every rental is considered a transction
- Payments are made for a transaction.

---------


service impl: https://github.com/navinprasath18/LendingLibrary/tree/main/src/main/java/com/lendinglib/app/service/impl




