# OPEN TELEMETRY

## Elastic-Search Installation

Start with docker-compose:

<code>
docker-compose up
</code>

Alternative:

<code>
https://github.com/jaegertracing/jaeger/blob/master/crossdock/jaeger-docker-compose.yml
</code>

<code>
docker run -d --link=elasticsearch --name jaeger  -e SPAN_STORAGE_TYPE=elasticsearch -e ES_SERVER_URLS=http://elasticsearch:9200 -e ES_TAGS_AS_FIELDS_ALL=true   -p 5775:5775/udp   -p 6831:6831/udp   -p 6832:6832/udp   -p 5778:5778   -p 16686:16686   -p 14268:14268   -p 14250:14250   -p 9411:9411 jaegertracing/all-in-one:1.21

docker run --name elasticsearch -d -p 9200:9200 -p 9300:9300 -p 5601:5601 -e "discovery.type=single-node" nshou/elasticsearch-kibana

docker run -d -p 3000:3000 grafana/grafana
</code>

## TIPS

https://opentelemetry.io/docs/

OPEN-TELEMETRY Automatic Instrumentation

`-javaagent:"opentelemetry-javaagent-all.jar" -Dotel.exporter=jaeger -Dotel.exporter.jaeger.endpoint=localhost:14250  -Dotel.exporter.jaeger.service.name="camunda" -Dotel.instrumentation.jdbc.enabled=false -Dotel.instrumentation.spring-webmvc.enabled=false -Dotel.instrumentation.jaxrs.enabled=false -Dotel.trace.classes.exclude="com.tomtom.flow.controller.CamundaApiDelegateController" `

`-Dio.opentelemetry.javaagent.slf4j.simpleLogger.defaultLogLevel=debug`


OPEN-TELEMETRY Manual Instrumentation

https://opentelemetry.io/docs/java/manual_instrumentation/

Other

