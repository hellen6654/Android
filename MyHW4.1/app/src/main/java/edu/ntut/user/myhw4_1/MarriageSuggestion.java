package edu.ntut.user.myhw4_1;


public class MarriageSuggestion
{
    public String getSuggestion(int iAgeRange)
    {

        String strSug = "建議：";
        switch (iAgeRange)
        {
            case 1:
                strSug += "還不急";
                break;
            case 2:
                strSug += "趕快結婚";
                break;
            case 3:
                strSug += "開始找對象";
                break;
        }
        return strSug;
    }
}
