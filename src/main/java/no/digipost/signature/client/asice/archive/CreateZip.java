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
package no.digipost.signature.client.asice.archive;

import no.digipost.signature.client.asice.ASiCEAttachable;
import no.digipost.signature.client.core.exceptions.RuntimeIOException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZip {

    public byte[] zipIt(final List<ASiCEAttachable> files) {
        try (ByteArrayOutputStream archive = new ByteArrayOutputStream()) {
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(archive)) {
                for (ASiCEAttachable file : files) {
                    ZipEntry zipEntry = new ZipEntry(file.getFileName());
                    zipEntry.setSize(file.getBytes().length);
                    zipOutputStream.putNextEntry(zipEntry);
                    zipOutputStream.write(file.getBytes());
                    zipOutputStream.closeEntry();
                }
            }
            return archive.toByteArray();
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }

    }
}
