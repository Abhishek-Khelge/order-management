package com.order.management.config.persistenceConfig;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

@Service("dateTimeProvider")
public class CustomDateTimeProvider implements DateTimeProvider {

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(ZonedDateTime.now());
    }

}
