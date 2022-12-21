/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GreenHouse.Swing;

import com.GreenHouse.Connection.JDBCHelper;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Thuy
 */
public class Table extends JDBCHelper {
    //public static DefaultTableModel model;
    public void hideLBLError(JLabel[] lbls) {
        for (JLabel lbl : lbls) {
            lbl.setVisible(false);
        }
    }

    public void reset(JTextField[] txts) {
        for (JTextField txt : txts) {
            txt.setText(null);
        }
        txts[0].requestFocus();
        txts[0].setEditable(true);
    }

    public void editColumnWidth(int[] col, JTable tbl) {
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            TableColumn column = tbl.getColumnModel().getColumn(i);
            column.setMinWidth(col[i]);
            column.setMaxWidth(col[i]);
            column.setPreferredWidth(col[i]);
        }
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbl.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ((DefaultTableCellRenderer) tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }
    
    public PreparedStatement prepareStatement(String sql, Object[] args) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        return ps;
    }
   
    public void statement(String sql, JTable tbl, String[] header) throws SQLException {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        tbl.setDefaultEditor(Object.class, null);
      //  tbl.getTableHeader().setCursor(new Cursor(HAND_CURSOR));
        tbl.getTableHeader().setFont(new Font("Segoe UI", 1, 13));
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Vector data = new Vector();
            for (int i = 0; i < header.length; i++) {
                data.add(rs.getObject(i + 1));
            }
            model.addRow(data);
        }
        tbl.setModel(model);
       // tbl.getModel();
        con.close();
    }
    
   
}
