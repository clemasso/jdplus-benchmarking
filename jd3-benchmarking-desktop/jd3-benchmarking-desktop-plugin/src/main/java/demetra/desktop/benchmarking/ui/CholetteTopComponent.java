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
package demetra.desktop.benchmarking.ui;

import demetra.desktop.benchmarking.documents.CholetteDocumentManager;
import demetra.desktop.ui.processing.Ts2ProcessingViewer;
import demetra.desktop.workspace.DocumentUIServices;
import demetra.desktop.workspace.WorkspaceFactory;
import demetra.desktop.workspace.WorkspaceItem;
import demetra.desktop.workspace.ui.WorkspaceTs2TopComponent;
import jdplus.benchmarking.univariate.CholetteDocument;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//demetra.desktop.benchmarking.ui//Cholette//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "CholetteTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ec.nbdemetra.benchmarking.CholetteTopComponent")
@ActionReference(path = "Menu/Statistical methods/Benchmarking", position = 2000)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_CholetteAction")
@Messages({
    "CTL_CholetteAction=Cholette",
    "CTL_CholetteTopComponent=Cholette Window",
    "HINT_CholetteTopComponent=This is a Cholette window"
})
public final class CholetteTopComponent extends WorkspaceTs2TopComponent<CholetteDocument> {

    private final ExplorerManager mgr = new ExplorerManager();

    private static CholetteDocumentManager manager() {
        return WorkspaceFactory.getInstance().getManager(CholetteDocumentManager.class);
    }

    public CholetteTopComponent() {
        this(null);
    }

    public CholetteTopComponent(WorkspaceItem<CholetteDocument> doc) {
        super(doc);
        initComponents();
        setToolTipText(Bundle.CTL_CholetteTopComponent());
    }

    @Override
    protected Ts2ProcessingViewer initViewer() {
        //       node=new InternalNode();
        return Ts2ProcessingViewer.create(this.getElement(), DocumentUIServices.forDocument(CholetteDocument.class), "Low-freq series", "High-freq series");
    }

    @Override
    public WorkspaceItem<CholetteDocument> newDocument() {
        return manager().create(WorkspaceFactory.getInstance().getActiveWorkspace());
    }

    //    private UniCholetteSpecification getSpecification(){
//        return getDocument().getElement().getSpecification();
//    }
//
//    private void setSpecification(UniCholetteSpecification spec){
//        getDocument().getElement().setSpecification(spec);
//        updateView();
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String getContextPath() {
        return CholetteDocumentManager.CONTEXTPATH; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return mgr;
    }

//    @Override
//    public Node getNode() {
//        return node;
//    }
//    class InternalNode extends AbstractNode {
//
//        InternalNode() {
//            super(Children.LEAF);
//            setDisplayName("Cholette method");
//        }
//
//        @Override
//        protected Sheet createSheet() {
//            Sheet sheet = super.createSheet();
//            Sheet.Set benchmarking = Sheet.createPropertiesSet();
//            benchmarking.setName("Benchmarking");
//            benchmarking.setDisplayName("Benchmarking");
//            Node.Property<Double> rho = new Node.Property(Double.class) {
//                @Override
//                public boolean canRead() {
//                    return true;
//                }
//
//                @Override
//                public Object getValue() throws IllegalAccessException, InvocationTargetException {
//                    UniCholetteSpecification spec=getSpecification();
//                    return spec.getRho();
//                }
//
//                @Override
//                public boolean canWrite() {
//                    return true;
//                }
//
//                @Override
//                public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//                    CholetteDocument document = getDocument().getElement();
//                    UniCholetteSpecification nspec = document.getSpecification().clone();
//                    nspec.setRho((Double)t);
//                    document.setSpecification(nspec);
//                    updateView();
//                }
//            };
//            rho.setName("Rho");
//            benchmarking.put(rho);
//            Node.Property<Double> lambda = new Node.Property(Double.class) {
//                @Override
//                public boolean canRead() {
//                    return true;
//                }
//
//                @Override
//                public Object getValue() throws IllegalAccessException, InvocationTargetException {
//                    UniCholetteSpecification spec=getSpecification();
//                    return spec.getLambda();
//                }
//
//                @Override
//                public boolean canWrite() {
//                    return true;
//                }
//
//                @Override
//                public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//                    CholetteDocument document = getDocument().getElement();
//                    UniCholetteSpecification nspec = document.getSpecification().clone();
//                    nspec.setLambda((Double)t);
//                    setSpecification(nspec);
//                }
//            };
//            lambda.setName("Lambda");
//            benchmarking.put(lambda);
//            sheet.put(benchmarking);
//
//            Sheet.Set agg = Sheet.createPropertiesSet();
//            agg.setName("Aggregation");
//            agg.setDisplayName("Aggregation");
////            Node.Property<AggregationType> type = new Node.Property(AggregationType.class) {
////                @Override
////                public boolean canRead() {
////                    return true;
////                }
////
////                @Override
////                public Object getValue() throws IllegalAccessException, InvocationTargetException {
////                    UniCholetteSpecification spec=getSpecification();
////                    return AggregationType.Sum;
////                }
////
////                @Override
////                public boolean canWrite() {
////                    return true;
////                }
////
////                @Override
////                public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
////                    CholetteDocument document = getDocument().getElement();
////                    UniCholetteSpecification nspec = document.getSpecification().clone();
////                    // to do
////                    document.setSpecification(nspec);
////                    updateView();
////                }
////            };
////            type.setName("Aggregation");
////            agg.put(type);
//            Node.Property<TsFrequency> freq = new Node.Property(TsFrequency.class) {
//                @Override
//                public boolean canRead() {
//                    return true;
//                }
//
//                @Override
//                public Object getValue() throws IllegalAccessException, InvocationTargetException {
//                    UniCholetteSpecification spec=getSpecification();
//                    return spec.getAggregationFrequency();
//                }
//
//                @Override
//                public boolean canWrite() {
//                    return true;
//                }
//
//                @Override
//                public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//                    CholetteDocument document = getDocument().getElement();
//                    UniCholetteSpecification nspec = document.getSpecification().clone();
//                    nspec.setAggregationFrequency((TsFrequency)t);
//                    document.setSpecification(nspec);
//                    updateView();
//                }
//            };
//            freq.setName("Frequency");
//            agg.put(freq);
// 
//            sheet.put(agg);
//            return sheet;
//        }
//    }
}
