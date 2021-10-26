/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;



import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


/**
 *
 * @author Jadiel
 */
public class Ajust_tabela {
    
    /*public static void dimensionaTabela(JTable ttabela) {  
        log.debug("Dimensionando tabela com "+ttabela.getRowCount()+" linhas...");   
        long initTime=System.currentTimeMillis();  
        packColumns(ttabela, 2);  
        packRows(ttabela, 0);  
        ttabela.setRowHeight(20);  
        ttabela.setIntercellSpacing(new Dimension(2, 2));  
        log.debug("Tabela redimensionada em "+(System.currentTimeMillis()-initTime)+"ms");   
} */ 
  
    public static int getPreferredRowHeight(JTable table, int rowIndex,  
            int margin) {  
        // Get the current default height for all rows  
        int height = table.getRowHeight();  
  
        // Determine highest cell in the row  
        for (int c = 0; c < table.getColumnCount(); c++) {  
            TableCellRenderer renderer = table.getCellRenderer(rowIndex, c);  
            Component comp = table.prepareRenderer(renderer, rowIndex, c);  
            int h = comp.getPreferredSize().height + 2 * margin;  
            height = Math.max(height, h);  
        }  
        return height;  
    }  
  
    //The height of each row is set to the preferred height of the  
    // tallest cell in that row.  
    public static void packRows(JTable table, int margin) {  
        packRows(table, 0, table.getRowCount(), margin);  
    }  
  
    //For each row >= start and < end, the height of a  
    // row is set to the preferred height of the tallest cell  
    // in that row.  
    public static void packRows(JTable table, int start, int end, int margin) {  
        for (int r = 0; r < table.getRowCount(); r++) {  
            // Get the preferred height  
            int h = getPreferredRowHeight(table, r, margin);  
  
            // Now set the row height using the preferred height  
            if (table.getRowHeight(r) != h) {  
                table.setRowHeight(r, h);  
            }  
        }  
    }  
  
    public static void packColumns(JTable table, int margin) {  
        for (int c = 0; c < table.getColumnCount(); c++) {  
            packColumn(table, c, 2);  
        }  
    }  
  
    public static void packColumn(JTable table, int vColIndex, int margin) {  
        TableModel model = table.getModel();  
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table  
                .getColumnModel();  
        TableColumn col = colModel.getColumn(vColIndex);  
        int width = 0;  
  
        // Get width of column header  
        TableCellRenderer renderer = col.getHeaderRenderer();  
        if (renderer == null) {  
            renderer = table.getTableHeader().getDefaultRenderer();  
        }  
        Component comp = renderer.getTableCellRendererComponent(table, col  
                .getHeaderValue(), false, false, -1, 0);  
        width = comp.getPreferredSize().width;  
  
        // Get maximum width of column data  
        for (int r = 0; r < table.getRowCount(); r++) {  
            renderer = table.getCellRenderer(r, vColIndex);  
            comp = renderer.getTableCellRendererComponent(table, table  
                    .getValueAt(r, vColIndex), false, false, r, vColIndex);  
            width = Math.max(width, comp.getPreferredSize().width);  
        }  
  
        // Add margin  
        width += 2 * margin;  
  
        // Set the width  
        col.setPreferredWidth(width);  
    }  
    
   
 
    public static void scrollToVisible(JTable table, int rowIndex)  
    {  
        scrollToVisible(table, rowIndex, 0);  
    }  
  
    public static void scrollToVisible(JTable table, int rowIndex, int vColIndex)  
    {  
        if (!(table.getParent() instanceof JViewport))  
            return;  
  
        setViewPortPosition((JViewport) table.getParent(), table.getCellRect(  
                rowIndex, vColIndex, true));  
    }  
  
    public static Collection<Integer> getReverseSelectedRows(JTable table)  
    {  
        Set<Integer> rows = new TreeSet<Integer>(new Comparator<Integer>()  
        {  
            public int compare(Integer o1, Integer o2)  
            {  
                return o2.compareTo(o1);  
            }  
        });  
  
        for (int r : table.getSelectedRows())  
            rows.add(r);  
  
        return rows;  
    }  
  
    public static void selectAndScroll(JTable table, int rowIndex)  
    {  
        table.getSelectionModel().setSelectionInterval(rowIndex, rowIndex);  
        scrollToVisible(table, rowIndex);  
    }  
  
    public static void scrollToSelection(JTree tree)  
    {  
        if (!(tree.getParent() instanceof JViewport))  
            return;  
  
        setViewPortPosition((JViewport) tree.getParent(),  
                tree.getPathBounds(tree.getSelectionPath()));  
  
    }  
  
    private static void setViewPortPosition(JViewport viewport,  
            Rectangle position)  
    {  
        // The location of the viewport relative to the object  
        Point pt = viewport.getViewPosition();  
  
        // Translate the cell location so that it is relative  
        // to the view, assuming the northwest corner of the  
        // view is (0,0)  
        position.setLocation(position.x - pt.x, position.y - pt.y);  
  
        // Scroll the area into view  
        viewport.scrollRectToVisible(position);  
    }  
  
    public static void expandAllNodes(JTree tree)  
    {  
        for (int i = 0; i < tree.getRowCount(); i++)  
            tree.expandRow(i);  
    }  
  
    public static void expandFirstNodes(JTree tree)  
    {  
        for (int i = tree.getRowCount() - 1; i >= 0; i--)  
            tree.expandRow(i);  
    }  
  
    /** 
     * Encode a color in a string in RGB format. This string is compatible to 
     * HTML format. 
     *  
     * @param color The color to encode (e.g. Color.RED) 
     * @return The encoded color (e.g. FF0000) 
     */  
    public static String encodeColor(Color color)  
    {  
        if (color == null)  
            return "000000";  
  
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(),  
                color.getBlue());  
    }  
  
    public static Component getOwnerWindow(Component component)  
    {  
        Component parent = component;  
        while (parent != null && !(parent instanceof Frame)  
                && !(parent instanceof Dialog))  
            parent = parent.getParent();  
        return parent;  
    }  
  
}  
    

