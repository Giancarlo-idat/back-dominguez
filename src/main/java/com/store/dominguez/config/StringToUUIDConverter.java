package com.store.dominguez.config;

import org.modelmapper.AbstractConverter;

import java.util.UUID;

public class StringToUUIDConverter extends AbstractConverter<String, UUID> {

    @Override
    protected UUID convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        return UUID.fromString(source);
    }
}