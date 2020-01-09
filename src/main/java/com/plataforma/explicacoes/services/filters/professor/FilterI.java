package com.plataforma.explicacoes.services.filters.professor;

import java.util.Set;

public interface FilterI<T> {
    Set<T> filter(Set<T> entities);
}
