/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.acme.numbers.serverless.workflow.functions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@QuarkusIntegrationTest
class RestExampleIT {

    @BeforeAll
    static void init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testRestExample() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(Collections.singletonMap("inputNumbers", new int[]{1, 2, 3, 4, 5, 6, 7}))
                .post("/RestExample")
                .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .body("workflowdata", not(emptyOrNullString()));
    }
}
