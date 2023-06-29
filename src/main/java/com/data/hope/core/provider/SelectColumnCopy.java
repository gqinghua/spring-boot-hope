/*
 * Datart
 * <p>
 * Copyright 2021
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.data.hope.core.provider;

import com.data.hope.core.provider.sql.Alias;
import com.data.hope.core.provider.sql.ColumnOperatorCopy;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectColumnCopy extends ColumnOperatorCopy implements Alias {

    private String alias;

    public static SelectColumnCopy of(String alias, String names) {
        SelectColumnCopy selectColumn = new SelectColumnCopy();
        selectColumn.setAlias(alias);
        selectColumn.setColumn(names);
        return selectColumn;
    }



}
