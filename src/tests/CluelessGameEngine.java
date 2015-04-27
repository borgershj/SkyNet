package tests;

import java.awt.Color;

import networkSimulation.CellLinkOverlay;
import networkSimulation.NodeOverlay;
import nullSAF.core.GameEngine;
import nullSAF.core.IClickable;
import nullSAF.maps.BlancOverlay;
import nullSAF.maps.BotOverlay;
import nullSAF.maps.GameEnginePerformanceDisplay;
import nullSAF.maps.GridRasterOverlay;
import nullSAF.maps.IScrollable;
import nullSAF.maps.Map;

public class CluelessGameEngine extends GameEngine {

	NodeOverlay neuronOverlay;
	CellLinkOverlay connectionsOverlay;
	BotOverlay ul;
	Map main, observed;
	int marge = 15;
	int miniMapSize;
	int size;

	class LayerMap extends Map {

		LayerMap() {
			super("Layer", CluelessGameEngine.this.main, Map.RIGHT, CluelessGameEngine.this.marge,
					CluelessGameEngine.this.miniMapSize, CluelessGameEngine.this.miniMapSize);
			addOverlay(new BlancOverlay(Color.WHITE)); // otherwise the background will not be wiped
			// FIXME: show layer connections too
			// addLayer(new ConnectionMapLayer(-1));
		}
	}

	class MainMap extends Map implements IScrollable, IClickable {

		public MainMap(String name, int leftInPixels, int topInPixels, int widthInPixels, int heigthInPixels) {
			super(name, leftInPixels, topInPixels, widthInPixels, heigthInPixels);
			addOverlay(new BlancOverlay(Color.WHITE)); // otherwise the background will not be wiped
			addOverlay(new GridRasterOverlay(10));
			addOverlay(CluelessGameEngine.this.connectionsOverlay);
			addOverlay(CluelessGameEngine.this.neuronOverlay);
		}
	}

	public CluelessGameEngine(int neuronrate) {
		this("My map", // name of the map,
				800, // canvas height in pixels
				100f, // mapsize in micrometer (1x = 1 neuron size)
				neuronrate * 3, // tickrate = desired neuronrate N times neurons for randomness
				4, // desired drawing rate (paint frequency of canvas)
				20, // gridnrs for observable
				1000); // observable update rate
	}

	private CluelessGameEngine(String mapname, int canvasHeightInPixels, float mapSizeInMeters, int tickRate,
			int frameRate, int observableGridNrs, int observableIntervalInMillis) {
		super(canvasHeightInPixels, mapSizeInMeters, tickRate, tickRate / frameRate, 1f, observableGridNrs,
				observableIntervalInMillis);
		this.size = (canvasHeightInPixels - 50);
		this.miniMapSize = (int) (this.size / 3.2);

		this.connectionsOverlay = new CellLinkOverlay(-1); // re-render connections never
		this.neuronOverlay = new NodeOverlay(1000 / frameRate); // update of overlays (not painting!) is at frameRate

		new GameEnginePerformanceDisplay(null, this.marge, this.marge);

		// create the big map
		this.main = new MainMap(mapname, this.marge, 2 * this.marge, this.size, this.size);

		// create mini map
		this.observed = new LayerMap();
	}
}
