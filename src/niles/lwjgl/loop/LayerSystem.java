package niles.lwjgl.loop;

import java.util.ArrayList;

import niles.lwjgl.batch.Batch;

public class LayerSystem {
	
	private ArrayList<Layer> layers;
	
	public LayerSystem() {
		layers = new ArrayList<Layer>();
	}

	public ArrayList<Layer> getLayers() {
		return layers;
	}

	public void setLayers(ArrayList<Layer> layers) {
		this.layers = layers;
	}

}
