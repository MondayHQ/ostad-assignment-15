package com.example.securednote.mappers;

public interface Mapper<A, B> {

    B mapTo(A a);

    A mapFrom(B b);

    void updateExisting(A a, B b);

}
