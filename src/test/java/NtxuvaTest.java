/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import mz.com.osoma.ntxuva.Ntxuva;
import mz.com.osoma.ntxuva.Position;
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

    @Test
    public void testPrint() {

        Ntxuva ntxuva = new Ntxuva();

        String out = "Turn: x\n";
        out += "2 2 2 2 2 2 \n2 2 2 2 2 2 \n2 2 2 2 2 2 \n2 2 2 2 2 2 \n";
        assertEquals(out, ntxuva.toString());

    }

    @Test
    public void testMoveOnXSide() {

        System.out.println("move x");
        Ntxuva ntxuva = new Ntxuva();
        ntxuva = ntxuva.move(new Position(1, 0));
        System.out.println("se fini");
        String out = "Turn: o\n";
        out += "0 3 3 0 3 3 \n1 4 1 3 3 0 \n2 2 0 2 2 2 \n2 2 0 2 2 2 \n";
        assertEquals(out, ntxuva.toString());
        
    }

    @Test
    public void testHasMoreThanOnePiece() {
        Ntxuva ntxuva = new Ntxuva(1);
//        assertFalse(ntxuva.moreThanOnePiece(new Position(0, 0)));
        assertFalse(ntxuva.moreThanOnePiece('x'));

        Ntxuva n = new Ntxuva(2);
//        assertTrue(n.moreThanOnePiece(new Position(0, 0)));
        assertFalse(ntxuva.moreThanOnePiece('x'));

    }

    @Test
    public void testIsOpponetSide() {
        Position p = new Position(0, 0);
        assertFalse(p.isOpponetSide());

        p.setRow(1);
        p.setColumn(5);
        assertFalse(p.isOpponetSide());

        p.setRow(2);
        p.setColumn(0);
        assertTrue(p.isOpponetSide());

        p.setRow(2);
        p.setColumn(5);
        assertTrue(p.isOpponetSide());

    }

    @Test
    public void testMoveOnOSide() {

        Ntxuva ntxuva = new Ntxuva();
        ntxuva = ntxuva.move(new Position(1, 0)).move(new Position(2, 0));
        String out = "Turn: x\n";
        out += "0 3 3 0 3 3 \n1 4 1 3 3 0 \n1 0 1 3 0 3 \n4 1 1 3 0 3 \n";
        assertEquals(out, ntxuva.toString());
    }

    @Test
    public void testWin() {

        String[] s = {"000000", "000000", "222222", "122222"};
        Ntxuva ntxuva = new Ntxuva(s);
        assertTrue(ntxuva.win('o'));

        String[] str = {"222222", "222222", "000000", "000000"};
        ntxuva = new Ntxuva(str);
        assertTrue(ntxuva.win('x'));

        String[] str1 = {"000010", "000000", "000010", "000000"};
        ntxuva = new Ntxuva(str1);
        assertTrue(ntxuva.win('o'));

        String[] str2 = {"000000", "000010", "000000", "000001"};
        ntxuva = new Ntxuva(str2);
        assertFalse(ntxuva.win('o'));

        String[] str3 = {"000001", "000000", "010000", "000000"};
        ntxuva = new Ntxuva(str3);
        assertFalse(ntxuva.win('x'));

        String[] str4 = {"000100", "000000", "000010", "000000"};
        ntxuva = new Ntxuva(str4);
        assertFalse(ntxuva.win('x'));

        String[] str5 = {"000000", "000100", "000000", "000010"};
        ntxuva = new Ntxuva(str5);
        assertFalse(ntxuva.win('x'));

        String[] str6 = {"000000", "000001", "000000", "000001"};
        ntxuva = new Ntxuva(str6);
        assertTrue(ntxuva.win('x'));

        String[] str7 = {"000001", "000000", "000001", "000000"};
        ntxuva = new Ntxuva(str7);
        assertTrue(ntxuva.win('x'));

    }

    @Test
    public void testSum() {

        String[] s = {"000000", "000001", "100000", "000000"};
        Ntxuva ntxuva = new Ntxuva(s);

        assertEquals(1, ntxuva.sumPieces('x'));
        assertEquals(1, ntxuva.sumPieces('o'));

        String[] str = {"000000", "000201", "100000", "004000"};
        Ntxuva ntxuva1 = new Ntxuva(str);

        assertEquals(3, ntxuva1.sumPieces('x'));
        assertEquals(5, ntxuva1.sumPieces('o'));

    }

}
