/*
 * Copyright 2013-2014 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
 * (see the CONTRIBUTORS file).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.xquery.runner.rt.vendor.saxon;

import org.intellij.xquery.runner.rt.RunnerAppTest;
import org.intellij.xquery.runner.rt.XQueryDataSourceType;
import org.junit.experimental.theories.DataPoints;

import static org.intellij.xquery.runner.rt.XQueryItemType.TEXT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DATE_TIME_STAMP;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_DURATION;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_LANGUAGE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_NEGATIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NON_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_NORMALIZED_STRING;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_POSITIVE_INTEGER;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_TOKEN;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_BYTE;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_INT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_LONG;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNSIGNED_SHORT;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED;
import static org.intellij.xquery.runner.rt.XQueryItemType.XS_UNTYPED_ATOMIC;

/**
 * User: ligasgr
 * Date: 12/01/14
 * Time: 19:14
 */
public class SaxonNativeRunnerAppTest extends RunnerAppTest {

    @DataPoints
    public static DataPair[] getSaxonCompatibleData() {
        return new DataPair[]{
                DataPair.pair(XS_NON_NEGATIVE_INTEGER, NUMERIC_VALUE),
                DataPair.pair(XS_POSITIVE_INTEGER, NUMERIC_VALUE),
                DataPair.pair(XS_NON_POSITIVE_INTEGER, NEGATIVE_NUMERIC_VALUE),
                DataPair.pair(XS_NEGATIVE_INTEGER, NEGATIVE_NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_INT, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_SHORT, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_LONG, NUMERIC_VALUE),
                DataPair.pair(XS_UNSIGNED_BYTE, "10"),
                DataPair.pair(XS_DURATION, "P3Y6M4DT12H30M5S"),
                DataPair.pair(XS_BYTE, "32"),
                DataPair.pair(XS_NORMALIZED_STRING, VALUE),
                DataPair.pair(XS_TOKEN, VALUE),
                DataPair.pair(XS_LANGUAGE, VALUE),
                DataPair.pair(XS_DATE_TIME_STAMP, "2013-12-31T23:59:59Z"),
                DataPair.pair(XS_UNTYPED, VALUE),
                DataPair.pair(XS_UNTYPED_ATOMIC, VALUE),
                DataPair.pair(TEXT, VALUE),
        };
    }

    @Override
    protected String getDataSourceType() {
        return XQueryDataSourceType.SAXON_NATIVE.toString();
    }
}
