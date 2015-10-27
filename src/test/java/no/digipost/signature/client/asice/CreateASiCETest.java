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
package no.digipost.signature.client.asice;

import no.digipost.signature.client.TestKonfigurasjon;
import no.digipost.signature.client.core.Document;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static no.digipost.signature.client.asice.CreateASiCE.createASiCE;

public class CreateASiCETest {

    @Test
    @Ignore("Writes files to disk. Can be useful for debugging")
    public void create_asice_and_write_to_disk() throws IOException {

        Document document = Document.builder("Subject", "Message", "file.txt", "hello".getBytes())
                .fileType(Document.FileType.TXT)
                .build();
        DocumentBundle aSiCE = createASiCE(document, TestKonfigurasjon.CLIENT_KEYSTORE);

        File tempFile = File.createTempFile("test", ".zip");
        IOUtils.copy(new ByteArrayInputStream(aSiCE.getBytes()), new FileOutputStream(tempFile));
        System.out.println("Skrev zip-fil til " + tempFile.getAbsolutePath());
    }

}