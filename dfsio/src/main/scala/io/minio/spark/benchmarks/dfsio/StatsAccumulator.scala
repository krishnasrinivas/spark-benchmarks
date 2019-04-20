/*
 * Copyright 2019 MinIO, Inc.
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

package io.minio.spark.benchmarks.dfsio

import org.apache.spark.rdd.RDD
import scala.collection.breakOut
import scala.collection.generic.CanBuildFrom

case class Stats(tasks: Long, size: BytesSize, time: Long, rate: Float, sqRate: Float, median: Float)

object StatsAccumulator {

  def median(seq: Seq[Float]): Float = {
    val sortedSeq = seq.sortWith(_ < _)
     
    if (seq.size % 2 == 1) sortedSeq(sortedSeq.size / 2)
      else {
          val (up, down) = sortedSeq.splitAt(seq.size / 2)
          (up.last + down.head) / 2
      }
  }

  def mode
    [T, CC[X] <: Seq[X]](coll: CC[T])
      (implicit o: T => Ordered[T], cbf: CanBuildFrom[Nothing, T, CC[T]])
        : CC[T] = {
            val grouped = coll.groupBy(x => x).mapValues(_.size).toSeq
              val max = grouped.map(_._2).max
                grouped.filter(_._2 == max).map(_._1)(breakOut)
        }

  def accumulate(rdd: RDD[Stats], median: Float): Stats = rdd.reduce {
    (s1, s2) => s1.copy(
      tasks = s1.tasks + s2.tasks,
      size = s1.size + s2.size,
      time = s1.time + s2.time,
      rate = s1.rate + s2.rate,
      sqRate = s1.sqRate + s2.sqRate,
      median = median
    )
  }

}
