db.people.mapReduce(
    function () {
        emit(
            this.nationality,
            {
                weight: this.weight,
                height: this.height
            }
        )
    },
    function (key, values) {
        let sumOfBMI = 0;
        let maxBMI = 0;
        let minBMI = Infinity;

        values.forEach(person => {
            const normalizedHeight = person.height / 100;
            const BMI = person.weight / (normalizedHeight * normalizedHeight);
            sumOfBMI += BMI;
            maxBMI = (BMI > maxBMI) ? BMI : maxBMI;
            minBMI = (BMI < minBMI) ? BMI : minBMI;
        });

        return {
            avgBMI: sumOfBMI / values.length,
            maxBMI: maxBMI,
            minBMI: minBMI
        }
    },
    { out: 'reduce_output' }
);


printjson(
    db.reduce_output.find().toArray()
)
