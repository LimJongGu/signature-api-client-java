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
package no.digipost.signature.client.asice.manifest;

import no.digipost.signature.client.core.Document;
import no.digipost.signature.client.core.IdentifierInSignedDocuments;
import no.digipost.signature.client.core.Sender;
import no.digipost.signature.client.portal.NotificationsUsingLookup;
import no.digipost.signature.client.portal.PortalDocument;
import no.digipost.signature.client.portal.PortalJob;
import no.digipost.signature.client.portal.PortalSigner;
import org.junit.Test;

import java.time.Clock;
import java.util.Collections;

import static java.util.concurrent.TimeUnit.DAYS;
import static org.junit.Assert.fail;

public class CreatePortalManifestTest {

    private final Clock clock = Clock.systemDefaultZone();

    @Test
    public void accept_valid_manifest() {
        CreatePortalManifest createManifest = new CreatePortalManifest(clock);

        PortalDocument document = PortalDocument.builder("Title", "file.txt", "hello".getBytes())
                .message("Message")
                .fileType(Document.FileType.TXT)
                .build();

        PortalJob job = PortalJob.builder(document, Collections.singletonList(PortalSigner.identifiedByPersonalIdentificationNumber("12345678910", NotificationsUsingLookup.EMAIL_ONLY).build()))
                .withActivationTime(clock.instant())
                .availableFor(30, DAYS)
                .withIdentifierInSignedDocuments(IdentifierInSignedDocuments.PERSONAL_IDENTIFICATION_NUMBER_AND_NAME)
                .build();
        try {
            createManifest.createManifest(job, new Sender("123456789"));
        } catch (Exception e) {
            fail("Expected no exception, got: " + e.getMessage());
        }
    }

}
