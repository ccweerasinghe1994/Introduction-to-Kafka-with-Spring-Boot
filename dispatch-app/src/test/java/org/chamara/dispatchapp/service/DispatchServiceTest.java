package org.chamara.dispatchapp.service;

import org.chamara.dispatchapp.message.OrderCreated;
import org.chamara.dispatchapp.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class DispatchServiceTest {

    private DispatchService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DispatchService();
    }

    @Test
    void itShouldProcess() {
        // given
        // when
        // then
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        underTest.process(testEvent);
    }
}