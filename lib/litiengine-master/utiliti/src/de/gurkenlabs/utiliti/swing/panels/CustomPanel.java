package de.gurkenlabs.utiliti.swing.panels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import de.gurkenlabs.litiengine.environment.tilemap.ICustomProperty;
import de.gurkenlabs.litiengine.environment.tilemap.IMapObject;
import de.gurkenlabs.litiengine.environment.tilemap.MapObjectProperty;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.utiliti.UndoManager;

@SuppressWarnings("serial")
public class CustomPanel extends PropertyPanel {
  private JTable tableCustomProperties;
  private JScrollPane scrollPane;
  private DefaultTableModel model;

  public CustomPanel() {
    super("panel_customProperties");

    this.scrollPane = new JScrollPane();

    JButton buttonAdd = new JButton("+");
    buttonAdd.addActionListener(a -> model.addRow(new Object[] { "", "" }));

    JButton buttonRemove = new JButton("-");
    buttonRemove.addActionListener(a -> {
      int[] rows = tableCustomProperties.getSelectedRows();
      for (int i = 0; i < rows.length; i++) {
        model.removeRow(rows[i] - i);

      }
    });

    GroupLayout groupLayout = new GroupLayout(this);
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(groupLayout.createSequentialGroup()
            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(buttonAdd).addPreferredGap(ComponentPlacement.RELATED).addComponent(buttonRemove, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createSequentialGroup().addGap(CONTROL_MARGIN).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
            .addGap(CONTROL_MARGIN)));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)
        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(buttonRemove).addComponent(buttonAdd)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    this.tableCustomProperties = new JTable();
    this.tableCustomProperties.getTableHeader().setReorderingAllowed(false);
    this.tableCustomProperties.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.scrollPane.setViewportView(tableCustomProperties);
    this.tableCustomProperties.setModel(new DefaultTableModel(new Object[][] {}, new String[] { Resources.strings().get("panel_name"), Resources.strings().get("panel_value") }) {
      Class<?>[] columnTypes = new Class<?>[] { String.class, String.class };
      boolean[] columnEditables = new boolean[] { true, true };

      @Override
      public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
      }

      @Override
      public boolean isCellEditable(int row, int column) {
        return columnEditables[column];
      }
    });
    tableCustomProperties.getColumnModel().getColumn(0).setResizable(false);

    this.model = (DefaultTableModel) this.tableCustomProperties.getModel();
    setLayout(groupLayout);

    this.setupChangedListeners();
  }

  @Override
  protected void clearControls() {
    this.model.setRowCount(0);
  }

  @Override
  protected void setControlValues(IMapObject mapObject) {
    this.clearControls();
    if (mapObject == null || mapObject.getProperties() == null) {
      return;
    }
    for (Map.Entry<String, ICustomProperty> prop : mapObject.getProperties().entrySet()) {
      if (MapObjectProperty.isCustom(prop.getKey())) {
        this.model.addRow(new Object[] { prop.getKey(), prop.getValue().getAsString() });
      }
    }
  }

  private void setupChangedListeners() {
    this.model.addTableModelListener(e -> {
      if (getDataSource() == null || isFocussing) {
        return;
      }

      UndoManager.instance().mapObjectChanging(getDataSource());
      List<String> setProperties = new ArrayList<>();
      for (int row = 0; row < model.getRowCount(); row++) {
        String name = (String) model.getValueAt(row, 0);
        String value = (String) model.getValueAt(row, 1);
        if (name != null && value != null && !name.isEmpty() && !value.isEmpty()) {
          setProperties.add(name);
          getDataSource().setValue(name, value);
        }
      }

      getDataSource().getProperties().keySet().removeIf(p -> MapObjectProperty.isCustom(p) && !setProperties.contains(p));
      UndoManager.instance().mapObjectChanged(getDataSource());
    });
  }
}
