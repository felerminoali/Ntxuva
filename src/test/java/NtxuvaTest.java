/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import mz.com.osoma.ntxuva.MiniMax;
import mz.com.osoma.ntxuva.Ntxuva;
import mz.com.osoma.ntxuva.Position;
import mz.com.osoma.ntxuva.Sucessor;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author feler
 */
public class NtxuvaTest {

    public NtxuvaTest() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

//    @Test
//    public void testPrint() {
//
//        Ntxuva ntxuva = new Ntxuva();
//
//        String out = "Turn: x\n";
//        out += "2 2 2 2 2 2 \n2 2 2 2 2 2 \n2 2 2 2 2 2 \n2 2 2 2 2 2 \n";
//        assertEquals(out, ntxuva.toString());
//
//    }
//
//    @Test
//    public void testMoveOnXSide() {
//
//        System.out.println("move x");
//        Ntxuva ntxuva = new Ntxuva();
//        ntxuva = ntxuva.move(new Position(1, 0));
//        System.out.println("se fini");
//        String out = "Turn: o\n";
//        out += "0 3 3 0 3 3 \n1 4 1 3 3 0 \n2 2 0 2 2 2 \n2 2 0 2 2 2 \n";
//        assertEquals(out, ntxuva.toString());
//
//    }
//
//    @Test
//    public void testHasMoreThanOnePiece() {
//        Ntxuva ntxuva = new Ntxuva(1);
//        assertFalse(ntxuva.moreThanOnePiece('x'));
//
//        Ntxuva n = new Ntxuva(4);
//        assertTrue(n.moreThanOnePiece('x'));
//    }
//
//    @Test
//    public void testIsOpponetSide() {
//        Position p = new Position(0, 0);
//        assertFalse(p.isOpponetSide());
//
//        p.setRow(1);
//        p.setColumn(5);
//        assertFalse(p.isOpponetSide());
//
//        p.setRow(2);
//        p.setColumn(0);
//        assertTrue(p.isOpponetSide());
//
//        p.setRow(2);
//        p.setColumn(5);
//        assertTrue(p.isOpponetSide());
//
//    }
//
//    @Test
//    public void testMoveOnOSide() {
//
//        Ntxuva ntxuva = new Ntxuva();
//        ntxuva = ntxuva.move(new Position(1, 0)).move(new Position(2, 0));
//        String out = "Turn: x\n";
//        out += "0 3 3 0 3 3 \n1 4 1 3 3 0 \n1 0 1 3 0 3 \n4 1 1 3 0 3 \n";
//        assertEquals(out, ntxuva.toString());
//    }
//
//    @Test
//    public void testWin() {
//
//    }
//
//    @Test
//    public void testSum() {
//
//        String[] s = {"000000", "000001", "100000", "000000"};
//        Ntxuva ntxuva = new Ntxuva(s);
//
//        assertEquals(1, ntxuva.sumPieces('x'));
//        assertEquals(1, ntxuva.sumPieces('o'));
//
//        String[] str = {"000000", "000201", "100000", "004000"};
//        Ntxuva ntxuva1 = new Ntxuva(str);
//
//        assertEquals(3, ntxuva1.sumPieces('x'));
//        assertEquals(5, ntxuva1.sumPieces('o'));
//
//    }
//
//    @Test
//    public void testPossibleMoves() {
//
//        String[] s = {"200030", "100000", "111222", "122000"};
//        Ntxuva ntxuva = new Ntxuva(s);
//
//        ArrayList<Sucessor> posibleMoves1 = new MiniMax().possibleMoves(ntxuva, 'o');
//
////        System.out.println(posibleMoves1);
//    }
//
//    @Test
//    public void testMiniMaxDecision() {
//
//        String[] s = {"100000", "010000", "001010", "000000"};
//        Ntxuva ntxuva = new Ntxuva(s, 'o');
//        Position p = new MiniMax().decisao_minimax(ntxuva);
//        assertEquals("[2, 2]", p.toString());
//
//    }
//    @Test
//    public void testMoveAntiClockWise() {
//
//        assertEquals("[1, 0]", new Position(0, 0).moveAntiClockWise().toString());
//        assertEquals("[0, 5]", new Position(1, 5).moveAntiClockWise().toString());
//    }
//    
//    @Test
//    public void testMoveClockWise() {
//        assertEquals("[0, 1]", new Position(0, 0).moveClockWise().toString());
//        assertEquals("[0, 0]", new Position(1, 0).moveClockWise().toString());
//        assertEquals("[1, 5]", new Position(0, 5).moveClockWise().toString());
//        
//        assertEquals("[2, 0]", new Position(3, 0).moveClockWise().toString());
//        assertEquals("[3, 5]", new Position(2, 5).moveClockWise().toString());
//        assertEquals("[2, 2]", new Position(2, 1).moveClockWise().toString());
//        
//        
//    }
    @Test
    public void testPositionId() {

        assertEquals(1, new Position(0, 0).getPositionId());
        assertEquals(2, new Position(0, 1).getPositionId());
        assertEquals(3, new Position(0, 2).getPositionId());
        assertEquals(6, new Position(2, 5).getPositionId());

    }

    @Test
    public void testMayersA() {

        int[][] b = {
            {1,2,1,0,1,4,3,1},
            {10,0,3,0,1,0,1,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
        };
        Ntxuva ntxuva = new Ntxuva(b,'x');
        
        assertTrue(new MiniMax().isMayerTestA(ntxuva, 'x'));
    }

    
     @Test
    public void testMayersB() {

        
        int[][] b = {
            {1,2,1,0,1,4,3,1},
            {10,0,3,0,1,0,1,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
        };
        Ntxuva ntxuva = new Ntxuva(b,'x');
        
        assertTrue(new MiniMax().isMayerTestB(ntxuva, 'x'));
    }
}
