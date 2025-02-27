/*
 * TeleStax, Open Source Cloud Communications  Copyright 2012.
 * and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.ussdgateway.tools.httpsimulator;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class HttpParametersForm extends JDialog {

    private HttpSimulatorParameters data;
    private JTextField tbListerningPort;
    private JTextField tbCallingHost;
    private JTextField tbCallingPort;

    public HttpParametersForm(JFrame owner) {
        super(owner, true);

        setTitle("HTTP general parameters");
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 620, 227);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel lblListerningPort = new JLabel("Listerning port");
        lblListerningPort.setBounds(10, 14, 401, 14);
        panel.add(lblListerningPort);
        
        tbListerningPort = new JTextField();
        tbListerningPort.setColumns(10);
        tbListerningPort.setBounds(424, 11, 180, 20);
        panel.add(tbListerningPort);
        
        JButton btOK = new JButton("OK");
        btOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                doOK();
            }
        });
        btOK.setBounds(327, 165, 136, 23);
        panel.add(btOK);
        
        JButton btCancel = new JButton("Cancel");
        btCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doCancel();
            }
        });
        btCancel.setBounds(468, 165, 136, 23);
        panel.add(btCancel);
        
        JLabel lblCallingHost = new JLabel("Calling host");
        lblCallingHost.setBounds(10, 42, 401, 14);
        panel.add(lblCallingHost);
        
        tbCallingHost = new JTextField();
        tbCallingHost.setColumns(10);
        tbCallingHost.setBounds(424, 39, 180, 20);
        panel.add(tbCallingHost);
        
        JLabel lblCallingPort = new JLabel("Calling port");
        lblCallingPort.setBounds(10, 70, 401, 14);
        panel.add(lblCallingPort);
        
        tbCallingPort = new JTextField();
        tbCallingPort.setColumns(10);
        tbCallingPort.setBounds(424, 67, 180, 20);
        panel.add(tbCallingPort);
    }

    public void setData(HttpSimulatorParameters data) {
        this.data = data;

        this.tbListerningPort.setText(((Integer) data.getListerningPort()).toString());
        this.tbCallingHost.setText(data.getCallingHost());
        this.tbCallingPort.setText(((Integer) data.getCallingPort()).toString());
    }

    public HttpSimulatorParameters getData() {
        return this.data;
    }

    private void doOK() {
        int intVal = 0;
        try {
            intVal = Integer.parseInt(this.tbListerningPort.getText());
            this.data.setListerningPort(intVal);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception when parsing ListerningPort value: " + e.toString());
            return;
        }
        this.data.setCallingHost(this.tbCallingHost.getText());
        try {
            intVal = Integer.parseInt(this.tbCallingPort.getText());
            this.data.setCallingPort(intVal);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception when parsing CallingPort value: " + e.toString());
            return;
        }

        this.dispose();
    }

    private void doCancel() {
        this.data = null;
        this.dispose();
    }
}
