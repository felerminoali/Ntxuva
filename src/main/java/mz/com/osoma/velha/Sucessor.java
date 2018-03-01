package mz.com.osoma.velha;
/******************************************************************************
 * UFABC - Inversidade Federal do ABC                                         *
 * MC 3303 - InteligÃªncia Artificial                                          *
 * Professor Jeronimo Pellegrini                                              *
 * Alunos:                                                                    *
 *      Andre Filipe de Moraes Batista                                        *
 *      Luis Fernando de Oliveira Jacintho                                    *
 ******************************************************************************/

public class Sucessor
{
  int[][] tabuleiro;
  int utilidade;
  
  /*
   * Construtor 
   */
  public Sucessor (int[][] tab)
  {
    /*
     * Cria um novo tabuleiro, baseado no que foi passado
     */
    int tam = tab.length;
    tabuleiro = new int[tam][tam];

    for (int i = 0; i < tam; i++)
      for (int j = 0; j < tam; j++)
        tabuleiro[i][j] = tab[i][j];
  }
}
