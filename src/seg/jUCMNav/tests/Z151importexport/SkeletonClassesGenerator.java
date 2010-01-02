package seg.jUCMNav.tests.Z151importexport;

import junit.framework.TestCase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class SkeletonClassesGenerator extends TestCase {
	static String jUCMNavHome = "C:\\Users\\Yan\\workspace\\seg.jUCMNav\\";
	static String generatedHome = jUCMNavHome + "src\\seg\\jUCMNav\\tests\\Z151importexport\\generatedSkeletonClasses\\";

	protected static String getHomeDirPath() throws IOException {
		File currentDir = new File(".");
		System.out.println("Finding seg.jUCMNav project root: " + currentDir.getCanonicalPath());
		try {
			return currentDir.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("The current directory doesn't exist");
		}
	}

	/* This test case is to generate skeleton classes for unmarshalling */
	public void testUNMarshalGenerator() {
		try {
			jUCMNavHome = getHomeDirPath() + "\\";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String srcDirPath = jUCMNavHome + "\\src\\grl\\";
		String dstDirPath = generatedHome + "\\unmarshal\\grl\\";
		String myPackageName = "grl";
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName);

		srcDirPath = jUCMNavHome + "\\src\\ucm\\";
		dstDirPath = generatedHome + "\\unmarshal\\ucm\\";
		myPackageName = "ucm";
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName);

		srcDirPath = jUCMNavHome + "\\src\\ucm\\map\\";
		dstDirPath = generatedHome + "\\unmarshal\\ucm\\map\\";
		myPackageName = "ucm.map";
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName);

		srcDirPath = jUCMNavHome + "\\src\\ucm\\performance\\";
		dstDirPath = generatedHome + "\\unmarshal\\ucm\\performance\\";
		myPackageName = "ucm.performance";
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName);

		srcDirPath = jUCMNavHome + "\\src\\ucm\\scenario\\";
		dstDirPath = generatedHome + "\\unmarshal\\ucm\\scenario\\";
		myPackageName = "ucm.scenario";
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName);

		srcDirPath = jUCMNavHome + "\\src\\urn\\";
		dstDirPath = generatedHome + "\\unmarshal\\urn\\";
		myPackageName = "urn";
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName);

		srcDirPath = jUCMNavHome + "\\src\\urncore\\";
		dstDirPath = generatedHome + "\\unmarshal\\urncore\\";
		myPackageName = "urncore";
		generateUNMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName);
	}

	private void generateUNMarshalClasses(String myPackageName, String srcDirPath, String dstDirPath, String srcPrefix, String dstPrefix) {
		File dstDir = new File(dstDirPath);
		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}
		File f = new File(srcDirPath);
		OutputStream os = null;
		PrintWriter pw = null;
		if (!srcPrefix.equals(""))
			srcPrefix = srcPrefix + ".";
		if (!dstPrefix.equals(""))
			dstPrefix = dstPrefix + ".";
		for (File file : f.listFiles()) {
			try {
				if (file.getName().endsWith("java")) {
					String className = file.getName().substring(0, file.getName().length() - 5);
					File g = new File(dstDirPath + className + "UMHandler.java");
					os = new PrintStream(g);
					pw = new PrintWriter(os);

					pw.println("package seg.jUCMNav.importexport.z151.unmarshal;");
					pw.println();

					String z151 = jUCMNavHome + "\\src\\seg\\jUCMNav\\importexport\\z151\\xml\\Z151.xsd";
					pw.println(this.getXMLdef(z151, className));

					Class c = Class.forName(myPackageName + ".impl." + className + "Impl");

					pw.println("import seg.jUCMNav.importexport.z151.generated." + className + ";");
					pw.println();

					Class sc = c.getSuperclass();
					if (sc != org.eclipse.emf.ecore.EObject.class)
						pw.println("public class " + className + "UMHandler " + "extends " + sc.getSimpleName() + "UMHandler " + "{");
					else
						pw.println("Public Class " + className + "UMHandler " + "extends " + "UMHandler {");
					pw.println("	public Object handle(Object o, Object target, boolean isFullConstruction) {");
					pw.println("		" + srcPrefix + className + " elemZ = (" + srcPrefix + className + ") o;");
					pw.println("		String objId = elemZ.getId();");
					pw.println("		" + dstPrefix + className + " elem = (" + dstPrefix + className + ") getObject(objId, target, " + dstPrefix + className + ".class);");
					pw.println("		if (isFullConstruction) {");

					if (sc != org.eclipse.emf.ecore.EObject.class)
						pw.println("			elem = (" + dstPrefix + className + ") super.handle(elemZ, elem, isFullConstruction);");

					for (Method method : c.getMethods()) {
						String name = method.getName();
						if (name.startsWith("set")) {
							pw.println("			elem." + name + "();");
						}
					}
					pw.println();

					for (Method method : c.getMethods()) {
						String name = method.getName();
						if (name.startsWith("get")) {
							pw.println("			elem." + name + "();");
						}
					}
					pw.println("		}");
					pw.println("		return elem;");
					pw.println("	}");
					pw.println("}");
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
		try {
			jUCMNavHome = getHomeDirPath() + "\\";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String myPackageName = "seg.jUCMNav.importexport.z151.generated";
		String srcDirPath = jUCMNavHome + "\\src\\seg\\jUCMNav\\importexport\\z151\\generated\\";
		String dstDirPath = generatedHome + "\\marshal\\";
		generateMarshalClasses(myPackageName, srcDirPath, dstDirPath, "", myPackageName);
	}

	private void generateMarshalClasses(String myPackageName, String srcDirPath, String dstDirPath, String srcPrefix, String dstPrefix) {
		File dstDir = new File(dstDirPath);
		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}

		File f = new File(srcDirPath);
		OutputStream os = null;
		PrintWriter pw = null;
		if (!srcPrefix.equals(""))
			srcPrefix = srcPrefix + ".";
		if (!dstPrefix.equals(""))
			dstPrefix = dstPrefix + ".";
		for (File file : f.listFiles()) {
			try {
				if (file.getName().endsWith("java")) {
					String className = file.getName().substring(0, file.getName().length() - 5);
					File g = new File(dstDirPath + className + "MHandler.java");
					os = new PrintStream(g);
					pw = new PrintWriter(os);

					pw.println("package seg.jUCMNav.importexport.z151.marshal;");
					pw.println();

					String z151 = jUCMNavHome + "\\src\\seg\\jUCMNav\\importexport\\z151\\xml\\Z151.xsd";
					pw.println(this.getXMLdef(z151, className));

					pw.println("import seg.jUCMNav.importexport.z151.generated." + className + ";");
					pw.println();
					Class c = Class.forName(myPackageName + "." + className);

					Class sc = c.getSuperclass();
					if (sc != java.lang.Object.class)
						pw.println("public class " + className + "MHandler " + "extends " + sc.getSimpleName() + "MHandler " + "{");
					else
						pw.println("Public Class " + className + "MHandler " + "extends " + "MHandler {");
					pw.println("	public Object handle(Object o, Object target, boolean isFullConstruction) {");
					pw.println("		" + this.getPrefix(className) + "." + className + " elem = (" + getPrefix(className) + "." + className + ") o;");
					pw.println("		String objId = elem.getId();");
					pw.println("		" + dstPrefix + className + " elemZ = (" + dstPrefix + className + ") getObject(objId, target, \"create" + className + "\");");
					pw.println("		if (isFullConstruction) {");
					if (sc != java.lang.Object.class)
						pw.println("			elemZ = (" + dstPrefix + className + ") super.handle(elem, elemZ, isFullConstruction);");

					for (Method method : c.getMethods()) {
						String name = method.getName();

						if (name.startsWith("set")) {
							pw.println("			elemZ." + name + "();");
						}
					}
					pw.println();

					for (Method method : c.getMethods()) {
						String name = method.getName();
						if (name.startsWith("get")) {
							pw.println("			elemZ." + name + "();");
						}
					}
					pw.println("		}");
					pw.println("		return elemZ;");
					pw.println("	}");
					pw.println("}");
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
		String[] suggestedPackage = { "grl", "urn", "urncore", "ucm", "ucm.map", "ucm.performance", "ucm.scenario" };
		for (String packageName : suggestedPackage) {
			try {
				Class c = Class.forName(packageName + ".impl." + className + "Impl");
				return packageName;
			} catch (ClassNotFoundException e) {
			}
		}
		return "";
	}

	private String getXMLdef(String z151, String className) {
		BufferedReader br = null;
		String line1 = "";
		String line2 = "";
		String line3 = "";
		String line4 = "";
		String ret = "";
		try {
			br = new BufferedReader(new FileReader(z151));

			String inputLine;
			while (null != (inputLine = br.readLine())) {
				// inputLine = inputLine.trim();
				line4 = line3;
				line3 = line2;
				line2 = line1;
				line1 = inputLine;

				if (inputLine.contains("name=\"" + className + "\"")) {
					ret = "//" + line4 + "\n" + "//" + line3 + "\n" + "//" + line2 + "\n" + "//" + line1 + "\n";
					while (null != (inputLine = br.readLine())) {
						// inputLine = inputLine.trim();
						if (!inputLine.contains("</xsd:complexType>"))
							ret = ret + "//" + inputLine + "\n";
						else
							break;
					}
					ret = ret + "//" + "  </xsd:complexType>\n";
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