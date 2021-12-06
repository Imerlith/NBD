# Running cypher scripts in docker
```
 cat task3.cypher | docker exec -i nbdneo4j cypher-shell -u neo4j -p s3cr3t
```
 where `task3.cypher` is a cypher scirpt and `nbdneo4j` is a running docker container
