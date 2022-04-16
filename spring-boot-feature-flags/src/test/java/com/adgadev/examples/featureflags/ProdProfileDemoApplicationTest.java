/*
 * Copyright (c) 2022 Adam Gaj
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

package com.adgadev.examples.featureflags;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// When property spring.profiles.group.property.prod set to `halo`,
// the spring context won't start due to mongodb connectivity issue

@SpringBootTest // (properties = "spring.profiles.group.prod=halo")
@ActiveProfiles("prod")
class ProdProfileDemoApplicationTest {

    @Test
    void contextLoads() {
    }
}