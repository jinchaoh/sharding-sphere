/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.proxy.transport.mysql.packet.command.query.binary.execute;

import lombok.Getter;

/**
 * Null bitmap.
 *
 * @author zhangyonglun
 */
public final class NullBitmap {
    
    private final int offset;
    
    @Getter
    private final int[] nullBitmap;
    
    public NullBitmap(final int columnsNumbers, final int offset) {
        this.offset = offset;
        nullBitmap = new int[calculateLength(columnsNumbers, offset)];
    }
    
    private int calculateLength(final int columnsNumbers, final int offset) {
        return (columnsNumbers + offset + 7) / 8;
    }
    
    /**
     * Judge parameter is null or not null.
     *
     * @param index column index
     * @return parameter is null or not null
     */
    public boolean isNullParameter(final int index) {
        int bytePosition = (index + offset) / 8;
        int bitPosition = (index + offset) % 8;
        return (nullBitmap[bytePosition] & (1 << bitPosition)) != 0;
    }
    
    /**
     * Set null bit.
     *
     * @param index index
     */
    public void setNullBit(final int index) {
        int bytePosition = (index + offset) / 8;
        int bitPositionInCurrentByte = (index + offset) % 8;
        nullBitmap[bytePosition] = 1 << bitPositionInCurrentByte;
    }
}
