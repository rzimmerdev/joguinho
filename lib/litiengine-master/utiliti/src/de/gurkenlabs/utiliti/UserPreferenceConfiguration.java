package de.gurkenlabs.utiliti;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.gurkenlabs.litiengine.configuration.ConfigurationGroup;
import de.gurkenlabs.litiengine.configuration.ConfigurationGroupInfo;
import de.gurkenlabs.litiengine.util.ColorHelper;

@ConfigurationGroupInfo(prefix = "user_")
public class UserPreferenceConfiguration extends ConfigurationGroup {
  private float zoom;
  private boolean showGrid;
  private boolean snapPixels;
  private boolean snapGrid;
  private boolean renderBoundingBoxes;
  private boolean renderCustomMapObjects;
  private boolean compressFile;
  private boolean syncMaps;
  private int frameState;
  private int gridWidth;
  private int gridHeight;
  private int mainSplitter;
  private int selectionEditSplitter;
  private int mapPanelSplitter;
  private int bottomSplitter;
  private int assetsSplitter;
  private int width;
  private int height;

  private float gridLineWidth;

  private String lastGameFile;
  private String[] lastOpenedFiles;
  private String gridColor;

  public UserPreferenceConfiguration() {
    this.zoom = 1.0f;
    this.showGrid = true;
    this.snapPixels = true;
    this.snapGrid = true;
    this.renderBoundingBoxes = true;
    this.lastOpenedFiles = new String[10];
    this.compressFile = false;
    this.gridWidth = 16;
    this.gridHeight = 16;
    this.gridLineWidth = 1.0f;
    this.gridColor = ColorHelper.encode(new Color(255, 255, 255, 65));
  }

  public void addOpenedFile(String str) {
    // ensure max 10 elements
    List<String> newFiles = new ArrayList<>();
    for (int i = 0; i <= this.lastOpenedFiles.length; i++) {
      newFiles.add(null);
    }

    // make space for the new element and clear all duplicates
    for (int i = 1; i < this.lastOpenedFiles.length; i++) {
      if (this.lastOpenedFiles[i - 1] != null && this.lastOpenedFiles[i - 1].equals(str)) {
        continue;
      }

      if (this.lastOpenedFiles[i - 1] == null || this.lastOpenedFiles[i - 1].equals("null")) {
        newFiles.add(i, null);
      } else {
        newFiles.add(i, this.lastOpenedFiles[i - 1]);
      }
    }

    // add the new element
    newFiles.add(0, str);
    newFiles.removeAll(Collections.singleton(null));
    // clear array
    this.lastOpenedFiles = new String[10];

    // fill array
    for (int i = 0; i < newFiles.size(); i++) {
      this.lastOpenedFiles[i] = newFiles.get(i);
    }

  }

  public float getZoom() {
    return this.zoom;
  }

  public void setZoom(float zoom) {
    this.zoom = zoom;
  }

  public boolean isShowGrid() {
    return this.showGrid;
  }

  public void setShowGrid(boolean showGrid) {
    this.showGrid = showGrid;
  }

  public boolean isSnapGrid() {
    return this.snapGrid;
  }

  public void setSnapGrid(boolean snapGrid) {
    this.snapGrid = snapGrid;
  }

  public boolean isRenderBoundingBoxes() {
    return this.renderBoundingBoxes;
  }

  public void setRenderBoundingBoxes(boolean renderBoundingBoxes) {
    this.renderBoundingBoxes = renderBoundingBoxes;
  }

  public boolean isRenderCustomMapObjects() {
    return this.renderCustomMapObjects;
  }

  public void setRenderCustomMapObjects(boolean renderCustomMapObjects) {
    this.renderCustomMapObjects = renderCustomMapObjects;
  }

  public String getLastGameFile() {
    return this.lastGameFile;
  }

  public void setLastGameFile(String lastGameFile) {
    this.lastGameFile = lastGameFile;
  }

  public String[] getLastOpenedFiles() {
    return this.lastOpenedFiles;
  }

  public void setLastOpenedFiles(String[] lastOpenedFiles) {
    this.lastOpenedFiles = lastOpenedFiles;
  }

  public boolean isCompressFile() {
    return compressFile;
  }

  public void setCompressFile(boolean compressFile) {
    this.compressFile = compressFile;
  }

  public int getGridWidth() {
    return this.gridWidth;
  }

  public int getGridHeight() {
    return this.gridHeight;
  }

  public float getGridLineWidth() {
    return this.gridLineWidth;
  }

  public String getGridColor() {
    return this.gridColor;
  }

  public void setGridLineWidth(float gridLineWidth) {
    this.gridLineWidth = gridLineWidth;
  }

  public void setGridColor(String gridColor) {
    this.gridColor = gridColor;
  }

  public void setGridWidth(int gridwidth) {
    this.gridWidth = gridwidth;
  }

  public void setGridHeight(int gridHeight) {
    this.gridHeight = gridHeight;
  }

  public boolean isSyncMaps() {
    return syncMaps;
  }

  public void setSyncMaps(boolean syncMaps) {
    this.syncMaps = syncMaps;
  }

  public int getMainSplitterPosition() {
    return mainSplitter;
  }

  public void setMainSplitter(int mainSplitter) {
    this.mainSplitter = mainSplitter;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getSelectionEditSplitter() {
    return selectionEditSplitter;
  }

  public void setSelectionEditSplitter(int selectionEditSplitter) {
    this.selectionEditSplitter = selectionEditSplitter;
  }

  public int getMapPanelSplitter() {
    return mapPanelSplitter;
  }

  public void setMapPanelSplitter(int mapPanelSplitter) {
    this.mapPanelSplitter = mapPanelSplitter;
  }

  public int getBottomSplitter() {
    return bottomSplitter;
  }

  public void setBottomSplitter(int bottomSplitter) {
    this.bottomSplitter = bottomSplitter;
  }

  public int getAssetsSplitter() {
    return assetsSplitter;
  }

  public void setAssetsSplitter(int assetsSplitter) {
    this.assetsSplitter = assetsSplitter;
  }

  public int getFrameState() {
    return frameState;
  }

  public void setFrameState(int frameState) {
    this.frameState = frameState;
  }

  public boolean isSnapPixels() {
    return snapPixels;
  }

  public void setSnapPixels(boolean snapPixels) {
    this.snapPixels = snapPixels;
  }
}