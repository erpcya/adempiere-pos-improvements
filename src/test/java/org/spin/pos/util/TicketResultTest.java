package org.spin.pos.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class TicketResultTest {

    @Test
    void newInstanceHasDefaultValues() {
        TicketResult result = TicketResult.newInstance();

        assertFalse(result.isError());
        assertNull(result.getSummary());
        assertNotNull(result.getResultValues());
        assertTrue(result.getResultValues().isEmpty());
        assertNull(result.getReportFile());
    }

    @Test
    void withErrorUpdatesFlagAndReturnsSameInstance() {
        TicketResult result = TicketResult.newInstance();

        assertSame(result, result.withError(true));
        assertTrue(result.isError());
        assertSame(result, result.withError(false));
        assertFalse(result.isError());
    }

    @Test
    void withSummaryAcceptsTextEmptyAndNullAndReturnsSameInstance() {
        TicketResult result = TicketResult.newInstance();

        assertSame(result, result.withSummary("Printed"));
        assertSame("Printed", result.getSummary());
        assertSame(result, result.withSummary(""));
        assertSame("", result.getSummary());
        assertSame(result, result.withSummary(null));
        assertNull(result.getSummary());
    }

    @Test
    void withResultValuesAcceptsMapAndNullAndReturnsSameInstance() {
        TicketResult result = TicketResult.newInstance();
        Map<String, Object> values = new HashMap<>();
        values.put("copies", 2);

        assertSame(result, result.withResultValues(values));
        assertSame(values, result.getResultValues());
        assertSame(result, result.withResultValues(null));
        assertNull(result.getResultValues());
    }

    @Test
    void withReportFileAcceptsFileAndNullAndReturnsSameInstance() {
        TicketResult result = TicketResult.newInstance();
        File reportFile = new File("ticket.pdf");

        assertSame(result, result.withReportFile(reportFile));
        assertSame(reportFile, result.getReportFile());
        assertSame(result, result.withReportFile(null));
        assertNull(result.getReportFile());
    }
}
