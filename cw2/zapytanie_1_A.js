printjson(
    db.people.aggregate([
            {
                $group: {
                    _id: "$sex",
                    totalWeight: {$sum: "$weight"},
                    totalHeight: {$sum: "$height"},
                    total: {$sum: 1},
                }
            },
            {
                $project: {
                    totalWeight: 1,
                    totalHeight: 1,
                    total: 1,
                    avgWeight: {
                        $divide: ['$totalWeight', '$total']
                    },
                    avgHeight: {
                        $divide: ['$totalHeight', '$total']
                    }
                }
            }
        ]).toArray()
)
