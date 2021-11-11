db.people.mapReduce(
    function () {
        emit(this.job, null)
    },
    function (key, values) {
        return 1;
    },
    { out: 'reduce_output' }
);


printjson(
    db.reduce_output.find().toArray()
)
