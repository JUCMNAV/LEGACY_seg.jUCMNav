package seg.jUCMNav.importexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
import ucm.map.ComponentRef;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentKind;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Responsibility;

/**
 * Export DXL Script to be run in Telelogic DOORS
 * 
 * @author Yongdae Kim, jkealey
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

    /**
     * Not used.
     */
    public void export(URNspec urn, FileOutputStream fos) throws InvocationTargetException {
        // TODO Auto-generated method stub

    }

    /**
     * Export the URNspec to the given filename in DXL format.
     */
    public void export(URNspec urn, String filename) throws InvocationTargetException {
        try {
            fos = new FileOutputStream(filename);

            writeHeader(urn);
            writeDevices(urn);
            writeComponents(urn);
            writeResponsibilities(urn);
            writeMaps(urn, filename);
            writeScenarios(urn);
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
     * Writes the information about components, such as id, name, type, and description of the components
     * 
     * @param urn
     *            URNspec
     * @throws IOException
     * 
     */
    protected void writeComponents(URNspec urn) throws IOException {
        for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) {
            Component component = (Component) iter.next();
            write("component( "); //$NON-NLS-1$

            // ID
            write(QUOTES);
            write(component.getId());
            write(QUOTES_COMMA);

            // Name
            write(QUOTES);
            write(component.getName());
            write(QUOTES_COMMA);

            // Type
            ComponentKind kind = component.getKind();
            String kindString = kind.getName();
            write(QUOTES);
            write(kindString);
            write(QUOTES_COMMA);

            // Description
            write(QUOTES);
            write(component.getDescription());
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

            // ConponentRole
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
     * Writes the DXL footer.
     * 
     * @throws IOException
     */
    protected void writeFooter() throws IOException {
        write("endImport\n"); //$NON-NLS-1$
    }

    /**
     * Writes the DXL header.
     * 
     * @param urn
     *            urn
     * @throws IOException
     */
    protected void writeHeader(URNspec urn) throws IOException {
        write("#include \"addins/UCM/lib/UCMUtilities.dxl\"\n"); //$NON-NLS-1$
        write("pragma runLim, 0\n\n"); //$NON-NLS-1$
        write("beginImport( " + QUOTES + urn.getName() + QUOTES + " )\n\n"); // write URN name //$NON-NLS-1$ //$NON-NLS-2$
    }

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
                int lastIndex = filename.indexOf("."); //$NON-NLS-1$

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
                write(respRef.getDescription());
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
}
