package edu.ntut.user.myhw3;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by YEE on 2018/3/28.
 */

public class MarriageSuggestionTest {
    @Test
    public void getSuggestion()
    {
        MarriageSuggestion marriageSuggestion = new MarriageSuggestion();
        String s = marriageSuggestion.getSuggestion("male",1,2);
        assertEquals(s,"建議：趕快結婚");

        s = marriageSuggestion.getSuggestion("male",1,5);
        assertEquals(s,"建議：趕快結婚");

        s = marriageSuggestion.getSuggestion("male",1,20);
        assertEquals(s,"建議：還不急");
        //======
        s = marriageSuggestion.getSuggestion("male",2,2);
        assertEquals(s,"建議：趕快結婚");

        s = marriageSuggestion.getSuggestion("male",2,5);
        assertEquals(s,"建議：開始找對象");

        s = marriageSuggestion.getSuggestion("male",2,20);
        assertEquals(s,"建議：還不急");
        //======
        s = marriageSuggestion.getSuggestion("male",3,2);
        assertEquals(s,"建議：開始找對象");

        s = marriageSuggestion.getSuggestion("male",3,5);
        assertEquals(s,"建議：趕快結婚");

        s = marriageSuggestion.getSuggestion("male",3,20);
        assertEquals(s,"建議：開始找對象");

        //==============================================================================

        s = marriageSuggestion.getSuggestion("female",1,2);
        assertEquals(s,"建議：趕快結婚");

        s = marriageSuggestion.getSuggestion("female",1,5);
        assertEquals(s,"建議：趕快結婚");

        s = marriageSuggestion.getSuggestion("female",1,20);
        assertEquals(s,"建議：還不急");
        //======
        s = marriageSuggestion.getSuggestion("female",2,2);
        assertEquals(s,"建議：趕快結婚");

        s = marriageSuggestion.getSuggestion("female",2,5);
        assertEquals(s,"建議：開始找對象");

        s = marriageSuggestion.getSuggestion("female",2,20);
        assertEquals(s,"建議：還不急");
        //======
        s = marriageSuggestion.getSuggestion("female",3,2);
        assertEquals(s,"建議：開始找對象");

        s = marriageSuggestion.getSuggestion("female",3,5);
        assertEquals(s,"建議：趕快結婚");

        s = marriageSuggestion.getSuggestion("female",3,20);
        assertEquals(s,"建議：開始找對象");
    }
}
