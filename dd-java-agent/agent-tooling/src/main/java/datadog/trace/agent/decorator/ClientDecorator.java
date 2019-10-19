package datadog.trace.agent.decorator;

import datadog.trace.api.DDTags;
import datadog.trace.instrumentation.api.AgentSpan;
import io.opentracing.Span;
import io.opentracing.tag.Tags;

public abstract class ClientDecorator extends BaseDecorator {

  protected abstract String service();

  protected String spanKind() {
    return Tags.SPAN_KIND_CLIENT;
  }

  @Override
  public Span afterStart(final Span span) {
    assert span != null;
    if (service() != null) {
      span.setTag(DDTags.SERVICE_NAME, service());
    }
    Tags.SPAN_KIND.set(span, spanKind());
    return super.afterStart(span);
  }

  @Override
  public AgentSpan afterStart(final AgentSpan span) {
    assert span != null;
    if (service() != null) {
      span.setTag(DDTags.SERVICE_NAME, service());
    }
    span.setTag(Tags.SPAN_KIND.getKey(), spanKind());
    return super.afterStart(span);
  }
}
