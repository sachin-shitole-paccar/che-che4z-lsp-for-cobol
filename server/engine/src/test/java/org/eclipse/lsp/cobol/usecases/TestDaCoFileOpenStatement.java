/*
 * Copyright (c) 2022 DAF Trucks NV.
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
 * Tests the DaCo OPEN FILE statement
 */
class TestDaCoFileOpenStatement {

    private static final String TEXT =
            "        IDENTIFICATION DIVISION. \r\n"
                    + "        PROGRAM-ID. test1. \r\n"
                    + "        DATA DIVISION. \r\n"
                    + "        WORKING-STORAGE SECTION. \r\n"
                    + "        01 {$*WS-AREA}. \r\n"
                    + "           03 {$*AREA-XW1}. \r\n"
                    + "             05 {$*DET001-XW1}. \r\n"
                    + "               07 FILLER               PIC X(5)    VALUE 2. \r\n"
                    + "        PROCEDURE DIVISION. \r\n"
                    + "            OPEN FILE 0123. \r\n"
                    + "            OPEN FILE 0123 MAX LENGTH 2. \r\n"
                    + "            OPEN FILE 0123 MAX LENGTH {$DET001-XW1}. \r\n"
                    + "            OPEN FILE 0123 MAX LENGTH LAYOUT. \r\n"
                    // Negative Tests
                    + "            OPEN FILE {01|1}. \r\n"
                    + "            OPEN FILE {01234|1}. \r\n"
                    + "            OPEN FILE 0123 MAX LENGTH {ABCD|2}. \r\n";

    @Test
    void test() {

        UseCaseEngine.runTest(
                TEXT,
                ImmutableList.of(),
                ImmutableMap.of(
                        "1",
                        new Diagnostic(
                                new Range(),
                                "Exact length of file reference must be 4 bytes",
                                DiagnosticSeverity.Error,
                                ErrorSource.DIALECT.getText()),
                        "2",
                        new Diagnostic(
                                new Range(),
                                "Variable ABCD is not defined",
                                DiagnosticSeverity.Error,
                                ErrorSource.PARSING.getText())),
                ImmutableList.of(), DialectConfigs.getDaCoAnalysisConfig());
    }
}
