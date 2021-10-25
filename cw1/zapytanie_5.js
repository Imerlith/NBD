printjson(db.people.find(
    {
       "birth_date": {
           $gte: "2001-01-01"
       }
    }, {
        "first_name": true,
        "last_name": true,
        "location.city": true,
    }
).toArray())