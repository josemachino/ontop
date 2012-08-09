/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DependencyTabPane.java
 *
 * Created on Aug 12, 2009, 10:18:26 AM
 */
package inf.unibz.it.obda.gui.swing.dependencies.panel;

import java.awt.Dimension;

import inf.unibz.it.obda.api.controller.APIController;
import inf.unibz.it.obda.gui.swing.constraints.panel.CheckConstraintsPanel;
import inf.unibz.it.obda.gui.swing.constraints.panel.ForeignKeyConstraintsPanel;
import inf.unibz.it.obda.gui.swing.constraints.panel.PrimaryKeyConstraintPanel;
import inf.unibz.it.obda.gui.swing.constraints.panel.UniquenessConstraintPanel;

import javax.swing.JFrame;

/**
 *The tab pane which is added to the OBDA tab of Protege4
 *
 * @author Manfred Gerstgrasser
 * 		   KRDB Research Center, Free University of Bolzano/Bozen, Italy 
 */
public class DependencyTabPane extends javax.swing.JPanel {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4286552797252744764L;
//	private javax.swing.JPanel jPanelFunctionalDepEditor;
    private javax.swing.JPanel jPanelFunctionalDepTree;
//    private javax.swing.JPanel jPanelInclusionDepEditor;
    private javax.swing.JPanel jPanelInclusionDepTree;
//    private javax.swing.JPanel jPanelDisjoinednessEditor;
    private javax.swing.JPanel jPanelDisjoinednessTree;
    
    private javax.swing.JPanel ccConstraintsPanel;
    private javax.swing.JPanel fkConstraintsPanel;
    private javax.swing.JPanel pkConstraintsPanel;
    private javax.swing.JPanel uqConstraintsPanel;
    /**
     * the API controller
     */
	private APIController apic = null;
    
    /** Creates new form DependencyTabPane */
    public DependencyTabPane(APIController apic) {
    	this.apic = apic;
        initComponents();
        fillTabs();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    /**
     * Adds all components the tab pane
     */
    private void fillTabs(){
    	
    	java.awt.GridBagConstraints gridBagConstraints;
//        jPanelDisjoinednessEditor = new InsertDisjoinednessAssertionPane(apic);
        jPanelDisjoinednessTree= new DisjoinednessAssertionTreePane(apic);
//        jPanelFunctionalDepEditor =new FunctionalDependencyEditorPane(apic);
        jPanelFunctionalDepTree =new FunctionalDepTreePane(apic);
//        jPanelInclusionDepEditor = new InsertInclusionDependencyPane(apic);
        jPanelInclusionDepTree = new InclusionDependencyTreePane(apic);
        
        ccConstraintsPanel = new CheckConstraintsPanel(apic);
        fkConstraintsPanel = new ForeignKeyConstraintsPanel(apic);
        pkConstraintsPanel = new PrimaryKeyConstraintPanel(apic);
        uqConstraintsPanel = new UniquenessConstraintPanel(apic);
        
        
//        gridBagConstraints = new java.awt.GridBagConstraints();
//        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
//        gridBagConstraints.weightx = 1.0;
//        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
//        jPanel1.add(jPanelInclusionDepEditor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanelInclusionDepTree, gridBagConstraints);
        
//        gridBagConstraints = new java.awt.GridBagConstraints();
//        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
//        gridBagConstraints.weightx = 1.0;
//        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
//        jPanel2.add(jPanelDisjoinednessEditor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jPanelDisjoinednessTree, gridBagConstraints);
        
//        gridBagConstraints = new java.awt.GridBagConstraints();
//        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
//        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
//        gridBagConstraints.weightx = 1.0;
//        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
//        jPanel3.add(jPanelFunctionalDepEditor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jPanelFunctionalDepTree, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(pkConstraintsPanel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(fkConstraintsPanel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(ccConstraintsPanel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel7.add(uqConstraintsPanel, gridBagConstraints);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        
        setPreferredSize(new Dimension(70,70));
        setMinimumSize(new Dimension(70,70));
        setLayout(new java.awt.GridBagLayout());
        
        jPanel4.setLayout(new java.awt.GridBagLayout());
        jTabbedPane1.addTab("Primary Key Constraints", jPanel4);
        
        jPanel5.setLayout(new java.awt.GridBagLayout());
        jTabbedPane1.addTab("Foreign Key Constraints", jPanel5);
        
        jPanel6.setLayout(new java.awt.GridBagLayout());
        jTabbedPane1.addTab("Check Constraints", jPanel6);
        
        jPanel7.setLayout(new java.awt.GridBagLayout());
        jTabbedPane1.addTab("Uniqueness Constraints", jPanel7);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        jTabbedPane1.addTab("Inclusion Dependencies", jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());
        jTabbedPane1.addTab("Disjointness Dependencies", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());
        jTabbedPane1.addTab("Functional Dependencies", jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jTabbedPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;

    // End of variables declaration//GEN-END:variables

}