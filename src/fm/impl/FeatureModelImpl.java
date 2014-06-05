/**
 */
package fm.impl;

import java.util.List;

import ca.mcgill.sel.core.COREFeature;
import fm.Feature;
import fm.FeatureModel;
import fm.FmPackage;
import grl.GRLspec;
import grl.impl.GRLGraphImpl;

import org.eclipse.emf.ecore.EClass;

import seg.jUCMNav.strategies.util.FeatureUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class FeatureModelImpl extends GRLGraphImpl implements FeatureModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FmPackage.Literals.FEATURE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public COREFeature getGlobalRoot() {
		GRLspec grl = this.getUrndefinition().getUrnspec().getGrlspec();
		List<Feature> roots = FeatureUtil.getRootFeatures(grl);
		// only returns the first of possible many roots (URN does not constrain feature models to one root)
		if (roots.isEmpty())
			return null;
		else
			return (COREFeature) roots.get(0); 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREFeature getLocalRoot() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

} //FeatureModelImpl
