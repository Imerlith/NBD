db.people.mapReduce(
    function () {
        this.credit.forEach( card => {
            emit(card.currency, card.balance)
        })
    },
    function (key, values) {
        const totalBalnce = Array.sum(values);
        const avgBalance = totalBalnce / values.length;

        return {
            totalBalnce,
            avgBalance
        }
    },
    {
        query: {
            nationality: "Poland",
            sex: "Female"
        },
        out: 'reduce_output'
    }
);


printjson(
    db.reduce_output.find().toArray()
)
