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
public class Position {

    public int row;
    public int column;

    public Position() {
    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isOpponetSide() {
        return this.row > Ntxuva.ROW_ONE;
    }

    public boolean isAttackingPosition() {
        return (row == Ntxuva.ROW_ONE || row == Ntxuva.ROW_TWO);
    }

    public Position moveClockWise() {

        if (!this.isOpponetSide()) {
            this.column = this.row == Ntxuva.ROW_ZERO ? this.column + 1 : this.column - 1;
        } else {
            this.column = this.row == Ntxuva.ROW_TWO ? this.column + 1 : this.column - 1;
        }

        if (this.column == -1) {
            if (!this.isOpponetSide()) {
                this.row = Ntxuva.ROW_ZERO;
            } else {
                this.row = Ntxuva.ROW_TWO;
            }
            this.column = Ntxuva.ROW_ZERO;
        }

        if (this.column == Ntxuva.COLUMNS) {

            if (!this.isOpponetSide()) {
                this.row = Ntxuva.ROW_ONE;
            } else {
                this.row = Ntxuva.ROW_TREE;
            }

            this.column = Ntxuva.COLUMNS - 1;
        }

        return this;

    }

    public Position moveAntiClockWise() {

        if (!this.isOpponetSide()) {
            this.column = this.row == Ntxuva.ROW_ZERO ? this.column - 1 : this.column + 1;
        } else {
            this.column = this.row == Ntxuva.ROW_TWO ? this.column - 1 : this.column + 1;
        }

        if (this.column == -1) {
            if (!this.isOpponetSide()) {
                this.row = Ntxuva.ROW_ONE;
            } else {
                this.row = Ntxuva.ROW_TREE;
            }
            this.column = Ntxuva.ROW_ZERO;
        }

        if (this.column == Ntxuva.COLUMNS) {

            if (!this.isOpponetSide()) {
                this.row = Ntxuva.ROW_ZERO;
            } else {
                this.row = Ntxuva.ROW_TWO;
            }

            this.column = Ntxuva.COLUMNS - 1;
        }
        
        return this;
    }

    public int getPositionId() {

        int max = Ntxuva.COLUMNS * 2;

        int id = 0;

        Position p = (this.isOpponetSide()) ? new Position(3, 0) : new Position(1, 0);

        for (int i = 0; i < max; i++) {
            if (p.row == this.row && p.column == this.column) {
                return i;
            }
            p.moveClockWise();
        }

        return id;
    }

    public Position getPositonById(int id, char turn) {

        int max = Ntxuva.COLUMNS * 2;

        Position p = (turn == 'o') ? new Position(3, 0) : new Position(1, 0);

        for (int i = 0; i < max; i++) {
            if (id == i) {
                return p;
            }
            p.moveClockWise();
        }

        return p;
    }

    @Override
    public String toString() {
        return "[" + this.row + ", " + this.column + "]";
    }

    @Override
    public boolean equals(Object o) {
        Position other = (Position) o;
        return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.row;
        hash = 71 * hash + this.column;
        return hash;
    }
}
