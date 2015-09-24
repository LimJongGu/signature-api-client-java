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
package no.digipost.signering.client.domain;

public class Signeringsoppdrag {

    private String signatar;
    private Dokument dokument;

    private Signeringsoppdrag(final String signatar, final Dokument dokument) {
        this.signatar = signatar;
        this.dokument = dokument;
    }

    public String getSignatar() {
        return signatar;
    }

    public Dokument getDokument() {
        return dokument;
    }

    public static Builder builder(final String signatar, final Dokument dokument) {
        return new Builder(signatar, dokument);
    }

    public static class Builder {

        private final Signeringsoppdrag target;
        private boolean built = false;

        public Builder(final String signatar, final Dokument dokument) {
            target = new Signeringsoppdrag(signatar, dokument);
        }

        public Signeringsoppdrag build() {
            if (built) throw new IllegalStateException("Can't build twice");
            built = true;
            return target;
        }
    }
}
