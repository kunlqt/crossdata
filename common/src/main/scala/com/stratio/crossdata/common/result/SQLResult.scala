/**
 * Copyright (C) 2015 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stratio.crossdata.common.result

import java.util.UUID

import com.stratio.crossdata.common.{Result, SQLResult}
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StructType

case class SuccessfulQueryResult(queryId: UUID, result: Array[Row], schema: StructType) extends SQLResult {
  override val resultSet = result

  def hasError = false

}

case class QueryCancelled(queryId: UUID) extends Result {
  override def hasError: Boolean = false
}

case class ErrorResult(queryId: UUID, message: String, cause: Option[Throwable] = None) extends SQLResult {
  override lazy val resultSet = cause.fold(throw new RuntimeException(message)) {
    throwable => throw new RuntimeException(message,throwable)
  }
  def hasError = true
}