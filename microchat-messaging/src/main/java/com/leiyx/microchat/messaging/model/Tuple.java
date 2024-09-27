package com.leiyx.microchat.messaging.model;

import java.util.Objects;

public class Tuple {
    String id;
    String type;

    public Tuple(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", id, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return Objects.equals(id, tuple.id) &&
                Objects.equals(type, tuple.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
