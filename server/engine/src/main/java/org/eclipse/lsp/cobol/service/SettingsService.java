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
package org.eclipse.lsp.cobol.service;

import com.google.gson.JsonPrimitive;
import org.eclipse.lsp.cobol.service.utils.SettingsParametersEnum;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Provide API definition to fetch configuration settings from the client. Works via LSP
 * workspace/configuration request.
 */
public interface SettingsService {
  /**
   * Fetch the required configuration section from the client. Note that Scope URI is null. The
   * {@link SettingsParametersEnum#LSP_PREFIX LSP prefix} will be added to specified section.
   *
   * @param section the required section.
   * @return a list of one configuration object.
   */
  CompletableFuture<List<Object>> getConfiguration(String... section);

  /**
   * Fetch the required configuration sections from the client. Note that Scope URI is null. The
   * {@link SettingsParametersEnum#LSP_PREFIX LSP prefix} will be added to each specified section.
   *
   * @param sections the required sections.
   * @return a list of configuration objects.
   */
  CompletableFuture<List<Object>> getConfigurations(List<String> sections);

  /**
   * Convert JSON objects in configuration response to strings.
   *
   * @param objects - content of the client settings response
   * @return objects converted to strings
   */
  List<String> toStrings(List<Object> objects);

  /**
   * Get config data as optional string
   *
   * @param data config response
   * @return optional string value
   */
  static Optional<String> getValueAsString(List<Object> data) {
    if (data == null || data.isEmpty()) return Optional.empty();
    Object obj = data.get(0);
    if (!(obj instanceof JsonPrimitive)) return Optional.empty();

    return Optional.of(((JsonPrimitive) obj).getAsString()).filter(it -> !it.isEmpty());
  }
}
