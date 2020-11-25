/*
 * Copyright (c) 2020 Adam Gaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.adgadev.examples.bookshop;

import com.adgadev.jplusone.asserts.api.JPlusOneAssertionContext;
import com.adgadev.jplusone.asserts.api.JPlusOneAssertionRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
class BookshopControllerTest {

    @Autowired
    private JPlusOneAssertionContext assertionContext;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldGetBookDetails() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/book/lazy")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        JPlusOneAssertionRule rule = JPlusOneAssertionRule
                .within().lastSession()
                .shouldBe().noImplicitOperations().exceptAnyOf(exclusions -> exclusions
                        .loadingEntity(Author.class)
                        .loadingCollection(Author.class, "books")
                );
        rule.check(assertionContext);
    }
}
