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

import java.util.Scanner;

public class Velha
{  
  /*
   * Pecas escorregadias?
   */
  static boolean ESCORREGA;
  
  public static void main (String[] args)
  {
    Scanner ent = new Scanner (System.in);
    
    System.out.print ("Voce deseja jogar com pecas escorregadias? [s/n]: ");
    String esc = ent.nextLine();
    
    if (esc.charAt(0) == 's' || esc.charAt(0) == 'S')
    {
      ESCORREGA = true;
      System.out.println ("Pecas escorregadias ativada.");
    }
    else
    {
      ESCORREGA = false;
      System.out.println ("Pecas escorregadias desativada.");
    }
    
    Tabuleiro t = new Tabuleiro (ESCORREGA);
    MiniMax mm = new MiniMax (ESCORREGA);
    t.imprimir ();
    
    
    do
    {
      int l, c;
      System.out.printf ("Sua jogada:\r\nLinha [0 - 3]: ");
      l = ent.nextInt ();
      System.out.printf ("Coluna [0 - 3]: ");
      c = ent.nextInt ();
      t.fazerJogada(l, c);
      t.imprimir ();
      if (!mm.teste_terminal(t.tabuleiro))
      {
        long time = System.currentTimeMillis ();
        t.tabuleiro = mm.decisao_minimax(t.tabuleiro);
        time = System.currentTimeMillis () - time;
        System.out.println ("Jogada do Computador (" + time + " ms):");
        t.imprimir ();
      }
    } while (!mm.teste_terminal(t.tabuleiro));
    
    int u = mm.utilidade(t.tabuleiro);
    if (u < 0)
      System.out.println ("Parabens! Voce ganhou...");
    else if (u == 0)
      System.out.println ("Empatou!");
    else
      System.out.println ("Voce realmente e pior que um computador...");
    System.out.println("Voce marcou " + mm.contaPontos(t.tabuleiro, -1) + " pontos.");
    System.out.println("O computador marcou " + mm.contaPontos(t.tabuleiro, 1) + " pontos.");
    
  }
}