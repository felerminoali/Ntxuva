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
    int maxProf = 5;

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

//                        if (!mayerTest(ntxuva, turn)) {
                        if (!ntxuva.isInfitMove(new Position(i, j))) {
                            Position p = new Position(i, j);

//                            System.out.println(ntxuva);
//                            System.out.println("P: " + p);
                            Ntxuva move = ntxuva.move(p);

                            suc.add(new Sucessor(move, p));
                        } 
else {
                            System.out.println("=========Cyclte ========");
                            System.out.println(ntxuva);
                            System.out.println("P: " + new Position(i, j));
                            System.out.println("=================");
                        }

                    }
                }
            }
        } else {
            for (int i = startRow; i < finishRow; i++) {
                for (int j = 0; j < Ntxuva.COLUMNS; j++) {
                    if (ntxuva.board[i][j] != 0) {

                        Position next = new Position(i, j).moveAntiClockWise();
                        if (ntxuva.board[next.row][next.column] == 0) {
//                            if (!mayerTest(ntxuva, turn)) {
//                            if (!ntxuva.isInfitMove(new Position(i, j))) {
                                Position p = new Position(i, j);
                                System.out.println(ntxuva);
                                System.out.println("P: " + p);
                                Ntxuva move = ntxuva.move(p);
                                
                                suc.add(new Sucessor(move, p));
//                            } else {
//                                System.out.println("=========Cyclte ========");
//                                System.out.println(ntxuva);
//                                System.out.println("P: " + new Position(i, j));
//                                System.out.println("=================");
//                            }
                        }
                    }
                }
            }
        }
        return suc;
    }

    public boolean mayerTest(Ntxuva ntxuva, char turn) {

//        boolean resultA = isMayerTestA(ntxuva, turn) || (!isMayerTestB(ntxuva, turn) || !isMayerTestC(ntxuva, turn) || !isMayerTestD(ntxuva, turn));
//        boolean resultB = isMayerTestB(ntxuva, turn) || (!isMayerTestA(ntxuva, turn) || !isMayerTestD(ntxuva, turn));
//        boolean resultC = isMayerTestC(ntxuva, turn) || (!isMayerTestA(ntxuva, turn) || !isMayerTestB(ntxuva, turn) || !isMayerTestD(ntxuva, turn));
//        boolean resultD = isMayerTestD(ntxuva, turn) || (!isMayerTestA(ntxuva, turn) || !isMayerTestB(ntxuva, turn) || !isMayerTestC(ntxuva, turn));
//
//        return resultA && resultB && resultC && resultD;
        return false;

    }

    public boolean isMayerTestA(Ntxuva ntxuva, char turn) {

        int startRow = (turn == 'x') ? Ntxuva.ROW_ZERO : Ntxuva.ROW_TWO;
        int finishRow = (turn == 'x') ? Ntxuva.ROW_TWO : Ntxuva.ROWS;

        boolean result = true;
        for (int i = startRow; i < finishRow; i++) {
            for (int j = 0; j < Ntxuva.COLUMNS; j++) {

                int seedsInHole = ntxuva.board[i][j];
                Position p = new Position(i, j);

                /* 
                  Mayer Test A:
                    Number of seed in hole(i) != i + 1
                 */
//                System.out.println(seedsInHole + "!=" + (p.getPositionId() + 1));
                result = result && (seedsInHole != (p.getPositionId() + 1));
                if (!result) {
//                     System.out.println("i: "+i+" j:"+j);
//                    return result;
                }
            }
        }

        return result;
    }

    public boolean isMayerTestB(Ntxuva ntxuva, char turn) {

        int startRow = (turn == 'x') ? Ntxuva.ROW_ZERO : Ntxuva.ROW_TWO;
        int finishRow = (turn == 'x') ? Ntxuva.ROW_TWO : Ntxuva.ROWS;

//        System.out.println("");
        boolean result = true;
        for (int i = startRow; i < finishRow; i++) {
            for (int j = 0; j < Ntxuva.COLUMNS; j++) {

                int seedsInHole = ntxuva.board[i][j];
                Position p = new Position(i, j);

                /* 
                  Mayer Test B:
                    Number of seed in hole(i) != i - 1
                 */
//                System.out.println(seedsInHole + "!=" + (p.getPositionId() - 1));
                result = result && (seedsInHole != (p.getPositionId() - 1));
                if (!result) {
//                    System.out.println("i: "+i+" j:"+j);
//                    return result;
                }
            }
        }

        return result;
    }

    public boolean isMayerTestC(Ntxuva ntxuva, char turn) {

        boolean result = true;
        /* 
                  Mayer Test C:
                   y(i+j) != y(i)+j+1
         */

        for (int i = 0; i < Ntxuva.COLUMNS * 2; i++) {
            for (int j = 0; j < Ntxuva.COLUMNS * 2; j++) {
                int k = i + j;

                if (k < Ntxuva.COLUMNS * 2) {
                    Position pk = new Position().getPositonById(k, turn);
                    Position pi = new Position().getPositonById(i, turn);

                    int seedsPosK = ntxuva.board[pk.row][pk.column];
                    int seedsPosI = ntxuva.board[pi.row][pi.column];

//                    System.out.println("i: " + i + " j: " + j + " => " + seedsPosK + "!=" + ((seedsPosI) + j + 1));
                    result = result && (seedsPosK != ((seedsPosI) + j + 1));
                    if (!result) {
//                        System.out.println("i: "+i+" j:"+j);
//                        return result;
                    }
                }

            }
        }

        return result;
    }

    public boolean isMayerTestD(Ntxuva ntxuva, char turn) {

        boolean result = true;
        /* 
                  Mayer Test C:
                   y(i+j) != y(i)+j+1
         */

        for (int i = 0; i < Ntxuva.COLUMNS * 2; i++) {
            for (int j = 0; j < Ntxuva.COLUMNS * 2; j++) {
                int k = i + j;

                if (k < Ntxuva.COLUMNS * 2) {
                    Position pk = new Position().getPositonById(k, turn);
                    Position pi = new Position().getPositonById(i, turn);

                    int seedsPosK = ntxuva.board[pk.row][pk.column];
                    int seedsPosI = ntxuva.board[pi.row][pi.column];

//                    System.out.println("i: " + i + " j: " + j + " => " + seedsPosK + "!=" + ((seedsPosI) + j - 1));
                    result = result && (seedsPosK != ((seedsPosI) + j - 1));
                    if (!result) {
//                        System.out.println("i: "+i+" j:"+j);
//                        return result;
                    }
                }

            }
        }

        return result;
    }
}
