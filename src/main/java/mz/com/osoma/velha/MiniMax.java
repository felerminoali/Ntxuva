/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.velha;

/******************************************************************************
 * UFABC - Inversidade Federal do ABC                                         *
 * MC 3303 - InteligÃªncia Artificial                                          *
 * Professor Jeronimo Pellegrini                                              *
 * Alunos:                                                                    *
 *      Andre Filipe de Moraes Batista                                        *
 *      Luis Fernando de Oliveira Jacintho                                    *
 ******************************************************************************/

import java.util.ArrayList;

public class MiniMax
{
  /*
   * Lista dos nos sucessores
   */
  static ArrayList<Sucessor> sucessores = new ArrayList<Sucessor> ();
  /*
   * Jogar com pecas escorregadias?
   */
  boolean escorrega;
  
  /*
   * Construtor
   */
  public MiniMax (boolean escorrega)
  {
    this.escorrega = escorrega;
  }
  
  /*
   * Metodo de decisao do MiniMax
   */
  public int[][] decisao_minimax (int[][] tab)
  {
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
   * Gera os sucessores de um jogador, a partir do estado atual
   */
  public ArrayList<Sucessor> gerar_sucessores (int[][] tab, int v)
  {
    ArrayList<Sucessor> suc = new ArrayList<Sucessor> ();
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {        
        if (tab[i][j] == 0)
        {
          /*
           * Se estiver jogando com pecas escorregadias...
           */
          if (escorrega)
          {
            /*
             * Verifica os vizinhos livre da posicao..
             */
            ArrayList<int []> vizinhos = vizinhosLivres(tab, i, j);
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
              i = vizinhos.get(x)[0];
              j = vizinhos.get(x)[1];
            }
          }
          
          tab[i][j] = v;
          suc.add(new Sucessor (tab));
          tab[i][j] = 0;
        }
      }
    }
    
    return suc;
  }
  
  /*
   * Metodo que verifica se ha vizinhos livres, considerando as diagonais...
   */
  public ArrayList<int []> vizinhosLivres (int[][] tabuleiro, int l, int c)
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
   * Fim de jogo?
   * O jogo so termina se nao houver mais espaco para jogadas...
   */
  public boolean teste_terminal (int[][] tab)
  {
    return (semEspaco (tab));
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
   * Verifica se jogador ganhou
   */
  public int contaPontos (int[][] tab, int v)
  {
    int pontos = 0;
    
    for (int i = 0; i < 4; i++)
    {
      pontos += contaLinha (tab, i, v);
      pontos += contaColuna (tab, i, v);
    }
    
    pontos += contaDiag1(tab, v);
    pontos += contaDiag2(tab, v);
    
    return pontos;
  }
  
  /*
   * Pontos na sequencia de linhas?
   * 
   * Metodo de contagem binaria.. um byte e desnecessario, precisaria apenas
   * de 4 bits.. Basicamente, para cada posicao atribui-se o valor 1 na mesma
   * posicao do byte, indicando que ali e dele. No final checamos as 3
   * possibilidades de marcar pontos, 4 posicoes vizinhas (1111) ou 3 posicoes
   * vizinhas (0111 ou 1110). Qualquer outra combinaÃ§Ã£o teria menos do que
   * 3 posicoes vizinhas e nao marcariam pontos.
   */
  private int contaLinha (int[][] tab, int l, int v)
  {
    byte soma = 0;
      
    for (int i = 0; i < 4; i++)
      if (tab[l][i] == v)
        soma += (1 << i);
    
    if (soma == 15) // 1111
      return 3;
    else if ((soma == 7) ||  (soma == 14)) // 0111 v 1110
      return 1;
    else
      return 0;
  }
  
  /*
   * Pontos na sequencia de colunas?
   *  
   * Metodo de contagem binaria.. um byte e desnecessario, precisaria apenas
   * de 4 bits.. Basicamente, para cada posicao atribui-se o valor 1 na mesma
   * posicao do byte, indicando que ali e dele. No final checamos as 3
   * possibilidades de marcar pontos, 4 posicoes vizinhas (1111) ou 3 posicoes
   * vizinhas (0111 ou 1110). Qualquer outra combinaÃ§Ã£o teria menos do que
   * 3 posicoes vizinhas e nao marcariam pontos.
   */
  private int contaColuna (int[][] tab, int c, int v)
  {
    int soma = 0;
    
    for (int i = 0; i < 4; i++)
      if (tab[i][c] == v)
        soma += (1 << i);
    
    if (soma == 15) // 1111
      return 3;
    else if ((soma == 7) || (soma == 14)) // 0111 v 1110
      return 1;
    else
      return 0;
  }
  
  /*
   * Ganhou na sequencia diagonal?
   *  
   * Metodo de contagem binaria.. um byte e desnecessario, precisaria apenas
   * de 4 bits.. Basicamente, para cada posicao atribui-se o valor 1 na mesma
   * posicao do byte, indicando que ali e dele. No final checamos as 3
   * possibilidades de marcar pontos, 4 posicoes vizinhas (1111) ou 3 posicoes
   * vizinhas (0111 ou 1110). Qualquer outra combinaÃ§Ã£o teria menos do que
   * 3 posicoes vizinhas e nao marcariam pontos.
   * No caso da diagonal, este metodo so funciona na diagonal principal, as
   * outras duas diagonais possiveis, a checagem e manual.
   */
  private int contaDiag1 (int[][] tab, int v)
  {
    int soma = 0;
    int pextra = 0;
    
    for (int i = 0; i < 4; i++)
      if (tab[i][i] == v)
        soma += (1 << i);
    
    /*
     * Nas duas diagonais a seguir so e possÃ­vel formar sequencias de 3,
     * podendo-se adicionar entao apena 1 ponto....
     */
    if (tab[1][0] == v && tab[2][1] == v && tab[3][2] == v)
      pextra++;
    if (tab[0][1] == v && tab[1][2] == v && tab[2][3] == v)
      pextra++;
    
    if (soma == 15)
      return 3 + pextra;
    else if ((soma == 7) || (soma == 14))
      return 1 + pextra;
    else
      return 0 + pextra;
  }
  
  /*
   * Ganhou na sequencia diagonal?
   *  
   * Metodo de contagem binaria.. um byte e desnecessario, precisaria apenas
   * de 4 bits.. Basicamente, para cada posicao atribui-se o valor 1 na mesma
   * posicao do byte, indicando que ali e dele. No final checamos as 3
   * possibilidades de marcar pontos, 4 posicoes vizinhas (1111) ou 3 posicoes
   * vizinhas (0111 ou 1110). Qualquer outra combinaÃ§Ã£o teria menos do que
   * 3 posicoes vizinhas e nao marcariam pontos.
   * No caso da diagonal, este metodo so funciona na diagonal principal, as
   * outras duas diagonais possiveis, a checagem e manual.
   */
  private int contaDiag2 (int[][] tab, int v)
  {
    int soma = 0;
    int pextra = 0;
    
    for (int i = 0; i < 4; i++)
      if (tab[3-i][i] == v)
        soma += (1 << i);
    
    /*
     * Nas duas diagonais a seguir so e possÃ­vel formar sequencias de 3,
     * podendo-se adicionar entao apena 1 ponto....
     */
    if (tab[0][2] == v && tab[1][1] == v && tab[2][0] == v)
      pextra++;
    if (tab[1][3] == v && tab[2][2] == v && tab[3][1] == v)
      pextra++;
    
    if (soma == 15)
      return 3 + pextra;
    else if ((soma == 7) || (soma == 14))
      return 1 + pextra;
    else
      return 0 + pextra;
  }
  
  /*
   * Nao tem mais espacos restantes no tabuleiro..
   */
  public boolean semEspaco (int[][] tab)
  {    
    for (int l = 0; l < 4; l++)
      for (int c = 0; c < 4; c++)
        if (tab[l][c] == 0)
          return false;
    
    return true;
  }
}