package com.tomtom.flow.examples.lettercounter;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

@Configuration
public class OpenTelemetryConfiguration {

    @Bean
    public OpenTelemetry openTelemetry() {
        // Create a channel towards Jaeger end point
        ManagedChannel jaegerChannel =
                ManagedChannelBuilder.forAddress("localhost", 14250).usePlaintext().build();
        // Export traces to Jaeger
        JaegerGrpcSpanExporter jaegerExporter =
                JaegerGrpcSpanExporter.builder()
                        .setServiceName("vowel-count")
                        .setChannel(jaegerChannel)
                        .setTimeout(30, TimeUnit.SECONDS)
                        .build();

        // Set to process the spans by the Jaeger Exporter
        OpenTelemetrySdk openTelemetry =
                OpenTelemetrySdk.builder()
                        .setTracerProvider(
                                SdkTracerProvider.builder()
                                        .addSpanProcessor(SimpleSpanProcessor.create(jaegerExporter))
                                        .build())
                        .build();

        // it's always a good idea to shut down the SDK cleanly at JVM exit.
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> openTelemetry.getTracerManagement().shutdown()));
        return openTelemetry;
    }
}

//Manual Instrumentation create child span for parent span given in Map
/*

		String trace = (String) varMap.get("traceparent");
		Map<String, String> w3cmap = new HashMap<>();
		w3cmap.put("traceparent",(String) varMap.get("traceparent"));
		Context context = W3CTraceContextPropagator.getInstance().extract(Context.current(), w3cmap, new TextMapPropagator.Getter<Map>() {

					@Override
					public String get(Map carrier, String key) {
						return (String)carrier.get(key);
					}

					@Override
					public Iterable<String> keys(Map carrier) {
						return carrier.keySet();
					}
		});

		Tracer tracer = openTelemetry.getTracer(this.getClass().getName());
		Span span = tracer.spanBuilder(this.getClass().getName()).setParent(context).startSpan();
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			span.end();
		}
*/