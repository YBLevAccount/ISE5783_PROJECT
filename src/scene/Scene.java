/**
 * 
 */
package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * this class represents the collection of lights and geometries
 * 
 * @author Shulman and Yonatan
 *
 */
public class Scene {
	/** 
	 * the name of the scene
	 */
	public final String name;
	/**
	 * the background color of the scene
	 */
	public Color background = Color.BLACK;
	/**
	 * the ambient light of the scene
	 */
	public AmbientLight ambientLight = AmbientLight.NONE;
	/**
	 * the geometries in the scene
	 */
	public Geometries geometries = new Geometries();

	public Scene(String name) {
		this.name = name;
	}

	/**
	 * setter for the background
	 * 
	 * @param background the background to set
	 * @return this object
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * setter for the ambient light
	 * 
	 * @param ambientLight the ambientLight to set
	 * @return this object
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * setter for geometries
	 * 
	 * @param geometries the geometries to set
	 * @return this object
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}

}
