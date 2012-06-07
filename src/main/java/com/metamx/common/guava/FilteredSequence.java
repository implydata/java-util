/*
 * Copyright 2011,2012 Metamarkets Group Inc.
 *
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
 */

package com.metamx.common.guava;

import com.google.common.base.Predicate;

/**
 */
public class FilteredSequence<T> implements Sequence<T>
{
  private final Sequence<T> baseSequence;
  private final Predicate<T> pred;

  public FilteredSequence(
      Sequence<T> baseSequence,
      Predicate<T> pred
  )
  {
    this.baseSequence = baseSequence;
    this.pred = pred;
  }

  @Override
  public <OutType> OutType accumulate(final Accumulator<OutType, T> accumulator)
  {
    return baseSequence.accumulate(
        new FilteringAccumulator<OutType, T>(pred, accumulator)
    );
  }

  @Override
  public <OutType> OutType accumulate(OutType initValue, Accumulator<OutType, T> accumulator)
  {
    return baseSequence.accumulate(
        initValue,
        new FilteringAccumulator<OutType, T>(pred, accumulator)
    );
  }

}