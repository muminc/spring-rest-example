package com.choudhury.domain;

public class ObjectWithId {
    protected long id;

    public ObjectWithId() {
    }

    public ObjectWithId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
