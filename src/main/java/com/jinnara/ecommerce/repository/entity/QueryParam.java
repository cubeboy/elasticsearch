package com.jinnara.ecommerce.repository.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Builder
@Data
public class QueryParam extends QueryTemplate {
  @MatchQuery(field="customer_full_name")
  private final String customerFullName;
  @TermsQuery(field="day_of_week")
  private final List<String> dayOfWeeks;
}
