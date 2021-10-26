/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Jadiel
 */
public class JFixedColScrollPane extends JScrollPane {

  private JTable fixedTable;
  private JTable mainTable;

  public JFixedColScrollPane( JTable fxTable, JTable mnTable ) throws Exception {
    super( mnTable );
    this.fixedTable = fxTable;
    this.mainTable = mnTable;

    fixedTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    mainTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

    setRowHeaderView( fixedTable );

    ListSelectionListener listSelectionListener = new ListSelectionListener() {
      @Override
      public void valueChanged( ListSelectionEvent e ) {
        JTable tabelaSelecionada = (e.getSource() == fixedTable.getSelectionModel()) ? fixedTable : mainTable;
        JTable tabelaASelecionar = (tabelaSelecionada == fixedTable) ? mainTable : fixedTable;
        tabelaASelecionar.getSelectionModel().removeListSelectionListener( this );
        tabelaASelecionar.clearSelection();
        int linha = tabelaSelecionada.getSelectedRow();
        if ( linha >= 0 ) {
          tabelaASelecionar.getSelectionModel().addSelectionInterval( linha, linha );
          if ( tabelaSelecionada == fixedTable ) {
            Rectangle rectangle = tabelaASelecionar.getCellRect( linha, 0, false );
            rectangle.width = tabelaASelecionar.getWidth();
            tabelaASelecionar.scrollRectToVisible( rectangle );
          }
        }
        tabelaASelecionar.getSelectionModel().addListSelectionListener( this );
      }
    };

    this.fixedTable.getSelectionModel().addListSelectionListener( listSelectionListener );
    this.mainTable.getSelectionModel().addListSelectionListener( listSelectionListener );

    final JTableHeader fixedTableHeader = this.fixedTable.getTableHeader();
    setCorner( ScrollPaneConstants.UPPER_LEFT_CORNER, fixedTableHeader );

    fixedTable.addComponentListener( new ComponentAdapter() {
      @Override
      public void componentResized( ComponentEvent e ) {
        fixedTable.setPreferredScrollableViewportSize( new Dimension( fixedTable.getColumnModel().getTotalColumnWidth(), 50 ) );
      }
    } );
  }

    public JFixedColScrollPane(JTable controle_dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  public JTable getFixedTable() throws Exception {
    return fixedTable;
  }

  public JTable getMainTable() throws Exception {
    return mainTable;
  }
}
