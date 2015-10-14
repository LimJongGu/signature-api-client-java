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
package no.digipost.signature.client;

import no.digipost.signature.client.asice.CreateASiCE;
import no.digipost.signature.client.asice.DocumentBundle;
import no.digipost.signature.client.domain.Sender;
import no.digipost.signature.client.domain.SignatureJob;
import no.digipost.signature.client.internal.CreateSignatureJobRequest;
import no.digipost.signature.client.internal.SenderFacade;
import no.digipost.signering.schema.v1.signature_job.XMLSignatureJobRequest;
import no.digipost.signering.schema.v1.signature_job.XMLSignatureJobResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignatureClient {

    private static final Logger LOG = LoggerFactory.getLogger(SignatureClient.class);

    private final Sender sender;
    private final ClientConfiguration clientConfiguration;
    private final CreateASiCE documentBundleBuilder;
    private final CreateSignatureJobRequest signatureJobRequestBuilder;
    private final SenderFacade senderFacade;

    public SignatureClient(Sender sender, ClientConfiguration clientConfiguration) {
        this.sender = sender;
        this.clientConfiguration = clientConfiguration;
        this.documentBundleBuilder = new CreateASiCE();
        signatureJobRequestBuilder = new CreateSignatureJobRequest();
        this.senderFacade = new SenderFacade(clientConfiguration);
    }

    public XMLSignatureJobResponse create(final SignatureJob signatureJob) {
        DocumentBundle documentBundle = documentBundleBuilder.createASiCE(signatureJob.getDocument(), clientConfiguration.getKeyStoreConfig());
        XMLSignatureJobRequest signatureJobRequest = signatureJobRequestBuilder.createSignatureJobRequest(signatureJob);

        return senderFacade.sendSignatureJobRequest(signatureJobRequest, documentBundle);
    }

    public String tryConnecting() {
        String responseString = senderFacade.tryConnecting();
        LOG.debug("Server responded with:\n" + responseString);
        if (!responseString.contains(sender.getOrganizationNumber())) {
            // TODO (EHH): Innføre en egen exception-type?
            throw new RuntimeException("Server didn't return organization number. Something is configured wrong.");
        }
        return responseString;
    }

}
