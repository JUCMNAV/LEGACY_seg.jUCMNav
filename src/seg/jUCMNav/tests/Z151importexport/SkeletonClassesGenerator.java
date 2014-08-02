package seg.jUCMNav.tests.Z151importexport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import junit.framework.TestCase;

public class SkeletonClassesGenerator extends TestCase {
	static String jUCMNavHome = getHomeDirPath() + "\\"; //$NON-NLS-1$
	static String generatedHome = jUCMNavHome + "\\src\\seg\\jUCMNav\\tests\\Z151importexport\\generatedSkeletonClasses\\"; //$NON-NLS-1$
	
	protected static String getHomeDirPath(){
		File currentDir = new File(".");		 //$NON-NLS-1$
		try {
			System.out.println("Finding seg.jUCMNav project root: " + currentDir.getCanonicalPath()); //$NON-NLS-1$
			return currentDir.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return ""; //$NON-NLS-1$
	}

	/* This test case is to generate skeleton classes for unmarshalling */
	public void testUNMarshalGenerator() {
		//jUCMNavHome = getHomeDirPath() + "\\";
		//generatedHome = jUCMNavHome + "\\src\\seg\\jUCMNav\\tests\\Z151importexport\\generatedSkeletonClasses\\";
		String srcDirPath = jUCMNavHome + "\\src\\grl\\"; //$NON-NLS-1$
		String dstDirPath = generatedHome + "\\unmarshal\\grl\\"; //$NON-NLS-1$
		String myPackageName = "grl"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

		srcDirPath = jUCMNavHome + "\\src\\ucm\\"; //$NON-NLS-1$
		dstDirPath = generatedHome + "\\unmarshal\\ucm\\"; //$NON-NLS-1$
		myPackageName = "ucm"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

		srcDirPath = jUCMNavHome + "\\src\\ucm\\map\\"; //$NON-NLS-1$
		dstDirPath = generatedHome + "\\unmarshal\\ucm\\map\\"; //$NON-NLS-1$
		myPackageName = "ucm.map"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

		srcDirPath = jUCMNavHome + "\\src\\ucm\\performance\\"; //$NON-NLS-1$
		dstDirPath = generatedHome + "\\unmarshal\\ucm\\performance\\"; //$NON-NLS-1$
		myPackageName = "ucm.performance"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

		srcDirPath = jUCMNavHome + "\\src\\ucm\\scenario\\"; //$NON-NLS-1$
		dstDirPath = generatedHome + "\\unmarshal\\ucm\\scenario\\"; //$NON-NLS-1$
		myPackageName = "ucm.scenario"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

		srcDirPath = jUCMNavHome + "\\src\\urn\\"; //$NON-NLS-1$
		dstDirPath = generatedHome + "\\unmarshal\\urn\\"; //$NON-NLS-1$
		myPackageName = "urn"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

		srcDirPath = jUCMNavHome + "\\src\\urncore\\"; //$NON-NLS-1$
		dstDirPath = generatedHome + "\\unmarshal\\urncore\\"; //$NON-NLS-1$
		myPackageName = "urncore"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

		srcDirPath = jUCMNavHome + "\\src\\asd\\"; //$NON-NLS-1$
		dstDirPath = generatedHome + "\\unmarshal\\asd\\"; //$NON-NLS-1$
		myPackageName = "asd"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

		srcDirPath = jUCMNavHome + "\\src\\fm\\"; //$NON-NLS-1$
		dstDirPath = generatedHome + "\\unmarshal\\fm\\"; //$NON-NLS-1$
		myPackageName = "asd"; //$NON-NLS-1$
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$

	}

	private void generateUNMarshalClasses(String myPackageName, String srcDirPath, String dstDirPath, String srcPrefix, String dstPrefix) {
		File dstDir = new File(dstDirPath);
		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}
		File f = new File(srcDirPath);
		OutputStream os = null;
		PrintWriter pw = null;
		if (!srcPrefix.equals("")) //$NON-NLS-1$
			srcPrefix = srcPrefix + "."; //$NON-NLS-1$
		if (!dstPrefix.equals("")) //$NON-NLS-1$
			dstPrefix = dstPrefix + "."; //$NON-NLS-1$
		for (File file : f.listFiles()) {
			try {
				if (file.getName().endsWith("java")) { //$NON-NLS-1$
					String className = file.getName().substring(0, file.getName().length() - 5);
					File g = new File(dstDirPath + className + "UMHandler.java"); //$NON-NLS-1$
					os = new PrintStream(g);
					pw = new PrintWriter(os);

					pw.println("package seg.jUCMNav.importexport.z151.unmarshal;"); //$NON-NLS-1$
					pw.println();

					String z151 = jUCMNavHome + "\\src\\seg\\jUCMNav\\importexport\\z151\\xml\\Z151.xsd"; //$NON-NLS-1$
					pw.println(this.getXMLdef(z151, className));

					Class c = Class.forName(myPackageName + ".impl." + className + "Impl"); //$NON-NLS-1$ //$NON-NLS-2$

					pw.println("import seg.jUCMNav.importexport.z151.generated." + className + ";"); //$NON-NLS-1$ //$NON-NLS-2$
					pw.println();

					Class sc = c.getSuperclass();
					if (sc != org.eclipse.emf.ecore.EObject.class)
						pw.println("public class " + className + "UMHandler " + "extends " + sc.getSimpleName() + "UMHandler " + "{"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
					else
						pw.println("Public Class " + className + "UMHandler " + "extends " + "UMHandler {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					pw.println("	public Object handle(Object o, Object target, boolean isFullConstruction) {"); //$NON-NLS-1$
					pw.println("		" + srcPrefix + className + " elemZ = (" + srcPrefix + className + ") o;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					pw.println("		String objId = elemZ.getId();"); //$NON-NLS-1$
					pw.println("		" + dstPrefix + className + " elem = (" + dstPrefix + className + ") getObject(objId, target, " + dstPrefix + className + ".class);"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					pw.println("		if (isFullConstruction) {"); //$NON-NLS-1$

					if (sc != org.eclipse.emf.ecore.EObject.class)
						pw.println("			elem = (" + dstPrefix + className + ") super.handle(elemZ, elem, isFullConstruction);"); //$NON-NLS-1$ //$NON-NLS-2$

					for (Method method : c.getMethods()) {
						String name = method.getName();
						if (name.startsWith("set")) { //$NON-NLS-1$
							pw.println("			elem." + name + "();"); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
					pw.println();

					for (Method method : c.getMethods()) {
						String name = method.getName();
						if (name.startsWith("get")) { //$NON-NLS-1$
							pw.println("			elem." + name + "();"); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
					pw.println("		}"); //$NON-NLS-1$
					pw.println("		return elem;"); //$NON-NLS-1$
					pw.println("	}"); //$NON-NLS-1$
					pw.println("}"); //$NON-NLS-1$
					pw.flush();
					pw.close();
					os.close();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// pw.close();
				// try {
				// os.close();
				// } catch (IOException ioe) {
				// ioe.printStackTrace();
				// }
			}
		}
	}

	/* This test case is to generate skeleton classes for marshalling */
	public void testMarshalGenerator() {
		//jUCMNavHome = getHomeDirPath() + "\\";
		//generatedHome = jUCMNavHome + "\\src\\seg\\jUCMNav\\tests\\Z151importexport\\generatedSkeletonClasses\\";
		String myPackageName = "seg.jUCMNav.importexport.z151.generated"; //$NON-NLS-1$
		String srcDirPath = jUCMNavHome + "\\src\\seg\\jUCMNav\\importexport\\z151\\generated\\"; //$NON-NLS-1$
		String dstDirPath = generatedHome + "\\marshal\\"; //$NON-NLS-1$
		generateMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName); //$NON-NLS-1$
	}

	private void generateMarshalClasses(String myPackageName, String srcDirPath, String dstDirPath, String srcPrefix, String dstPrefix) {
		File dstDir = new File(dstDirPath);
		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}

		File f = new File(srcDirPath);
		OutputStream os = null;
		PrintWriter pw = null;
		if (!srcPrefix.equals("")) //$NON-NLS-1$
			srcPrefix = srcPrefix + "."; //$NON-NLS-1$
		if (!dstPrefix.equals("")) //$NON-NLS-1$
			dstPrefix = dstPrefix + "."; //$NON-NLS-1$
		for (File file : f.listFiles()) {
			try {
				if (file.getName().endsWith("java")) { //$NON-NLS-1$
					String className = file.getName().substring(0, file.getName().length() - 5);
					File g = new File(dstDirPath + className + "MHandler.java"); //$NON-NLS-1$
					os = new PrintStream(g);
					pw = new PrintWriter(os);

					pw.println("package seg.jUCMNav.importexport.z151.marshal;"); //$NON-NLS-1$
					pw.println();

					String z151 = jUCMNavHome + "\\src\\seg\\jUCMNav\\importexport\\z151\\xml\\Z151.xsd"; //$NON-NLS-1$
					pw.println(this.getXMLdef(z151, className));

					pw.println("import seg.jUCMNav.importexport.z151.generated." + className + ";"); //$NON-NLS-1$ //$NON-NLS-2$
					pw.println();
					Class c = Class.forName(myPackageName + "." + className); //$NON-NLS-1$

					Class sc = c.getSuperclass();
					if (sc != java.lang.Object.class)
						pw.println("public class " + className + "MHandler " + "extends " + sc.getSimpleName() + "MHandler " + "{"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
					else
						pw.println("Public Class " + className + "MHandler " + "extends " + "MHandler {"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					pw.println("	public Object handle(Object o, Object target, boolean isFullConstruction) {"); //$NON-NLS-1$
					pw.println("		" + this.getPrefix(className) + "." + className + " elem = (" + getPrefix(className) + "." + className + ") o;"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
					pw.println("		String objId = elem.getId();"); //$NON-NLS-1$
					pw.println("		" + dstPrefix + className + " elemZ = (" + dstPrefix + className + ") getObject(objId, target, \"create" + className + "\");"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					pw.println("		if (isFullConstruction) {"); //$NON-NLS-1$
					if (sc != java.lang.Object.class)
						pw.println("			elemZ = (" + dstPrefix + className + ") super.handle(elem, elemZ, isFullConstruction);"); //$NON-NLS-1$ //$NON-NLS-2$

					for (Method method : c.getMethods()) {
						String name = method.getName();

						if (name.startsWith("set")) { //$NON-NLS-1$
							pw.println("			elemZ." + name + "();"); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
					pw.println();

					for (Method method : c.getMethods()) {
						String name = method.getName();
						if (name.startsWith("get")) { //$NON-NLS-1$
							pw.println("			elemZ." + name + "();"); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
					pw.println("		}"); //$NON-NLS-1$
					pw.println("		return elemZ;"); //$NON-NLS-1$
					pw.println("	}"); //$NON-NLS-1$
					pw.println("}"); //$NON-NLS-1$
					pw.close();
					os.close();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// pw.close();
				// try {
				// os.close();
				// } catch (IOException ioe) {
				// ioe.printStackTrace();
				// }
			}
		}
	}

	private String getPrefix(String className) {
		String[] suggestedPackage = { "grl", "urn", "urncore", "ucm", "ucm.map", "ucm.performance", "ucm.scenario", "asd", "fm" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
		for (String packageName : suggestedPackage) {
			try {
				Class c = Class.forName(packageName + ".impl." + className + "Impl"); //$NON-NLS-1$ //$NON-NLS-2$
				return packageName;
			} catch (ClassNotFoundException e) {
			}
		}
		return ""; //$NON-NLS-1$
	}

	private String getXMLdef(String z151, String className) {
		BufferedReader br = null;
		String line1 = ""; //$NON-NLS-1$
		String line2 = ""; //$NON-NLS-1$
		String line3 = ""; //$NON-NLS-1$
		String line4 = ""; //$NON-NLS-1$
		String ret = ""; //$NON-NLS-1$
		try {
			br = new BufferedReader(new FileReader(z151));

			String inputLine;
			while (null != (inputLine = br.readLine())) {
				// inputLine = inputLine.trim();
				line4 = line3;
				line3 = line2;
				line2 = line1;
				line1 = inputLine;

				if (inputLine.contains("name=\"" + className + "\"")) { //$NON-NLS-1$ //$NON-NLS-2$
					ret = "//" + line4 + "\n" + "//" + line3 + "\n" + "//" + line2 + "\n" + "//" + line1 + "\n"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
					while (null != (inputLine = br.readLine())) {
						// inputLine = inputLine.trim();
						if (!inputLine.contains("</xsd:complexType>")) //$NON-NLS-1$
							ret = ret + "//" + inputLine + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
						else
							break;
					}
					ret = ret + "//" + "  </xsd:complexType>\n"; //$NON-NLS-1$ //$NON-NLS-2$
					return ret;
				}
			}
			return ret;

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return ret;
	}

}