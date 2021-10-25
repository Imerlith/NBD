printjson(db.people.insert(
    {
        "sex" : "Male",
		"first_name" : "Przemyslaw",
		"last_name" : "Golebski",
		"job" : "Software developer",
		"email" : "s16540@pjwstk.edu.pl",
		"location" : {
			"city" : "Warsawa",
			"address" : {
				"streetname" : "La Follette",
				"streetnumber" : "03661"
			}
		},
		"description" : "costam costam",
		"height" : "162.68",
		"weight" : "68.37",
		"birth_date" : "2013-05-23T16:10:58Z",
		"nationality" : "Poland",
		"credit" : [
			{
				"type" : "jcb",
				"number" : "4017957170327",
				"currency" : "PLN",
				"balance" : "4463.86"
			}
		]
    }
))