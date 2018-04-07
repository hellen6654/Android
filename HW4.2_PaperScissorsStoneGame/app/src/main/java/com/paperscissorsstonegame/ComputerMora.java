package com.paperscissorsstonegame;

import android.content.res.Resources;

public class ComputerMora {
    public int getMoraResult(int com,int player)
    {
        int result=81000;

        //1 – 剪刀, 2 – 石頭, 3 – 布.
        if(player==1) // 玩家出剪刀
        {
            if(com==1) //電腦出剪刀
                 result=0; //平手 - 0
            else if (com==2) //電腦出石頭
                result=1; //玩家輸 - 1
            else if (com==3) // 電腦出布
                result=2;// 玩家贏 - 2
        }
        else if (player==2) //玩家出石頭
        {
            if(com==1) //電腦出剪刀
                result=2;// 玩家贏 - 2
            else if (com==2) //電腦出石頭
                result=0; //平手 - 0
            else if (com==3) // 電腦出布
                result=1; //玩家輸 - 1
        }
        else //玩家出布
        {
            if(com==1) //電腦出剪刀
                result=1; //玩家輸 - 1
            else if (com==2) //電腦出石頭
                result=2;// 玩家贏 - 2
            else if (com==3) // 電腦出布
                result=0; //平手 - 0
        }
        return result;
    }
}
