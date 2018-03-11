/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.ntxuva;

/**
 *
 * @author feler
 */
public class Ntxuva {

    static final int COLUMNS = 8;
    static final int ROWS = 4;
    static final int ROW_ZERO = 0;
    static final int ROW_ONE = 1;
    static final int ROW_TWO = 2;
    static final int ROW_TREE = 3;

    public char turn;

    public int[][] board;

    public Ntxuva() {
        reset(2);
    }

    public Ntxuva(String[] strBoard) {

        this.turn = 'x';
        if (ROWS == strBoard.length) {
            char characters[][] = new char[ROWS][COLUMNS];

            for (int i = 0; i < ROWS; i++) {
                characters[i] = strBoard[i].toCharArray();
            }

            this.board = new int[ROWS][COLUMNS];
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    this.board[i][j] = Integer.parseInt(characters[i][j] + "");
                }
            }
        }

    }

    public Ntxuva(String[] strBoard, char t) {

        if (ROWS == strBoard.length) {
            char characters[][] = new char[ROWS][COLUMNS];

            for (int i = 0; i < ROWS; i++) {
                characters[i] = strBoard[i].toCharArray();
            }

            this.board = new int[ROWS][COLUMNS];
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    this.board[i][j] = Integer.parseInt(characters[i][j] + "");
                }
            }

            this.turn = t;
        }

    }

    public Ntxuva(int pieces) {
        reset(pieces);
    }

    public Ntxuva(int[][] board, char t) {
        this.board = board;
        this.turn = t;
    }

    Ntxuva(Ntxuva ntxuva) {

        this.board = ntxuva.board;
        this.turn = ntxuva.turn;

    }

    public final void reset(int pieces) {

        this.turn = 'x';
        this.board = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = pieces;
            }
        }

    }

    public void reset(int[][] b, char t) {

        this.turn = t;
        this.board = new int[ROWS][COLUMNS];
        for (int k = 0; k < ROWS; k++) {
            System.arraycopy(b[k], 0, board[k], 0, COLUMNS);
        }

    }

    public boolean moreThanOnePiece(char pTurn) {

        int startRow = (pTurn == 'x') ? ROW_ZERO : ROW_TWO;
        int finishRow = (pTurn == 'x') ? ROW_TWO : ROWS;

        for (int i = startRow; i < finishRow; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moreThanOnePiece(Position position, int[][] newBoard) {

        int startRow = (!position.isOpponetSide()) ? ROW_ZERO : ROW_TWO;
        int finishRow = (!position.isOpponetSide()) ? ROW_TWO : ROWS;
        for (int i = startRow; i < finishRow; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (newBoard[i][j] > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public char changeTurn() {
        return (this.turn == 'x') ? 'o' : 'x';
    }

    public Ntxuva move(Position start) {

        Position current = new Position(start.row, start.column);

        int[][] newBoard = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, COLUMNS);
        }

        if (newBoard[current.row][current.column] > 0) {

            int tempStones = 0;

            if (moreThanOnePiece(start, newBoard)) {

                if (newBoard[start.row][start.column] > 1) {

                    while (true) {
                        if (tempStones == 0 && newBoard[current.row][current.column] == 1) {

                            if (current.isAttackingPosition()) {
                                score(current, newBoard);
                            }

                            return new Ntxuva(newBoard, changeTurn());
                        }

                        if (tempStones == 0 && newBoard[current.row][current.column] > 0) {
                            tempStones = newBoard[current.row][current.column];
                            newBoard[current.row][current.column] = 0;
                        }

                        current.moveAntiClockWise();

                        newBoard[current.row][current.column] = newBoard[current.row][current.column] + 1;
                        tempStones--;
                    }
                }
            } else {

                if (newBoard[start.row][start.column] > 0) {

                    Position next = new Position(start.row, start.column).moveAntiClockWise();
                    if (newBoard[next.row][next.column] == 0) {

                        if (tempStones == 0 && newBoard[current.row][current.column] > 0) {
                            tempStones = newBoard[current.row][current.column];
                            newBoard[current.row][current.column] = 0;
                        }

                        current.moveAntiClockWise();

                        newBoard[current.row][current.column] = newBoard[current.row][current.column] + 1;
                        tempStones--;

                        if (tempStones == 0 && newBoard[current.row][current.column] == 1) {

                            if (current.isAttackingPosition()) {
                                score(current, newBoard);
                            }

                            return new Ntxuva(newBoard, changeTurn());
                        }
                    }
                }

            }
        }

        return this;

    }

    public boolean isInfitMove(Position start) {

        Position current = new Position(start.row, start.column);

        int[][] newBoard = new int[ROWS][COLUMNS];
        int[][] tempBoard = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, COLUMNS);
            System.arraycopy(board[i], 0, tempBoard[i], 0, COLUMNS);
        }

        if (newBoard[current.row][current.column] > 0) {

            int tempStones = 0;

            if (moreThanOnePiece(start, newBoard)) {

                if (newBoard[start.row][start.column] > 1) {

                    while (true) {
                        
//                        System.out.println("entrei");
                        if (tempStones == 0 && newBoard[current.row][current.column] == 1) {

                            if (current.isAttackingPosition()) {
                                score(current, newBoard);
                            }

                            return false;
                        }

                        if (tempStones == 0 && newBoard[current.row][current.column] > 0) {
                            tempStones = newBoard[current.row][current.column];
                            newBoard[current.row][current.column] = 0;
                        }

                        current.moveAntiClockWise();

                        newBoard[current.row][current.column] = newBoard[current.row][current.column] + 1;
                        tempStones--;
                        
                          boolean rsub = true;
                            int startRow = (!current.isOpponetSide()) ? ROW_ZERO : ROW_TWO;
                            int finishRow = (!current.isOpponetSide()) ? ROW_TWO : ROWS;
                            for (int i = startRow; i < finishRow; i++) {
                                for (int j = 0; (j < COLUMNS) && (rsub); j++) {
                                    rsub = rsub && (tempBoard[i][j] == newBoard[i][j]);
                                }
                            }
                            
//                            System.out.println(rsub);
//                            System.out.println(new Ntxuva(newBoard,this.turn));
                            if(rsub) return rsub;
                    }
                }
            } else {

                if (newBoard[start.row][start.column] > 0) {

                    Position next = new Position(start.row, start.column).moveAntiClockWise();
                    if (newBoard[next.row][next.column] == 0) {

                        if (tempStones == 0 && newBoard[current.row][current.column] > 0) {
                            tempStones = newBoard[current.row][current.column];
                            newBoard[current.row][current.column] = 0;
                        }

                        current.moveAntiClockWise();

                        newBoard[current.row][current.column] = newBoard[current.row][current.column] + 1;
                        tempStones--;

                        if (tempStones == 0 && newBoard[current.row][current.column] == 1) {

                            if (current.isAttackingPosition()) {
                                score(current, newBoard);
                            }

                        }
                    }
                }

            }
        }

        return false;

    }

    public int score(Position current, int[][] newBoard) {

        int totalScore = 0;

        if (!current.isOpponetSide()) {

            if (newBoard[ROW_TWO][current.column] > 0) {

                int i = ROW_TWO;
                while (i < ROWS) {

                    totalScore += newBoard[i][current.column];
                    newBoard[i][current.column] = 0;
                    i++;
                }
            }

        } else {

            if (newBoard[ROW_ONE][current.column] > 0) {
                int i = ROW_ZERO;
                while (i < ROW_TWO) {
                    totalScore += newBoard[i][current.column];
                    newBoard[i][current.column] = 0;
                    i++;
                }
            }
        }
        return totalScore;
    }

    public boolean win(char pTurn) {

//        if (sumPieces('x') == 1) {
//            
//            if(sumPieces('o') == 1){
//            
//            Position pX = getLastPiece('x');
//            Position pO = getLastPiece('o');
//
//            if (pTurn == 'o') {
//
//                if (pO.column <= pX.column) {
//                    return !pX.isAttackingPosition() && pO.isAttackingPosition();
//                } else {
//                    return !(pX.isAttackingPosition() && !pO.isAttackingPosition());
//                }
//            } else {
//                if (pO.column < pX.column) {
//                    return false;
//                } else if (pO.column == pX.column) {
//                    return true;
//                }
//                return false;
//            }
//        }
//        }
        int startRow = (pTurn == 'x') ? ROW_TWO : ROW_ZERO;
        int finishRow = (pTurn == 'x') ? ROWS : ROW_TWO;
        for (int i = startRow; i < finishRow; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public Position getLastPiece(char turn) {

        int startRow = (turn == 'x') ? ROW_ZERO : ROW_TWO;
        int finishRow = (turn == 'x') ? ROW_TWO : ROWS;

        Position p = new Position();
        for (int i = startRow; i < finishRow; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] > 0) {
                    return new Position(i, j);
                }
            }
        }

        return p;
    }

    public int sumPieces(char pTurn) {

        int startRow = (pTurn == 'x') ? ROW_ZERO : ROW_TWO;
        int finishRow = (pTurn == 'x') ? ROW_TWO : ROWS;

        int sum = 0;
        for (int i = startRow; i < finishRow; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] > 0) {
                    sum += board[i][j];
                }
            }
        }

        return sum;
    }

    public boolean gameEnd() {
        return win('x') || win('o');
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Turn: ").append(turn).append("\n");
        for (int[] board1 : board) {
            for (int j = 0; j < board1.length; j++) {
                str.append(board1[j]).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

}
