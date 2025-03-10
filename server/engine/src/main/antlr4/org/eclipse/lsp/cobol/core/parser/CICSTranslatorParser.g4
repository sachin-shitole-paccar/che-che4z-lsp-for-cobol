/*
 * Copyright (c) 2022 Broadcom.
 * The term "Broadcom" refers to Broadcom Inc. and/or its subsidiaries.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Broadcom, Inc. - initial API and implementation
 */

parser grammar CICSTranslatorParser;
options {tokenVocab = CICSTranslatorLexer;}

startRule: .*? cicsTranslatorRules* EOF;
cicsTranslatorRules: cicsTranslatorStmt .*?;

cicsTranslatorStmt
    : XOPTS LPARENCHAR compilerOption (COMMACHAR? compilerOption)* RPARENCHAR
    ;

compilerOption
    : APOST |
      CBLCARD |
      CICS |
      COBOL2 |
      COBOL3 |
      CPSM |
      DBCS |
      DEBUG |
      DLI |
      EDF |
      EXCI |
      FEPI |
      FLAG |
      LENGTH |
      LINECOUNT |
      LINKAGE |
      NATLANG |
      NOCBLCARD |
      NOCPSM |
      NODEBUG |
      NOEDF |
      NOFEPI |
      NOLENGTH |
      NOLINKAGE |
      NONUM |
      NOOPTIONS |
      NOSEQ |
      NOSPIE |
      NOVBREF |
      NUM |
      OPTIONS |
      QUOTE |
      SEQ |
      SP |
      SPACE |
      SPIE |
      SYSEIB |
      VBREF
    ;