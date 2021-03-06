/**
 * Copyright (C) Posten Norge AS
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
package no.digipost.signature.client.direct;

import no.digipost.signature.api.xml.XMLDirectSignatureJobStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class DirectJobStatusTest {

    @Test
    public void ableToConvertAllStatusesFromXsd() {
        List<DirectJobStatus> convertedStatuses = new ArrayList<>();
        for (XMLDirectSignatureJobStatus xmlStatus : XMLDirectSignatureJobStatus.values()) {
            convertedStatuses.add(DirectJobStatus.fromXmlType(xmlStatus));
        }
        assertThat(convertedStatuses, hasSize(XMLDirectSignatureJobStatus.values().length));
    }
}
