package sugangSincheong;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import constants.Constants.EPGangjwaSelectionPanel;
import control.CGangjwa;
import valueObject.VGangjwa;

public class PGangjwaSelectionPanel extends JTable {
	private static final long serialVersionUID = 1L;

	private Vector<VGangjwa> vGangjwas;
	private DefaultTableModel tableModel;
	
	private String hakgwaFileName;
	private PResultPanel pMiridamgiPanel;
	private PResultPanel pSincheongPanel;

	public PGangjwaSelectionPanel(ListSelectionListener listSelectionHandler) {
		Vector<String> header = new Vector<String>();
		for (EPGangjwaSelectionPanel ePGangjwaSelectionPanel: EPGangjwaSelectionPanel.values()) {
			header.addElement(ePGangjwaSelectionPanel.getText());
		}
		
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(tableModel);
	}

	public void intialize(String hakgwaFileName, PResultPanel pMiridamgiPanel, PResultPanel pSincheongPanel) {
		this.pMiridamgiPanel = pMiridamgiPanel;
		this.pSincheongPanel = pSincheongPanel;
		
		this.update(hakgwaFileName);
	}

	public void update(String hakgwaFileName) {
		this.hakgwaFileName = hakgwaFileName;
		
		CGangjwa cGangjwa = new CGangjwa();
		this.vGangjwas = cGangjwa.getData(this.hakgwaFileName);
		
		this.removeDuplicatedGangjwas(this.pMiridamgiPanel.getGangjwas());
		this.removeDuplicatedGangjwas(this.pSincheongPanel.getGangjwas());
		
		this.updateTableContents();
	}
	
	public void update() {
		this.update(this.hakgwaFileName);
	}

	public Vector<VGangjwa> removeSelectedGangjwas() {
		int[] indices = this.getSelectedRows();
		Vector<VGangjwa> vSelectedGangjwas = new Vector<VGangjwa>();
		for (int index=indices.length-1; index>=0; index--) {
			vSelectedGangjwas.add(this.vGangjwas.get(index));
			this.vGangjwas.remove(index);
		}
		this.updateTableContents();
		return vSelectedGangjwas;
	}
	
	
	private void removeDuplicatedGangjwas(Vector<VGangjwa> vSelectedGangjwas) {
		for (VGangjwa vSelectedGangjwa : vSelectedGangjwas) {
			for (int i = this.vGangjwas.size() - 1; i >= 0; i--) {
				if (this.vGangjwas.get(i).getId().equals(vSelectedGangjwa.getId())) {
					this.vGangjwas.remove(i);
					break;
				}
			}
		}
	}
	
	private void updateTableContents() {
		this.tableModel.setRowCount(0);
		for (VGangjwa vGangjwa : this.vGangjwas) {
			Vector<String> row = new Vector<String>();
			row.add(vGangjwa.getId());
			row.add(vGangjwa.getName());
			row.add(vGangjwa.getLecturer());
			row.add(vGangjwa.getCredit());
			row.add(vGangjwa.getTime());
			this.tableModel.addRow(row);
		}
		if (this.vGangjwas.size() > 0) {
			this.setRowSelectionInterval(0, 0);
		}
	}
}
