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
package no.digipost.signature.client.portal;

import no.digipost.signature.client.core.ConfirmationReference;
import no.digipost.signature.client.core.PAdESReference;
import no.digipost.signature.client.core.XAdESReference;
import no.digipost.signering.schema.v1.portal_signature_job.XMLPortalSignatureJobStatus;


public class PortalSignatureJobStatusChanged {

    private PortalSignatureJobStatus status;
    private String id;
    private XAdESReference xAdESUrl;
    private PAdESReference pAdESUrl;
    private ConfirmationReference confirmationUrl;

    public PortalSignatureJobStatusChanged(XMLPortalSignatureJobStatus status, String id, String xAdESUrl, String pAdESUrl, String confirmationUrl) {
        this.status = PortalSignatureJobStatus.fromXmlType(status);
        this.id = id;
        this.xAdESUrl = new XAdESReference(xAdESUrl);
        this.pAdESUrl = new PAdESReference(pAdESUrl);
        this.confirmationUrl = new ConfirmationReference(confirmationUrl);
    }

    public PortalSignatureJobStatus getStatus() {
        return status;
    }

    public XAdESReference getxAdESUrl() {
        return xAdESUrl;
    }

    public PAdESReference getpAdESUrl() {
        return pAdESUrl;
    }

    public ConfirmationReference getConfirmationUrl() {
        return confirmationUrl;
    }

    public String getId() {
        return id;
    }
}
