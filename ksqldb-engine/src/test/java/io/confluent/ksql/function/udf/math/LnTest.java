/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License; you may not use this file
 * except in compliance with the License.  You may obtain a copy of the License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.function.udf.math;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Test;

public class LnTest {

  private Ln udf;

  @Before
  public void setUp() {
    udf = new Ln();
  }

  @Test
  public void shouldHandleNull() {
    assertThat(udf.ln((Integer)null), is(nullValue()));
    assertThat(udf.ln((Long)null), is(nullValue()));
    assertThat(udf.ln((Double)null), is(nullValue()));
  }

  @Test
  public void shouldHandleNegative() {
    assertThat(Double.isNaN(udf.ln(-1.0)), is(true));
  }

  @Test
  public void shouldHandleZero() {
    assertThat(Double.isInfinite(udf.ln(0.0)), is(true));
  }

  @Test
  public void shouldHandlePositive() {
    assertThat(udf.ln(1), is(0.0));
    assertThat(udf.ln(1L), is(0.0));
    assertThat(udf.ln(1.0), is(0.0));
  }
}