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

import no.digipost.signature.client.core.internal.Cancellable;

public class PortalJobResponse implements Cancellable {

    private final long signatureJobId;
    private final CancellationUrl cancellationUrl;

    public PortalJobResponse(long signatureJobId, CancellationUrl cancellationUrl) {
        this.signatureJobId = signatureJobId;
        this.cancellationUrl = cancellationUrl;
    }

    public long getSignatureJobId() {
        return signatureJobId;
    }

    @Override
    public CancellationUrl getCancellationUrl() {
        return cancellationUrl;
    }
}
