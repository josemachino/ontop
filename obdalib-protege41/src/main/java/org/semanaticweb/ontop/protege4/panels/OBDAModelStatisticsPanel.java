package org.semanaticweb.ontop.protege4.panels;

/*
 * #%L
 * ontop-protege4
 * %%
 * Copyright (C) 2009 - 2013 KRDB Research Centre. Free University of Bozen Bolzano.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.HashMap;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.semanaticweb.ontop.utils.VirtualABoxStatistics;

public class OBDAModelStatisticsPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 2317777246039649415L;
	
    public OBDAModelStatisticsPanel(VirtualABoxStatistics statistics) {
        initComponents();
        initContent(statistics);
    }
    
    private void initContent(VirtualABoxStatistics statistics) {
    	
    	/* Fill the label summary value */
    	String message = "";
		try {
			int count = statistics.getTotalTriples();
			message = String.format("%s %s", count, (count == 1 ? "triple" : "triples"));
		} 
		catch (Exception e) {
			message = String.format("%s. Please try again!", e.getMessage());
		} 
    	lblSummaryValue.setText(message); 	
    	
    	/* Fill the triples summary table */
    	final HashMap<String, HashMap<String, Integer>> data = statistics.getStatistics();
    	for (String datasourceName : data.keySet()) {
    		HashMap<String, Integer> mappingStat = data.get(datasourceName);
    		
    		final int row = mappingStat.size();
    		final int col = 2;    		
    		final String[] columnNames = {"Mapping ID", "Number of Triples"};
    		
    		Object[][] rowData = new Object[row][col];
    		
    		int index = 0;
    		for (String mappingId : mappingStat.keySet()){
    			rowData[index][0] = mappingId;
    			rowData[index][1] = mappingStat.get(mappingId);
    			index++;
    		}    		
    		JTable tblTriplesCount = createStatisticTable(rowData, columnNames);
			tabDataSources.add(datasourceName, new JScrollPane(tblTriplesCount));
    	}
    }
    
    private JTable createStatisticTable(Object[][] rowData, String[] columnNames) {
    	
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

		@SuppressWarnings("serial")
		JTable table = new JTable(model) {
			// Create a model in which the cells can't be edited
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};		
		return table;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSummary = new javax.swing.JPanel();
        lblSummary = new javax.swing.JLabel();
        lblSummaryValue = new javax.swing.JLabel();
        pnlTriplesSummary = new javax.swing.JPanel();
        tabDataSources = new javax.swing.JTabbedPane();

        setFont(new java.awt.Font("Arial", 0, 18));
        setMinimumSize(new java.awt.Dimension(520, 400));
        setPreferredSize(new java.awt.Dimension(520, 400));
        setLayout(new java.awt.BorderLayout());

        pnlSummary.setMinimumSize(new java.awt.Dimension(156, 23));
        pnlSummary.setPreferredSize(new java.awt.Dimension(156, 23));
        pnlSummary.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblSummary.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblSummary.setText("Total triples produced:");
        pnlSummary.add(lblSummary);

        lblSummaryValue.setFont(new java.awt.Font("Tahoma", 1, 11));
        pnlSummary.add(lblSummaryValue);

        add(pnlSummary, java.awt.BorderLayout.NORTH);

        pnlTriplesSummary.setLayout(new java.awt.BorderLayout());

        tabDataSources.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabDataSources.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        pnlTriplesSummary.add(tabDataSources, java.awt.BorderLayout.CENTER);

        add(pnlTriplesSummary, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblSummary;
    private javax.swing.JLabel lblSummaryValue;
    private javax.swing.JPanel pnlSummary;
    private javax.swing.JPanel pnlTriplesSummary;
    private javax.swing.JTabbedPane tabDataSources;
    // End of variables declaration//GEN-END:variables
}
