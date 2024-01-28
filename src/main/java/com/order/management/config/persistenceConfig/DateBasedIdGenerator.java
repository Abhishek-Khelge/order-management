package com.order.management.config.persistenceConfig;

import com.order.management.entity.AuditBaseEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class DateBasedIdGenerator implements IdentifierGenerator {

    private static final AtomicInteger sequenceTill999 = new AtomicInteger(0);

    private static final int MAX_SEQUENCE = 500;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (object instanceof AuditBaseEntity entity) {
            if (StringUtils.hasText(entity.getId()))
                return entity.getId();
        }
        String id1 = getPKDatePrefixFromDate();
        String id2 = "S01";
        String id3 = String.format("%04d", Math.abs(Thread.currentThread().getId() % 10000));
        String id4 = getNextSequence();
        return id1 + id2 + id3 + id4;
    }

    public String getPKDatePrefixFromDate() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        return now.format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"));
    }

    private String getNextSequence() {
        if (sequenceTill999.get() > MAX_SEQUENCE) {
            sequenceTill999.set(0);
        }
        return String.format("%03d", sequenceTill999.incrementAndGet());
    }

}
