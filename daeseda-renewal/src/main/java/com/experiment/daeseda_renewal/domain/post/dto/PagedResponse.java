package com.experiment.daeseda_renewal.domain.post.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PagedResponse<T> {

  private final int number;
  private final int size;
  private final int totalPages;
  private final long totalElements;
  private final boolean first;
  private final boolean last;
  private final List<T> content;

  public static <T> PagedResponse<T> of(Page<T> p) {
    return PagedResponse.<T>builder()
                        .number(p.getNumber())
                        .size(p.getSize())
                        .totalPages(p.getTotalPages())
                        .totalElements(p.getTotalElements())
                        .first(p.isFirst())
                        .last(p.isLast())
                        .content(p.getContent())
                        .build();
  }
}
