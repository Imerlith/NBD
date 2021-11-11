printjson(
    db.people.mapReduce(
        function() {
            emit(
                this.sex,
                {
                    weight: this.weight,
                    height: this.height
                }
            );
        },
        function(key, val) {
            let totalWeight = 0;
            let totalHeight = 0;

            val.forEach(element => {
                totalWeight += element.weight;
                totalHeight += element.height;
            });

            return {
                avgWeight: totalWeight / val.length,
                avgHeight: totalHeight / val.length,
            };
        },
        {
            out:'reduce_output'
        }
    )
)


printjson(
    db.reduce_output.find().toArray()
)
