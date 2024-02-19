package org.chamara.dispatchapp.handler;

import org.chamara.dispatchapp.message.OrderCreated;
import org.chamara.dispatchapp.service.DispatchService;
import org.chamara.dispatchapp.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderCreateHandlerTest {
    private OrderCreateHandler underTest;

    private DispatchService dispatchServiceMock;

    @BeforeEach
    void setUp() {
        dispatchServiceMock = mock(DispatchService.class);
        underTest = new OrderCreateHandler(dispatchServiceMock);
    }

    @Test
    void itShouldListen() throws Exception {
        // given
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        // when
        underTest.listen(testEvent);
        // then
        verify(dispatchServiceMock, times(1)).process(testEvent);
    }

    @Test
    void itWillThrowsExceptionListen() throws Exception {
        // given
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(randomUUID(), randomUUID().toString());
        doThrow(new RuntimeException("Service failure")).when(dispatchServiceMock).process(testEvent);
        // when
        underTest.listen(testEvent);
        // then
        verify(dispatchServiceMock, times(1)).process(testEvent);
    }
}