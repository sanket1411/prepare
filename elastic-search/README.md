# ELASTIC KIBANA LOGSTASH

## Elastic-Search Installation

Start with docker-compose:

<code>

docker-compose up
</code>

Alternative:

<code>

docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:tag
</code>

Kibana-elastic combined non-official image:

<code>

docker run -d -p 9200:9200 -p 5601:5601 nshou/elasticsearch-kibana
</code>

## TIPS
Regarding create index

`Create Index in application startup code with mapping,can be usefull`

`While putting mapping use appropriate date format`

`Nested fields can also be put through mapping`

Updating Index

`While updating index when using nested mapping use script and pipeline`

`To evaluate pipeline use POST /_ingest/pipeline/_simulate`

`Always handle response of elastic in java code don't jsut call update api`

Other

`Pipeline are not mapped to a specific index,if same pipeline name is used old pipeline code will be overridden`

`For Nested fields visualization use Vega visualization option in kibana`
