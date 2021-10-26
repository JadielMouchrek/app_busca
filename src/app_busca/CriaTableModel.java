/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_busca;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jadiel
 */
public class CriaTableModel extends AbstractTableModel {  
  
    private static final long serialVersionUID = 1L;  
  
    /** 
     * Essa lista armazenará os objetos do tipo {@link Cliente} atualmente 
     * exibidos na tablela. 
     */  
    private List<Lista_principal> lista; 
  
    // ==============================================================  
    // Construtores.  
    // ==============================================================  
  
    /** 
     * Constutor que simplesmente instancia o nosso {@link List} que usaremos 
     * para guardar os valores. 
     */  
    public CriaTableModel() {  
        // no construtor, instanciamos o List  
        lista = new ArrayList<Lista_principal>();  
    }  
  
    /** 
     * Criamos um construtor de conveniência para já popular a lista. 
     *  
     * @param arq
   
     *            a lista de clientes a ser adicionada. 
     */  
    public CriaTableModel(List<Lista_principal> arq) {  
        this();  
        lista.addAll(arq);  
    }  
  
    // ==============================================================  
    // Métodos implementados.  
    // ==============================================================  
  
    @Override  
    public Class<?> getColumnClass(int coluna) {  
        // todas as colunas representam uma String  
        return String.class;  
    }  
  
    @Override  
    public int getColumnCount() {  
        // esse método deve retornar o número de colunas. No caso, 3 (uma para o  
        // nome, uma para o sobrenome e uma para o apelido).  
        return 3;  
    }  
  
    @Override  
    public String getColumnName(int coluna) {  
        // vamos retornar o nome de cada coluna  
        switch (coluna) {  
        case 0:  
            return "Local_Arquivo"; // o nome da primeira coluna  
        case 1:  
            return "Data da Pesquisa"; // o nome da segunda  
        case 2:  
            return "Hora da Pesquisa"; // e o da terceira  
        default:  
            return ""; // isso nunca deve ocorrer, pois temos só 3 colunas  
        }  
    }  
  
    @Override  
    public int getRowCount() {  
        // retorna o número de linhas, ou seja, a quantidade de entradas na  
        // nossa lista.  
        return lista.size();  
    }  
  
    @Override  
    public Object getValueAt(int linha, int coluna) {  
        // vai retornar o valor de determinada célula. A linha representa a  
        // posição do Cliente na nossa lista e a coluna vai ser: 1 - nome, 2 -  
        // sobrenome e 3 - apelido  
        // primeiro vamos pegar o Cliente da linha  
        Lista_principal c = lista.get(linha);  
        // façamos um switch  
        switch (coluna) {  
        case 0:  
            return c.getlocal_arquivo(); // retornamos o nome  
        case 1:  
            return c.getdata_listagem(); // retornamos o sobrenome  
        case 2:  
            return c.gethora(); // e o apelido  
        default:  
            return null; // isso nunca deve ocorrer, pois temos só 3 colunas  
        }  
    }  
  
    @Override  
    public boolean isCellEditable(int rowIndex, int columnIndex) {  
        // nesse caso, todas as células são editáveis  
        return false;  
    }  
  
    /*@Override  
    public void setValueAt(Object valor, int linha, int coluna) {  
        // aqui devemos atualizar o valor de nossos Clientes  
        // vemos em qual linha ele está  
        Lista_principal c = lista.get(linha);  
        // e vemos o que será atualizado  
        switch (coluna) {  
        case 0:  
            c.setlocal_arquivo(valor.toString()); // mudamos o nome  
            break;  
        case 1:  
            c.setdata_listagem(valor.toString); // mudamos o sobrenome  
            break;  
        case 2:  
            c.sethora(valor.toString()); // e o apelido  
            break;  
        }  
        // é importante notificar os listeners a cada alteração  
        fireTableDataChanged();  
    } */ 
  
    // ==============================================================  
    // Até aqui seria o mínimo necessário para um TableModel funcional, mas  
    // ainda não há métodos para adicionar, remover ou resgatar objetos. Vamos  
    // criá-los.  
    // ==============================================================  
  
    /** 
     * Adiciona um novo {@link Cliente} e notifica os listeners que um novo 
     * registro foi adicionado. 
     * @param c
     */  
    public void adiciona(Lista_principal c) {  
        lista.add(c);  
        // informamos os listeners que a linha (size - 1) foi adicionada  
        fireTableRowsInserted(lista.size() - 1, lista.size() - 1);  
    }  
  
    /** 
     * Similar ao {@link #adiciona(Cliente)}, porém para remover. Recebe o 
     * índice do cliente a ser removido. Se não souber o índice, use o método 
     * {@link #getIndice(Cliente)} antes. 
     * @param indice
     */  
    public void remove(int indice) {  
        lista.remove(indice);  
        fireTableRowsDeleted(indice, indice);  
    }  
  
    /** 
     * Retorna o índice de determinado cliente. 
     * @param c
     * @return 
     */  
    public int getIndice(Lista_principal c) {  
        return lista.indexOf(c);  
    }  
  
    /** 
     * Adiciona todos os clientes na lista à este modelo. 
     * @param lista
     */  
    public void adicionaLista(List<Lista_principal> lista) {  
        int i = lista.size();  
        lista.addAll(lista);  
        fireTableRowsInserted(i, i + lista.size());  
    }  
  
    /** 
     * Esvazia a lista. 
     */  
    public void limpaLista() {  
        int i = lista.size();  
        lista.clear();  
        fireTableRowsDeleted(0, i - 1);  
    }  
  
}  
    

