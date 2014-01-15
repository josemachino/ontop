package org.semanaticweb.ontop.protege4.utils;

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

import java.io.PrintWriter;
import java.io.StringWriter;

public class TextMessageDialog extends javax.swing.JDialog {

	private static final long	serialVersionUID	= 3229901534514938061L;
	
	/** A return status code - returned if Cancel button has been pressed */
	public static final int RET_CANCEL = 0;

	/** A return status code - returned if OK button has been pressed */
	public static final int RET_OK = 1;
	
	private int returnStatus = RET_CANCEL;

	/** Creates new form TextMessageDialog */
	public TextMessageDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/***
	 * Puts the stack trace of the given exception as the text of this message
	 * dialog.
	 */
	public void setText(Exception e) {
		StringWriter wr = new StringWriter();
		PrintWriter pr = new PrintWriter(wr);
		e.printStackTrace(pr);
		this.setText(wr.toString());
	}

	public void setText(String text) {
		this.jTextArea1.setText(text);
	}

	public void setCarrent(int position) {
		this.jTextArea1.setCaretPosition(position);
	}

	/** 
	 * @return the return status of this dialog - one of RET_OK or RET_CANCEL 
	 */
	public int getReturnStatus() {
		return returnStatus;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Message:");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jLabel1, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        okButton.setText("Close");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        getContentPane().add(okButton, gridBagConstraints);

        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(640, 480));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("com.ibm.db2.jcc.b.SqlException?: DB2 SQL error: SQLCODE: -101, SQLSTATE: 54001, SQLERRMC: null\nat com.ibm.db2.jcc.b.zc.e(zc.java:1606)\nat com.ibm.db2.jcc.b.zc.a(zc.java:1206)\nat com.ibm.db2.jcc.a.db.h(db.java:149)\nat com.ibm.db2.jcc.a.db.a(db.java:43)\nat com.ibm.db2.jcc.a.r.a(r.java:30)\nat com.ibm.db2.jcc.a.sb.g(sb.java:152)\nat com.ibm.db2.jcc.b.zc.n(zc.java:1186)\nat com.ibm.db2.jcc.b.zc.a(zc.java:1857)\nat com.ibm.db2.jcc.b.zc.a(zc.java:497)\nat com.ibm.db2.jcc.b.zc.executeQuery(zc.java:481)\nat it.uniroma1.dis.quonto.datasourcemanager.impl.DB2DataSourceManager.executeQuery(DB2DataSourceManager.java:175)\nat it.uniroma1.dis.quonto.eql.impl.EqlEngine?.evaluateEQLWithoutCheckConsistency(EqlEngine?.java:160)\nat it.uniroma1.dis.quonto.eql.impl.EqlEngine?.evaluateEQL(EqlEngine?.java:192)\nat it.uniroma1.dis.quonto.mastromanager.MastroManager?.answerEQLQuery(MastroManager?.java:273)\nat it.fub.inf.quonto.owlapi.QuontoOWLReasonerWrapper.answerEQL(QuontoOWLReasonerWrapper.java:921)\nat inf.unibz.it.obda.protege4.gui.view.query.QueryInterfaceViewComponent?$6.run(QueryInterfaceViewComponent?.java:345)\nat inf.unibz.it.obda.gui.swing.dataquery.panel.QueryInterfacePanel?$10.run(QueryInterfacePanel?.java:335)\nat java.lang.Thread.run(Thread.java:637)");
        jTextArea1.setPreferredSize(new java.awt.Dimension(640, 480));
        jScrollPane2.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
		doClose(RET_OK);
	}// GEN-LAST:event_okButtonActionPerformed

	/** Closes the dialog */
	private void closeDialog(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_closeDialog
		doClose(RET_CANCEL);
	}// GEN-LAST:event_closeDialog

	private void doClose(int retStatus) {
		returnStatus = retStatus;
		setVisible(false);
		dispose();
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
