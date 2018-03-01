package mz.com.osoma.velha;
/* *****************************************************************************
 * UFABC - Inversidade Federal do ABC                                         *
 * MC 3303 - InteligÃªncia Artificial                                          *
 * Professor Jeronimo Pellegrini                                              *
 * Alunos:                                                                    *
 *      Andre Filipe de Moraes Batista                                        *
 *      Luis Fernando de Oliveira Jacintho                                    *
 ******************************************************************************/

import java.util.ArrayList;

public class Tabuleiro
{
  /*
   * Vetor de conversao para impressao na tela
   */
  static char[] conversao = {'o', ' ', 'x'};
  /*
   * Matriz do tabuleiro
   */
  static int[][] tabuleiro;
  /*
   * Pecas Escorregadias?
   */
  boolean escorrega;
  
  /*
   * Construtor
   *   entrada: tamanho do tabuleiro
   */
  public Tabuleiro (boolean escorrega)
  {
    this.escorrega = escorrega;
    tabuleiro = new int[4][4];
  }
  
  /*
   * Metodo invocado para a jogada do Jogador! 
   */
  public void fazerJogada (int l, int c)
  {
    if (tabuleiro[l][c] == 0)
    {
      /*
       * Se estiver jogando com pecas escorregadias...
       */
      if (escorrega)
      {
        /*
         * Verifica os vizinhos livre da posicao..
         */
        ArrayList<int []> vizinhos = vizinhosLivres(l, c);
        
        /*
         * Se houver ao menos um vizinho livre, tem 20% de chance da peÃ§a
         * escorregar..
         */
        if (vizinhos.size() > 0 && Math.random() <= 0.2)
        {
          /*
           * Escolhe um dos vizinhos aleatoriamente..
           */
          int x = (int) (Math.random() * vizinhos.size());
          /*
           * Transforma as coordenadas atuais nas coordenadas do vizinho
           * escolhido..
           */
          l = vizinhos.get(x)[0];
          c = vizinhos.get(x)[1];
          System.out.println ("A peca escorregou e caiu na posicao: " + l + ", " + c);
        }
      }
      tabuleiro[l][c] = -1;
    }
    else
      System.out.println ("Posicao ja ocupada, perdeu a vez!");
  }
  
  /*
   * Metodo que verifica se ha vizinhos livres, considerando as diagonais...
   */
  public ArrayList<int []> vizinhosLivres (int l, int c)
  {
    ArrayList<int []> vizinhos = new ArrayList<int []> ();
    
    /*
     * Vizinhos da linha anterior, se houver...
     */
    if (l > 0)
    {
      if (c > 0)
        if (tabuleiro[l-1][c-1] == 0)
          vizinhos.add(new int[] {l-1, c-1});
      
      if (tabuleiro[l-1][c] == 0)
        vizinhos.add(new int[] {l-1, c});
      
      if (c < 3)
        if (tabuleiro[l-1][c+1] == 0)
          vizinhos.add(new int[] {l-1, c+1});
    }
    
    /*
     * Vizinhos da mesma linha...
     */
    if (c > 0)
      if (tabuleiro[l][c-1] == 0)
        vizinhos.add(new int[] {l, c-1});
    
    if (c < 3)
      if (tabuleiro[l][c+1] == 0)
        vizinhos.add(new int[] {l, c+1});
    
    /*
     * Vizinhos da linha posterior, se houver...
     */
    if (l < 3)
    {
      if (c > 0)
        if (tabuleiro[l+1][c-1] == 0)
          vizinhos.add(new int[] {l+1, c-1});
      
      if (tabuleiro[l+1][c] == 0)
        vizinhos.add(new int[] {l+1, c});
      
      if (c < 3)
        if (tabuleiro[l+1][c+1] == 0)
          vizinhos.add(new int[] {l+1, c+1});
    }
    
    return vizinhos;
  }
  
  /*
   * Metodo para a impressÃ£o do tabuleiro na tela 
   */
  public void imprimir ()
  {    
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        System.out.printf (" %c %c", conversao[tabuleiro[i][j] + 1], j == 3 ? ' ' : '|');
      }
      if (i != (3))
        System.out.println("\r\n---+---+---+---");
    }
    System.out.println("\r\n");
  }
}