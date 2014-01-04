/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.functions.log

import com.typesafe.scalalogging.slf4j.Logging

/**
 * to wrap com.typesafe.scalalogging.slf4j.Logging
 * to use logback, config files are 
 * main - functions/src/main/resources/com/github/takahish0306/framework/log/logback.xml
 * test - functions/src/main/resources/com/github/takahish0306/framework/log/logback-test.xml
 */
trait Logger extends Logging
