/*
 * Copyright 2013 National Bank of Belgium
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package jdplus.benchmarking.desktop.plugin.benchmarking.documents;

import jdplus.toolkit.base.api.DemetraVersion;
import jdplus.toolkit.desktop.plugin.workspace.AbstractFileItemRepository;
import jdplus.toolkit.desktop.plugin.workspace.WorkspaceItem;
import jdplus.toolkit.desktop.plugin.workspace.WorkspaceItemRepository;
import jdplus.toolkit.base.tsp.TsMeta;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import jdplus.benchmarking.base.core.benchmarking.univariate.GRPDocument;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author palatej
 */
@ServiceProvider(service = WorkspaceItemRepository.class)
public final class GrpDocFileRepository extends AbstractFileItemRepository< GRPDocument > {

    @Override
    public boolean load(WorkspaceItem<GRPDocument> item) {
        return loadFile(item, (GRPDocument o) -> {
            item.setElement(o);
            item.resetDirty();
        });
    }

    @Override
    public boolean save(WorkspaceItem<GRPDocument> doc, DemetraVersion version) {
        GRPDocument element = doc.getElement();
       
        Map<String, String> meta=new HashMap<>(element.getMetadata());
        TsMeta.TIMESTAMP.store(meta, LocalDateTime.now(Clock.systemDefaultZone()));
        element.updateMetadata(meta);
        return storeFile(doc, element, version, doc::resetDirty);
    }

    @Override
    public boolean delete(WorkspaceItem<GRPDocument> doc) {
        return deleteFile(doc);
    }

    @Override
    public Class<GRPDocument> getSupportedType() {
        return GRPDocument.class;
    }

}
