package com.nhom10.webts.exception;

public class EntityPrimaryKeyExistsException extends RuntimeException {
    public EntityPrimaryKeyExistsException(Class<?> clazz, Long id) {
        super(clazz.getSimpleName() + " has id=" + id + " found!");
    }

    public EntityPrimaryKeyExistsException(Class<?> clazz, String id) {
        super(clazz.getSimpleName() + " has id=" + id + " found!");
    }
}
