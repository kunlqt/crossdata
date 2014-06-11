/*
 * Stratio Meta
 *
 * Copyright (c) 2014, Stratio, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package com.stratio.meta.driver;

import com.stratio.meta.common.result.ErrorResult;
import com.stratio.meta.common.result.Result;
import com.stratio.meta.server.MetaServer;

import org.testng.annotations.*;

public class DriverParentTest extends ParentCassandraTest {

  private static final long SLEEP_TIME = 5000;

  protected static BasicDriver driver = null;

  protected static MetaServer metaServer = null;

  @BeforeClass
  public void init() {
    System.out.println("INIT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    if(metaServer == null) {
      metaServer = new MetaServer();
      metaServer.init(null);
      metaServer.start();

      try {
        Thread.sleep(SLEEP_TIME);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      driver = new BasicDriver();
      try {
        driver.connect("TEST_USER");
      }catch (Exception e){
        e.printStackTrace();
        driver = null;
        finish();
      }
    }
  }

  //@AfterClass(alwaysRun=true)
  @AfterSuite
  public void finish() {
    System.out.println("FINISHING ------------------------------");
    driver.close();
    metaServer.stop();
    metaServer.destroy();
//    try {
//      Thread.sleep(SLEEP_TIME*2);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    System.out.println("FINISH <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
  }

  public static String getErrorMessage(Result metaResult){
    String result = "Invalid class: " + metaResult.getClass();
    if(ErrorResult.class.isInstance(metaResult)){
      result = ErrorResult.class.cast(metaResult).getErrorMessage();
    }
    return result;
  }

}
