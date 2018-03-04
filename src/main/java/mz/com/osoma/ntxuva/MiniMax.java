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
    int maxProf = 3;

    public MiniMax() {
    }

    /*
   * Metodo de decisao do MiniMax
     */
//    public int[][] decisao_minimax(int[][] tab) {
    public Position decisao_minimax(Ntxuva ntuxa) {

        /*
     * Limpa os sucessores
         */
        sucessores.clear();

        /*
     * Recebe a utilidade mÃ¡xima
         */
        int v = valor_max(ntuxa, Integer.MIN_VALUE, Integer.MAX_VALUE, true, 1);

        /*
     * Percorre a lista em busca do primeiro sucessor com utilidade maxima
         */
        for (Sucessor s : sucessores) {
            if (s.utilidade == v) {
                return s.position;
            }
        }

        return null;

    }

    public int valor_max(Ntxuva ntxuva, int alfa, int beta, boolean prim, int prof) {
        /*
     * Se o jogo acabou, retorna a utilidade
         */
//    if (teste_terminal (tab))
        if (teste_terminal(ntxuva) || prof++ > maxProf) {
            return utilidade(ntxuva);
        }

        /*
     * Atribui -Infinito
         */
        int v = Integer.MIN_VALUE;

        /*
     * Percorre os nos sucessores de MAX
         */
        
        for (Sucessor s : possibleMoves(ntxuva, 'o')) {
            
            v = Math.max(v, valor_min(s.tabuleiro, alfa, beta, prof));
            s.utilidade = v;

            /*
       * Se forem os primeiros sucessores, adiciona na lista de sucessores...
             */
            if (prim) {
                sucessores.add(s);
            }

            /*
       * Poda Beta - Se o valor for maior que beta, retorna o valor..
             */
            if (v >= beta) {
                return v;
            }

            /*
       * Se o valor for maior que Alfa, Alfa recebe o valor...
             */
            alfa = Math.max(alfa, v);
        }

        return v;
    }

    public int valor_min(Ntxuva ntxuva, int alfa, int beta, int prof) {
        /*
     * Se o jogo acabou, retorna a utilidade
         */
        if (teste_terminal(ntxuva) || prof++ > maxProf) {
            return utilidade(ntxuva);
        }

        /*
     * Atribui +Infinito
         */
        int v = Integer.MAX_VALUE;

        /*
     * Percorre os nos sucessores de MIN
         */
        for (Sucessor s : possibleMoves(ntxuva, 'x')) {
            v = Math.min(v, valor_max(s.tabuleiro, alfa, beta, false, prof));
            s.utilidade = v;

            /*
       * Poda Alfa - Se o valor for menor que alfa, retorna o valor...
             */
            if (v <= alfa) {
                return v;
            }

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
    public int utilidade(Ntxuva ntxuva) {
        int pc, usr;

        pc = contaPecas(ntxuva, 'o');
        usr = contaPecas(ntxuva, 'x');

        return (pc - usr);
    }

    public int contaPecas(Ntxuva ntxuva, char turn) {
        return ntxuva.sumPieces(turn);
    }

    /*
   * Fim de jogo?
   * O jogo so termina se nao houver mais espaco para jogadas...
     */
    public boolean teste_terminal(Ntxuva ntxuva) {
//        return (semEspaco(tab));
        return false;
    }

    public ArrayList<Sucessor> possibleMoves(Ntxuva ntxuva, char turn) {

        ArrayList<Sucessor> suc = new ArrayList<>();

        int startRow = (turn == 'x') ? Ntxuva.ROW_ZERO : Ntxuva.ROW_TWO;
        int finishRow = (turn == 'x') ? Ntxuva.ROW_TWO : Ntxuva.ROWS;

        if (ntxuva.moreThanOnePiece(turn)) {
            for (int i = startRow; i < finishRow; i++) {
                for (int j = 0; j < Ntxuva.COLUMNS; j++) {
                    if (ntxuva.board[i][j] > 1) {
                        Position p = new Position(i, j);

                        Ntxuva move = ntxuva.move(p);
                        suc.add(new Sucessor(move, p));
                    }
                }
            }
        } else {
            for (int i = startRow; i < finishRow; i++) {
                for (int j = 0; j < Ntxuva.COLUMNS; j++) {
                    if (ntxuva.board[i][j] != 0) {
                        Position p = new Position(i, j);
                        Ntxuva move = ntxuva.move(p);
                        suc.add(new Sucessor(move, p));
                    }
                }
            }
        }
        return suc;
    }

}
