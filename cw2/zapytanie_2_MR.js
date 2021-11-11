db.people.mapReduce(
    function () {
        this.credit.forEach(card => {
            emit(card.currency, card.balance);
        });
    },
    function (key, values) {
        return Array.sum(values);
    },
    { out: 'reduce_output' }
);


printjson(
    db.reduce_output.find().toArray()
)
