package com.paperscissorsstonegame;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
public class ComputerMoraTest {
    @Test
    public void getMoraResult()
    {
        ComputerMora cm = new ComputerMora();
        //1 – 剪刀, 2 – 石頭, 3 – 布.
        //平手 - 0  玩家輸 - 1 玩家贏 - 2
        int i = cm.getMoraResult(1,1);
        assertEquals(i,0);

        i = cm.getMoraResult(1,2);
        assertEquals(i,2);

        i = cm.getMoraResult(1,3);
        assertEquals(i,1);

        //==================================
        i = cm.getMoraResult(2,1);
        assertEquals(i,1);

        i = cm.getMoraResult(2,2);
        assertEquals(i,0);

        i = cm.getMoraResult(2,3);
        assertEquals(i,2);

        //==================================
        i = cm.getMoraResult(3,1);
        assertEquals(i,2);

        i = cm.getMoraResult(3,2);
        assertEquals(i,1);

        i = cm.getMoraResult(3,3);
        assertEquals(i,0);

    }
}
