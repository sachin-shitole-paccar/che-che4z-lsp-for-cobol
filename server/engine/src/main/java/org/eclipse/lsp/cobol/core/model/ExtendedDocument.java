/*
 * Copyright (c) 2020 Broadcom.
 * The term "Broadcom" refers to Broadcom Inc. and/or its subsidiaries.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Broadcom, Inc. - initial API and implementation
 *
 */
package org.eclipse.lsp.cobol.core.model;

import lombok.Value;
import org.eclipse.lsp.cobol.core.semantics.CopybooksRepository;

import java.util.Map;

/**
 * This value class represents the extended document, i.e. the original file where all the COPY
 * statements are replaced by the actual copybooks content.
 */
@Value
public class ExtendedDocument {
  String uri;
  String text;
  CopybooksRepository copybooks;
  Map<String, DocumentMapping> documentMapping;
}
