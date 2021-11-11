printjson(
    db.people.aggregate(
        [
            {
                $addFields: {
                    bmi: {
                        $divide: [
                            "$weight",
                            {
                                $multiply: [
                                    {$divide: ["$height", 100]},
                                    {$divide: ["$height", 100]}
                                ]
                            }
                        ]
                    }
                }
            },
            {
                $group: {
                    _id: "$nationality",
                    avgBMI: {$avg: "$bmi"},
                    maxBMI: {$max: "$bmi"},
                    minBMI: {$min: "$bmi"},
                }
            }
        ]
    ).toArray()
)

