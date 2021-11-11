printjson(
    db.people.aggregate(
        [
            {
                $match: {
                    nationality: "Poland",
                    sex: "Female"
                }
            },
            {
                $unwind: {
                    path: "$credit"
                }
            },
            {
                $group: {
                    _id: "$credit.currency",
                    totalBalance: {
                        $sum: "$credit.balance"
                    },
                    avgBalance: {
                        $avg: "$credit.balance"
                    }
                }
            }
        ]
    ).toArray()
)

