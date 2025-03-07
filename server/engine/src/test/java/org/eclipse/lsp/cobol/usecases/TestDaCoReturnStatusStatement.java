/*
 * Copyright (c) 2021 DAF Trucks NV.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * DAF Trucks NV – implementation of DaCo COBOL statements
 * and DAF development standards
 */
package org.eclipse.lsp.cobol.usecases;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.eclipse.lsp.cobol.core.model.ErrorSource;
import org.eclipse.lsp.cobol.usecases.engine.UseCaseEngine;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.lsp4j.Range;
import org.junit.jupiter.api.Test;

/**
 * Tests the DaCo Return Status statement
 */
class TestDaCoReturnStatusStatement {

    private static final String TEXT =
            "        IDENTIFICATION DIVISION. \r\n"
                    + "        PROGRAM-ID. test1. \r\n"
                    + "        DATA DIVISION. \r\n"
                    + "        WORKING-STORAGE SECTION. \r\n"
                    + "        01 {$*WS-AREA}. \r\n"
                    + "           03 {$*AREA-XW1}. \r\n"
                    + "             05 {$*DET001-XW1}. \r\n"
                    + "               07 FILLER               PIC X(5)    VALUE 'REMBD'. \r\n"
                    + "        PROCEDURE DIVISION. \r\n"
                    + "            RETURN INFO 002. \r\n"
                    + "            RETURN WARNING 002. \r\n"
                    + "            RETURN ERROR 002. \r\n"
                    + "            RETURN INFO 002 'SDG GFG'. \r\n"
                    + "            RETURN WARNING 002 'SDG GFG'. \r\n"
                    + "            RETURN ERROR 002 'SDG GFG'. \r\n"
                    + "            RETURN INFO 002 {$DET001-XW1}. \r\n"
                    + "            RETURN WARNING 002 {$DET001-XW1}. \r\n"
                    + "            RETURN ERROR 002 {$DET001-XW1}. \r\n"
                    + "            RETURN ERROR 002 {$DET001-XW1} {$DET001-XW1}. \r\n"
                    + "            RETURN ERROR 002 {$DET001-XW1} {$DET001-XW1} {$DET001-XW1}. \r\n"
                    + "            RETURN ERROR 002 'MODULE' {$DET001-XW1}. \r\n"
                    + "            RETURN ERROR 002 'MODULE' {$DET001-XW1} {$DET001-XW1}. \r\n"
                    // Negative tests
                    + "            RETURN INFO {00|2}. \r\n"
                    + "            RETURN WARNING {00|2}. \r\n"
                    + "            RETURN ERROR {00|2}. \r\n"
                    + "            RETURN INFO {0023|2}. \r\n"
                    + "            RETURN WARNING {0023|2}. \r\n"
                    + "            RETURN ERROR {0023|2}. \r\n"
                    + "            RETURN INFO 002 {ASD|1}. \r\n"
                    + "            RETURN WARNING 002 {ASD|1}. \r\n"
                    + "            RETURN ERROR 002 {ASD|1}. \r\n";

    @Test
    void test() {

        UseCaseEngine.runTest(
                TEXT,
                ImmutableList.of(),
                ImmutableMap.of(
                        "1",
                        new Diagnostic(
                                new Range(),
                                "Variable ASD is not defined",
                                DiagnosticSeverity.Error,
                                ErrorSource.PARSING.getText()),
                        "2",
                        new Diagnostic(
                                new Range(),
                                "Exact length of message code must be 3 bytes",
                                DiagnosticSeverity.Error,
                                ErrorSource.DIALECT.getText())),
                ImmutableList.of(),
                DialectConfigs.getDaCoAnalysisConfig());
    }
}
