package com.charles.kataquiz.model;

public record Category(int id, String name) {

    @Override
    public String toString() {
        return this.name;
    }
}
