package seg.jUCMNav.importexport;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;

import seg.jUCMNav.Messages;
import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
import ucm.map.ComponentRef;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNlink;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentKind;
import urncore.IURNConnection;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Responsibility;

/**
 * Export DXL Script to be run in Telelogic DOORS
 * 
 * @author Yongdae Kim, jkealey, jfroy
 * 
 */
public class ExportDXL implements IURNExport {

    private FileOutputStream fos = null;
    public static final String QUOTES = "\""; //$NON-NLS-1$
    public static final String QUOTES_COMMA = "\", "; //$NON-NLS-1$
    public static final String COMMA = ","; //$NON-NLS-1$
    public static final String QUOTES_DOUBLE = QUOTES + QUOTES;
    public static final String END_ELEM = " )\n"; //$NON-NLS-1$
    public static final String QUOTES_END_ELEM = "\" )\n"; //$NON-NLS-1$

    private String filename;
    /**
     * Not used.
     */
    public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
        // TODO Auto-generated method stub

    }

    /**
     * Export the URNspec to the given filename in DXL format.
     */
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {
        this.filename = filename;
        try {
            fos = new FileOutputStream(filename);

            writeHeader(urn);
            writeDevices(urn);
            writeActors(urn);
            writeComponents(urn);
            writeResponsibilities(urn);
            writeIntentionalElements(urn);
            writeElementLinks(urn);
            writeGrlDiagrams(urn, filename);
            writeMaps(urn, filename);
            writeScenarios(urn);
            writeStrategies(urn);
            writeUrnLinks(urn);
            writeFooter();

        } catch (Exception e) {
            throw new InvocationTargetException(e);
        } finally {
            // close the stream
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Write the string to the file output stream.
     * 
     * @param s
     *            the string to write
     * @throws IOException
     */
    public void write(String s) throws IOException {
        if (s != null && s.length() > 0) {
            fos.write(s.getBytes());
        }
    }

    /**
     * Writes the information about actors such as id, name, type, and description
     * 
     * @param urn
     *            URNspec
     * @throws IOException
     * 
     */
    protected void writeActors(URNspec urn) throws IOException {
        for (Iterator iter = urn.getGrlspec().getActors().iterator(); iter.hasNext();) {
            Actor actor = (Actor)iter.next();
            write("actor( "); //$NON-NLS-1$

            // ID
            write(QUOTES);
            write(actor.getId());
            write(QUOTES_COMMA);

            // Name
            write(QUOTES);
            write(actor.getName());
            write(QUOTES_COMMA);

            // Description
            write(QUOTES);
            write(actor.getDescription());
            write(QUOTES);
            
            write(END_ELEM);
        }
        write("\n"); //$NON-NLS-1$
    }

    /**
     * Writes the information about actor references, such as id, fx, fy, width, height, definition id, name, and parent actor
     * of stubs.
     * 
     * @param graph
     *            GRLGraph
     * @throws IOException
     */
    protected void writeActorRef(GRLGraph graph) throws IOException {
        for (Iterator iter1 = graph.getContRefs().iterator(); iter1.hasNext();) {
            ActorRef actorRef = (ActorRef) iter1.next();
            write("   actorRef( "); //$NON-NLS-1$

            // ID
            write(QUOTES);
            write(actorRef.getId());
            write(QUOTES_COMMA);

            // Fx
            String compX = "" + actorRef.getX(); //$NON-NLS-1$
            write(compX);
            write(COMMA);

            // Fy
            String compY = "" + actorRef.getY(); //$NON-NLS-1$
            write(compY);
            write(COMMA);

            // Width
            String width = "" + actorRef.getWidth(); //$NON-NLS-1$
            write(width);
            write(COMMA);

            // Height
            String height = "" + actorRef.getHeight(); //$NON-NLS-1$
            write(height);
            write(COMMA);

            // DefinitionID
            Actor actor = (Actor) actorRef.getContDef();
            write(QUOTES);
            write(actor.getId());
            write(QUOTES_COMMA);

            // Name
            write(QUOTES);
            write(actor.getName());
            write(QUOTES_COMMA);

            // Parent Actor
            write(QUOTES);
            if (actorRef.getParent() != null) {
                Actor parActor = (Actor) actorRef.getParent().getContDef();
                write(parActor.getName());
            }
            write(QUOTES_END_ELEM);
        }
    }
    
    /**
     * Writes the information about components such as id, name, type, and description of the components
     * 
     * @param urn
     *            URNspec
     * @throws IOException
     * 
     */
    protected void writeComponents(URNspec urn) throws IOException {
        for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) {
            Component element = (Component)iter.next();
            write("component( "); //$NON-NLS-1$

            // ID
            write(QUOTES);
            write(element.getId());
            write(QUOTES_COMMA);

            // Name
            write(QUOTES);
            write(element.getName());
            write(QUOTES_COMMA);

            ComponentKind kind = ((Component)element).getKind();
            String kindString = kind.getName();
            write(QUOTES);
            write(kindString);
            write(QUOTES_COMMA);

            // Description
            write(QUOTES);
            write(element.getDescription());
            write(QUOTES_COMMA);

            // DeviceID
            // TODO: Implement
            write(QUOTES_DOUBLE);

            write(END_ELEM);
        }
        write("\n"); //$NON-NLS-1$
    }

    /**
     * Writes the information about component references, such as id, fx, fy, width, height, anchored, definition id, name, component role, and parent component
     * of stubs.
     * 
     * @param ucmmap
     *            UCMmap
     * @throws IOException
     */
    protected void writeCompRef(UCMmap ucmmap) throws IOException {
        for (Iterator iter1 = ucmmap.getContRefs().iterator(); iter1.hasNext();) {
            ComponentRef compRef = (ComponentRef) iter1.next();
            write("   compRef( "); //$NON-NLS-1$

            // ID
            write(QUOTES);
            write(compRef.getId());
            write(QUOTES_COMMA);

            // Fx
            String compX = "" + compRef.getX(); //$NON-NLS-1$
            write(compX);
            write(COMMA);

            // Fy
            String compY = "" + compRef.getY(); //$NON-NLS-1$
            write(compY);
            write(COMMA);

            // Width
            String width = "" + compRef.getWidth(); //$NON-NLS-1$
            write(width);
            write(COMMA);

            // Height
            String height = "" + compRef.getHeight(); //$NON-NLS-1$
            write(height);
            write(COMMA);

            // Anchored
            write(QUOTES);
            if (compRef.isAnchored()) {
                write("yes"); //$NON-NLS-1$
            } else {
                write("no"); //$NON-NLS-1$
            }
            write(QUOTES_COMMA);

            // DefinitionID
            Component component = (Component) compRef.getContDef();
            write(QUOTES);
            write(component.getId());
            write(QUOTES_COMMA);

            // Name
            write(QUOTES);
            write(component.getName());
            write(QUOTES_COMMA);

            // ComponentRole
            write(QUOTES);
            write(compRef.getRole());
            write(QUOTES_COMMA);
            // ParentComponent
            write(QUOTES);
            if (compRef.getParent() != null) {
                Component parComp = (Component) compRef.getParent().getContDef();
                write(parComp.getName());
            }
            write(QUOTES_END_ELEM);
        }
    }

    /**
     * Writes the information about the devices used.
     * 
     * @param urn
     *            urn
     * @throws IOException
     * 
     */
    protected void writeDevices(URNspec urn) throws IOException {
        // TODO Device ... not implemented yet

        // device( "d1COMMAprocessor1COMMACOMMA0" )

        // for (Iterator deviceIter = urn.getUcmspec().getResources().iterator(); deviceIter.hasNext();) {
        // write("device( ");
        // GeneralResource gr = (GeneralResource) deviceIter.next();
        //
        // write(" )\n");
        // }

    }

    /**
     * Writes the information about evaluations for a grl stategy.
     * 
     * @param strategy
     *            EvaluationStrategy
     * @throws IOException
     */
    protected void writeEvaluations(EvaluationStrategy strategy) throws IOException {
        EvaluationStrategyManager.getInstance(false).setStrategy(strategy);
        EvaluationStrategyManager.getInstance(false).calculateEvaluation();

        for (Iterator iter = strategy.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            IntentionalElement elem = (IntentionalElement) iter.next();
            

            Evaluation eval = EvaluationStrategyManager.getInstance(false).getEvaluationObject(elem);
            
            // evaluation
            if (eval.getStrategies() != null) {
                write( "   defined( "); //$NON-NLS-1$
            } else {
                write("   evaluation( ");//$NON-NLS-1$
            }
            
            //Element Id
            write(QUOTES);
            write(elem.getId());
            write(QUOTES_COMMA);            
            
            //Element Name
//            write(QUOTES);
//            write(elem.getName());
//            write("(E)"); //$NON-NLS-1$
//            write(QUOTES_COMMA);
            
            String temp = QUOTES + eval.getEvaluation() + QUOTES_END_ELEM;
            write(temp);
        }
        
        //Write evaluation for actors
        for (Iterator iter = strategy.getGrlspec().getActors().iterator(); iter.hasNext();) {
            Actor actor = (Actor) iter.next();
            
            int evaluation = EvaluationStrategyManager.getInstance(false).getActorEvaluation(actor);
            
            write("   evaluation( ");//$NON-NLS-1$
            
            write(QUOTES);
            write(actor.getId());
            write(QUOTES_COMMA);
            
            String temp = QUOTES + evaluation + QUOTES_END_ELEM;
            write(temp);
        }
        
    }
    /**
     * Writes the DXL footer.
     * 
     * @throws IOException
     */
    protected void writeFooter() throws IOException {
        write("endImport\n"); //$NON-NLS-1$
    }

    /**
     * Writes the information about grl graph, such as id, name.
     * 
     * @param urn
     *            urn
     * @param filename
     *            Path
     * @throws IOException
     */
    protected void writeGrlDiagrams(URNspec urn, String filename) throws IOException {
        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram element = (IURNDiagram) iter.next();
            if (element instanceof GRLGraph) {
                GRLGraph grlgraph = (GRLGraph) element;
                // map
                write("grldiagram( "); //$NON-NLS-1$

                // id
                String graphID = grlgraph.getId();
                write(QUOTES);
                write(graphID);
                write(QUOTES_COMMA);

                // Name
                String graphName = grlgraph.getName();
                write(QUOTES);
                write(graphName);
                write(QUOTES_COMMA);

                // GraphFileName
                int firstIndex = filename.lastIndexOf("\\") + 1; //$NON-NLS-1$
                int lastIndex = filename.lastIndexOf("."); //$NON-NLS-1$

                String bitmapFilename = filename.substring(firstIndex, lastIndex);
                bitmapFilename = bitmapFilename.concat("-GRLGraph"); //$NON-NLS-1$
                bitmapFilename = bitmapFilename.concat(graphID);
                bitmapFilename = bitmapFilename.concat("-"); //$NON-NLS-1$
                bitmapFilename = bitmapFilename.concat(ExportWizard.cleanFileName(graphName));
                bitmapFilename = bitmapFilename.concat(".bmp"); //$NON-NLS-1$

                write(QUOTES);
                write(bitmapFilename);
                write(QUOTES_COMMA);

                // Diagram Title
                write(QUOTES);
                write(graphName);
                write(QUOTES_COMMA);

                // Description
                write(QUOTES);
                write(grlgraph.getDescription());
                write(QUOTES_END_ELEM);
                
                //Write the element in the graph
                writeActorRef(grlgraph);
                writeGrlNodes(grlgraph);
                
                //Write the links in the diagram
                //writeLinkRef(grlgraph);
                
            }
        }
        write("\n\n"); //$NON-NLS-1$

    }
    
    /**
     * Writes the DXL header.
     * 
     * @param urn
     *            urn
     * @throws IOException
     */
    protected void writeHeader(URNspec urn) throws IOException {
        write("#include \"addins/URN/lib/URNUtilities.dxl\"\n"); //$NON-NLS-1$
        write("pragma runLim, 0\n\n"); //$NON-NLS-1$
        //Set the name of the import model
        // GraphFileName
        int firstIndex = filename.lastIndexOf("\\") + 1; //$NON-NLS-1$
        int lastIndex = filename.lastIndexOf("."); //$NON-NLS-1$

        String name = filename.substring(firstIndex, lastIndex);

        write("beginImport( " + QUOTES + name + QUOTES + " )\n\n"); // write URN name //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Writes the information about ElementLinks
     * 
     * @param urn
     *            urn
     * @throws IOException
     */
    protected void writeElementLinks(URNspec urn) throws IOException {
        for (Iterator iter = urn.getGrlspec().getLinks().iterator(); iter.hasNext();) {
            //Create different type of dxl based on the type of link
            ElementLink link = (ElementLink) iter.next();
            if (link instanceof Contribution){
                write("contribution("); //$NON-NLS-1$
            } else {
                write("elementlink( "); //$NON-NLS-1$
            }
            
            // ID
            write(QUOTES);
            write(link.getId());
            write(QUOTES_COMMA);

            // Name
            write(QUOTES);
            write(link.getName());
            write(QUOTES_COMMA);
            
            if (link instanceof Decomposition)
            {
                write(QUOTES);
                write("decomposition"); //$NON-NLS-1$
                write(QUOTES_COMMA);
            } else if (link instanceof Dependency){
                write(QUOTES);
                write("dependency"); //$NON-NLS-1$
                write(QUOTES_COMMA);
            } else if (link instanceof Contribution){
                write(QUOTES);
                write("contribution"); //$NON-NLS-1$
                write(QUOTES_COMMA);
                
                // Contribution Type
                write(QUOTES);
                write(((Contribution)link).getContribution().getName());
                write(QUOTES_COMMA);
                
                //Correlation
                write(QUOTES);
                if (((Contribution)link).isCorrelation())
                {
                    write("1"); //$NON-NLS-1$
                } else {
                    write("0"); //$NON-NLS-1$
                }
                write(QUOTES_COMMA);
            } else {
                throw new IOException(Messages.getString("ExportDXL.InvalidElementLinkType")); //$NON-NLS-1$
            }
            
            // Description
            write(QUOTES);
            write(link.getDescription());
            write(QUOTES_COMMA);
            
            // Source
            write(QUOTES);
            write(link.getSrc().getId());
            write(QUOTES_COMMA);
            
            // Destination
            write(QUOTES);
            write(link.getDest().getId());
            write(QUOTES_END_ELEM);
        }
        write("\n"); //$NON-NLS-1$    
    }

    /**
     * Writes the information about intentional elements
     * 
     * @param urn
     *            urn
     * @throws IOException
     */
    protected void writeIntentionalElements(URNspec urn) throws IOException {
        for (Iterator iter = urn.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            IntentionalElement element = (IntentionalElement) iter.next();
            write("intentionalelement( "); //$NON-NLS-1$

            // ID
            write(QUOTES);
            write(element.getId());
            write(QUOTES_COMMA);

            // Name
            write(QUOTES);
            write(element.getName());
            write(QUOTES_COMMA);

            // Type 
            IntentionalElementType type = element.getType();
            write(QUOTES);
            write(type.getName());
            write(QUOTES_COMMA);
            
            // Description
            write(QUOTES);
            write(element.getDescription());
            write(QUOTES_COMMA);

            // ProcessorDemand
            DecompositionType decompType = element.getDecompositionType();
            write(QUOTES);
            write(decompType.getName());
            write(QUOTES_END_ELEM);
        }
        write("\n"); //$NON-NLS-1$

    }

    /**
     * Writes the information about grl nodes (intentional element references or belief), 
     * such as id, fx, fy, enclosing actor, definition id, name, description, priority and criticality
     * 
     * @param graph
     *            GRLGraph
     * @throws IOException
     */
    protected void writeGrlNodes(GRLGraph graph) throws IOException {
        for (Iterator iter1 = graph.getNodes().iterator(); iter1.hasNext();) {
            IURNNode specNode = (IURNNode) iter1.next();
            if (specNode instanceof IntentionalElementRef) {
                IntentionalElementRef elementRef = (IntentionalElementRef) specNode;
                write("   intentionalElementRef( "); //$NON-NLS-1$

                // ID
                write(QUOTES);
                write(elementRef.getId());
                write(QUOTES_COMMA);

                // Fx
                String respX = "" + elementRef.getX(); //$NON-NLS-1$
                write(respX);
                write(COMMA);

                // Fy
                String respY = "" + elementRef.getY(); //$NON-NLS-1$
                write(respY);
                write(COMMA);

                // EnclosingActor
                ActorRef actorRef = (ActorRef) elementRef.getContRef();
                write(QUOTES);
                if (actorRef != null) {
                    write(actorRef.getId());
                }
                write(QUOTES_COMMA);

                // DefinitionID
                IntentionalElement intElement = elementRef.getDef();
                write(QUOTES);
                write(intElement.getId());
                write(QUOTES_COMMA);

                // Name
                write(QUOTES);
                write(intElement.getName());
                write(QUOTES_COMMA);

                // Description
                write(QUOTES);
                write(intElement.getDescription());
                write(QUOTES_COMMA);
                
                //Priority
                write(QUOTES);
                write(elementRef.getPriority().getName());
                write(QUOTES_COMMA);
                
                //Criticality
                write(QUOTES);
                write(elementRef.getCriticality().getName());
                write(QUOTES_END_ELEM);
            } else if (specNode instanceof Belief){
                Belief belief = (Belief) specNode;
                write("   belief( "); //$NON-NLS-1$

                // ID
                write(QUOTES);
                write(belief.getId());
                write(QUOTES_COMMA);

                // Fx
                String respX = "" + belief.getX(); //$NON-NLS-1$
                write(respX);
                write(COMMA);

                // Fy
                String respY = "" + belief.getY(); //$NON-NLS-1$
                write(respY);
                write(COMMA);

                // EnclosingActor
                ActorRef actorRef = (ActorRef) belief.getContRef();
                write(QUOTES);
                if (actorRef != null) {
                    write(actorRef.getId());
                }
                write(QUOTES_COMMA);

                //Link to intentional element (id)
                write(QUOTES);
                if (belief.getSucc().size() > 0){
                    IURNConnection connection = (IURNConnection)belief.getSucc().get(0);
                    if (connection != null && (connection instanceof BeliefLink)){
                        IntentionalElementRef beliefref = (IntentionalElementRef)connection.getTarget();
                        write(beliefref.getId());
                    }
                }
                write(QUOTES_COMMA);
                
                // Name
                write(QUOTES);
                write(belief.getName());
                write(QUOTES_COMMA);

                // Description
                write(QUOTES);
                write(belief.getDescription());
                write(QUOTES_COMMA);
                
                //Author
                write(QUOTES);
                write(belief.getAuthor());
                write(QUOTES_END_ELEM);
            }
        }
    }
    
    /**
     * Writes the information about Link References
     * 
     * @param graph
     *            GRLGraph
     * @throws IOException
     */
//    protected void writeLinkRef(GRLGraph graph) throws IOException {
//        for (Iterator iter = graph.getConnections().iterator(); iter.hasNext();) {
//            IURNConnection connection = (IURNConnection) iter.next();
//            if (connection instanceof LinkRef){
//                write("   linkref( "); //$NON-NLS-1$
//                LinkRef ref = (LinkRef)connection;
//                //Source
//                write(QUOTES);
//                write(((URNmodelElement)ref.getSource()).getId());
//                write(QUOTES_COMMA);         
//                
//                //Target
//                write(QUOTES);
//                write(((URNmodelElement)ref.getTarget()).getId());
//                write(QUOTES_COMMA);  
//                
//                //Element Link
//                write(QUOTES);
//                write(ref.getLink().getId());
//                write(QUOTES_END_ELEM);                  
//            }
//        }
//    }
    
    /**
     * Writes the information about maps, such as id, name, map file name, map title, and description of maps.
     * 
     * Calls the methods writeRespRef(ucmmap, fos), writeStub(ucmmap, fos), writeCompRef(ucmmap, fos) to write required informations about the responsibility
     * refereneces, stubs, and component references of each map.
     * 
     * @param urn
     *            urn
     * @param filename
     *            Path
     * @throws IOException
     */
    protected void writeMaps(URNspec urn, String filename) throws IOException {
        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram element = (IURNDiagram) iter.next();
            if (element instanceof UCMmap) {
                UCMmap ucmmap = (UCMmap) element;
                // map
                write("map( "); //$NON-NLS-1$

                // id
                String mapID = ucmmap.getId();
                write(QUOTES);
                write(mapID);
                write(QUOTES_COMMA);

                // Name
                String mapName = ucmmap.getName();
                write(QUOTES);
                write(mapName);
                write(QUOTES_COMMA);

                // MapFileName
                int firstIndex = filename.lastIndexOf("\\") + 1; //$NON-NLS-1$
                int lastIndex = filename.lastIndexOf("."); //$NON-NLS-1$

                String bitmapFilename = filename.substring(firstIndex, lastIndex);
                bitmapFilename = bitmapFilename.concat("-Map"); //$NON-NLS-1$
                bitmapFilename = bitmapFilename.concat(mapID);
                bitmapFilename = bitmapFilename.concat("-"); //$NON-NLS-1$
                bitmapFilename = bitmapFilename.concat(ExportWizard.cleanFileName(mapName));
                bitmapFilename = bitmapFilename.concat(".bmp"); //$NON-NLS-1$

                write(QUOTES);
                write(bitmapFilename);
                write(QUOTES_COMMA);

                // MapTitle
                write(QUOTES);
                write(mapName);
                write(QUOTES_COMMA);

                // Description
                write(QUOTES);
                write(ucmmap.getDescription());
                write(QUOTES_END_ELEM);

                // Nodes (respRef, stub)
                // respRef
                writeRespRef(ucmmap);
                // stub
                writeStub(ucmmap);
                // compRef
                writeCompRef(ucmmap);
            }
        }
        write("\n\n"); //$NON-NLS-1$

    }

    /**
     * Writes the information about responsibilities, such as id, name, description, and processor demand of the responsibilities
     * 
     * @param urn
     *            urn
     * @throws IOException
     */
    protected void writeResponsibilities(URNspec urn) throws IOException {
        for (Iterator iter = urn.getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
            Responsibility resp = (Responsibility) iter.next();
            write("responsibility( "); //$NON-NLS-1$

            // ID
            write(QUOTES);
            write(resp.getId());
            write(QUOTES_COMMA);

            // Name
            write(QUOTES);
            write(resp.getName());
            write(QUOTES_COMMA);

            // Description
            write(QUOTES);
            write(resp.getDescription());
            write(QUOTES_COMMA);

            // ProcessorDemand
            String size = "" + resp.getDemands().size(); //$NON-NLS-1$
            write(QUOTES);
            write(size);
            write(QUOTES_END_ELEM);
        }
        write("\n"); //$NON-NLS-1$

    }

    /**
     * Writes the information about responsibility references, such as id, fx, fy, enclosing component, definition id, name, and description of
     * responsibilities.
     * 
     * @param ucmmap
     *            ucmmap
     * @throws IOException
     */
    protected void writeRespRef(UCMmap ucmmap) throws IOException {
        for (Iterator iter1 = ucmmap.getNodes().iterator(); iter1.hasNext();) {
            IURNNode specNode = (IURNNode) iter1.next();
            if (specNode instanceof RespRef) {
                RespRef respRef = (RespRef) specNode;
                write("   respRef( "); //$NON-NLS-1$

                // ID
                write(QUOTES);
                write(respRef.getId());
                write(QUOTES_COMMA);

                // Fx
                String respX = "" + respRef.getX(); //$NON-NLS-1$
                write(respX);
                write(COMMA);

                // Fy
                String respY = "" + respRef.getY(); //$NON-NLS-1$
                write(respY);
                write(COMMA);

                // EnclosingComponent
                ComponentRef compRef = (ComponentRef) respRef.getContRef();
                write(QUOTES);
                if (compRef != null) {
                    write(compRef.getId());
                }
                write(QUOTES_COMMA);

                // DefinitionID
                Responsibility res = respRef.getRespDef();
                write(QUOTES);
                write(res.getId());
                write(QUOTES_COMMA);

                // Name
                write(QUOTES);
                write(res.getName());
                write(QUOTES_COMMA);

                // Description
                write(QUOTES);
                write(res.getDescription());
                write(QUOTES_END_ELEM);
            }
        }
    }

    /**
     * Writes the scenario information.
     * 
     * @param urn
     *            urn
     * @throws IOException
     */
    protected void writeScenarios(URNspec urn) throws IOException {
        // TODO Scenarios not yet implemented.

        // tab indentation
        // scenarioGroup( "SetupCOMMAscenarioGroup-SetupCOMMA" )
        // scenario( "InstallPASCOMMAscenarioGroup-Setup_scenario-InstallPASCOMMA" )
        // seq( "seq0COMMAscenarioGroup-Setup_scenario-InstallPAS" )
        // doElement( "h0COMMAInstallCOMMAStartCOMMACOMMACellPhoneClientsCOMMAcr0COMMACOMMAseq0" )
        // doElement( "h59COMMAInstallPASCOMMAConnect_StartCOMMACOMMACellPhoneClientsCOMMAcr7COMMACOMMAseq0" )
        // doElement( "h61COMMAInstallPASforCellPhoneClientCOMMARespCOMMACOMMAPASCOMMAcr6COMMACOMMAseq0" )
        // doElement( "h60COMMAPASInstalledCOMMAConnect_EndCOMMACOMMACellPhoneClientsCOMMAcr7COMMACOMMAseq0" )
        // doElement( "h34COMMACOMMAEnd_PointCOMMACOMMACellPhoneClientsCOMMAcr0COMMACOMMAseq0" )
        // scenario( "RemovePASCOMMAscenarioGroup-Setup_scenario-RemovePASCOMMA" )
        // seq( "seq1COMMAscenarioGroup-Setup_scenario-RemovePAS" )
        // doElement( "h31COMMASelectScenarioCOMMAStartCOMMACOMMACellPhoneClientsCOMMAcr0COMMACOMMAseq1" )
        // condition( "h20COMMACONDremovePASCOMMAbv1COMMAseq1" )
        // doElement( "h64COMMARemovePASCOMMAConnect_StartCOMMACOMMACellPhoneClientsCOMMAcr8COMMACOMMAseq1" )
        // doElement( "h68COMMARemovePASforCellPhoneClientCOMMARespCOMMACOMMAPASCOMMAcr9COMMACOMMAseq1" )
        // doElement( "h71COMMADeletePhotosCOMMARespCOMMACOMMAPASCOMMAcr9COMMACOMMAseq1" )
        // doElement( "h66COMMAPASRemovedCOMMAConnect_EndCOMMACOMMACellPhoneClientsCOMMAcr8COMMACOMMAseq1" )
        // doElement( "h2COMMARemovedCOMMAEnd_PointCOMMACOMMACellPhoneClientsCOMMAcr0COMMACOMMAseq1" )
    }

    /**
     * Writes the strategies information.
     * 
     * @param urn
     *            urnspec
     * @throws IOException
     */
    protected void writeStrategies(URNspec urn) throws IOException {
        for (Iterator iter = urn.getGrlspec().getStrategies().iterator(); iter.hasNext();){
            EvaluationStrategy strategy = (EvaluationStrategy)iter.next();
            write("strategy( "); //$NON-NLS-1$
            
            // ID
            write(QUOTES);
            write(strategy.getId());
            write(QUOTES_COMMA);    
            
            //Name
            write(QUOTES);
            write(strategy.getName());
            write(QUOTES_COMMA);
            
            //Description
            write(QUOTES);
            write(strategy.getDescription());
            write(QUOTES_COMMA);
            
            //Author
            write(QUOTES);
            write(strategy.getAuthor());
            write(QUOTES_END_ELEM);
            
            writeEvaluations(strategy);
            write("\n"); //$NON-NLS-1$
        }
    }
    /**
     * Writes the information about stubs, such as id, fx, fy, name, stub type, and plugin ids of stubs.
     * 
     * @param ucmmap
     *            ucmmap
     * @throws IOException
     */
    protected void writeStub(UCMmap ucmmap) throws IOException {
        for (Iterator iter1 = ucmmap.getNodes().iterator(); iter1.hasNext();) {
            IURNNode specNode = (IURNNode) iter1.next();
            if (specNode instanceof Stub) {
                Stub stub = (Stub) specNode;
                write("   stub( "); //$NON-NLS-1$

                // ID
                write(QUOTES);
                write(stub.getId());
                write(QUOTES_COMMA);

                // Fx
                String stubX = "" + stub.getX(); //$NON-NLS-1$
                write(stubX);
                write(COMMA);

                // Fy
                String stubY = "" + stub.getY(); //$NON-NLS-1$
                write(stubY);
                write(COMMA);

                // Name
                write(QUOTES);
                write(stub.getName());
                write(QUOTES_COMMA);

                // StubType
                write(QUOTES);
                if (stub.isDynamic()) {
                    write("dynamic"); //$NON-NLS-1$
                } else {
                    write("static"); //$NON-NLS-1$
                }
                write(QUOTES_COMMA);

                // PluginIDs
                write(QUOTES);
                for (Iterator iter2 = stub.getBindings().iterator(); iter2.hasNext();) {
                    PluginBinding binding = (PluginBinding) iter2.next();
                    UCMmap map = binding.getPlugin();
                    write(map.getId());
                    write(";"); //$NON-NLS-1$
                }
                write(QUOTES_END_ELEM);
            }
        }
    }
    
    /**
     * Writes the information about urnlinks
     * 
     * @param urnspec
     *            urnspec
     * @throws IOException
     */
    protected void writeUrnLinks(URNspec urnspec) throws IOException {
        for (Iterator iter = urnspec.getUrnLinks().iterator(); iter.hasNext();) {
            URNlink links = (URNlink) iter.next();
            write("urnlink("); //$NON-NLS-1$
            
            //From
            write(QUOTES);
            write(links.getFromElem().getId());
            write(QUOTES_COMMA);             
            
            //TO
            write(QUOTES);
            write(links.getToElem().getId());
            write(QUOTES_END_ELEM);             
        }
        write("\n"); //$NON-NLS-1$
    }
}
