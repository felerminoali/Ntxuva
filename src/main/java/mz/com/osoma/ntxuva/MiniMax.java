/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.ntxuva;

import java.util.ArrayList;

/**
 *
 * @author felermino
 */
public class MiniMax {

    static ArrayList<Sucessor> sucessores = new ArrayList<>();

    public MiniMax() {
    }

    /*
   * Metodo de decisao do MiniMax
     */
    public int[][] decisao_minimax(int[][] tab) {

         /*
     * Limpa os sucessores
     */
    sucessores.clear ();

    /*
     * Recebe a utilidade mÃ¡xima
     */
    int v = valor_max (tab, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    
    /*
     * Percorre a lista em busca do primeiro sucessor com utilidade maxima
     */
    for (Sucessor s: sucessores)
      if (s.utilidade == v)
        return s.tabuleiro;
    
    return tab;
        
    }
    
    
    public int valor_max (int[][] tab, int alfa, int beta, boolean prim)
  {
    /*
     * Se o jogo acabou, retorna a utilidade
     */
    if (teste_terminal (tab))
      return utilidade (tab);
    
    /*
     * Atribui -Infinito
     */
    int v = Integer.MIN_VALUE;
    
    /*
     * Percorre os nos sucessores de MAX
     */
    for (Sucessor s: gerar_sucessores (tab, 1))
    {
      v = Math.max(v, valor_min (s.tabuleiro, alfa, beta));
      s.utilidade = v;

      /*
       * Se forem os primeiros sucessores, adiciona na lista de sucessores...
       */
      if (prim)
        sucessores.add(s);
      
      /*
       * Poda Beta - Se o valor for maior que beta, retorna o valor..
       */
      if (v >= beta)
        return v;
      
      /*
       * Se o valor for maior que Alfa, Alfa recebe o valor...
       */
      alfa = Math.max(alfa, v);
    }
    
    return v;
  }
  
  public int valor_min (int[][] tab, int alfa, int beta)
  {
    /*
     * Se o jogo acabou, retorna a utilidade
     */
    if (teste_terminal (tab))
      return utilidade (tab);
    
    /*
     * Atribui +Infinito
     */
    int v = Integer.MAX_VALUE;
    
    /*
     * Percorre os nos sucessores de MIN
     */
    for (Sucessor s: gerar_sucessores (tab, -1))
    {
      v = Math.min(v, valor_max (s.tabuleiro, alfa, beta, false));
      s.utilidade = v;
      
      /*
       * Poda Alfa - Se o valor for menor que alfa, retorna o valor...
       */
      if (v <= alfa)
        return v;
      
      /*
       * Se valor menor que Beta, Beta o recebe...
       */
      beta = Math.min(beta, v);
    }
    
    return v;
  }
  
  /*
   * Retorna a utilidade...
   * Aqui a utilidade considerada e a diferenca de pontos entre o computador e
   * o jogador, o computador nao deseja apenas vencer, mas tambem humilhar =P
   */
  public int utilidade (int[][] tab)
  {
    int pc, usr;
    
    pc = contaPontos(tab, 1);
    usr = contaPontos (tab, -1);
    
    return (pc-usr);
  }
  
  /*
   * Fim de jogo?
   * O jogo so termina se nao houver mais espaco para jogadas...
   */
  public boolean teste_terminal (int[][] tab)
  {
    return (semEspaco (tab));
  }
  

}
