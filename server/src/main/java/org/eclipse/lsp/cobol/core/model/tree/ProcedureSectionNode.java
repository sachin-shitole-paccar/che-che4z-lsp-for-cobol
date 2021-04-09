/*
 * Copyright (c) 2021 Broadcom.
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
package org.eclipse.lsp.cobol.core.model.tree;

import lombok.Value;
import org.eclipse.lsp.cobol.core.model.Locality;

/**
 * The class represents procedure section in COBOL.
 */
@Value
public class ProcedureSectionNode extends Node {
  String name;
  String text;
  public ProcedureSectionNode(Locality location, String name, String text) {
    super(location, NodeType.PROCEDURE_SECTION);
    this.name = name;
    this.text = text;
  }
}
