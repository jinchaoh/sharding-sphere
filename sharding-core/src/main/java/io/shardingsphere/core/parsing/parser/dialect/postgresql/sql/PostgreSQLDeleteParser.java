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

package io.shardingsphere.core.parsing.parser.dialect.postgresql.sql;

import io.shardingsphere.core.parsing.lexer.LexerEngine;
import io.shardingsphere.core.parsing.lexer.dialect.postgresql.PostgreSQLKeyword;
import io.shardingsphere.core.parsing.lexer.token.DefaultKeyword;
import io.shardingsphere.core.parsing.lexer.token.Keyword;
import io.shardingsphere.core.parsing.parser.dialect.postgresql.clause.facade.PostgreSQLDeleteClauseParserFacade;
import io.shardingsphere.core.parsing.parser.sql.dml.delete.AbstractDeleteParser;
import io.shardingsphere.core.rule.ShardingRule;

/**
 * Delete parser for PostgreSQL.
 *
 * @author zhangliang
 */
public final class PostgreSQLDeleteParser extends AbstractDeleteParser {
    
    public PostgreSQLDeleteParser(final ShardingRule shardingRule, final LexerEngine lexerEngine) {
        super(shardingRule, lexerEngine, new PostgreSQLDeleteClauseParserFacade(shardingRule, lexerEngine));
    }
    
    @Override
    protected Keyword[] getSkippedKeywordsBetweenDeleteAndTable() {
        return new Keyword[] {DefaultKeyword.FROM, PostgreSQLKeyword.ONLY};
    }
}
