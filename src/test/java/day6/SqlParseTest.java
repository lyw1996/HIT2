package day6;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SqlParseTest {


    SqlParse sqlParse=new SqlParse();

    //单一表达式
    @Test
    public void testEqCondition() {
        String expected = "select * from table where companyname = ernst handel";
        Assert.assertEquals(expected, sqlParse.parse("companyName == Ernst Handel"));
    }

    @Test
    public void testNotEqCondition() {
        String expected = "select * from table where companyname <> ernst handel";
        Assert.assertEquals(expected, sqlParse.parse("companyName != Ernst Handel"));
    }

    //not表达式
    @Test
    public void testNotCondition() {
        String expected = "select * from table where not id = picco";
        Assert.assertEquals(expected, sqlParse.parse("!(id == PICCO)"));
    }

    //两个表达式 一个and
    @Test
    public void testAND1() {
        String expected = "select * from table where (companyname <> ernst handel) and (id = picco)";
        Assert.assertEquals(expected, sqlParse.parse("(companyName != Ernst Handel) and (id == PICCO)"));
    }
    @Test
    public void testNotAND1() {
        String expected = "select * from table where not (companyname <> ernst handel) and (id = picco)";
        Assert.assertEquals(expected, sqlParse.parse("!(companyName != Ernst Handel) and (id == PICCO)"));
    }

    //三个表达式 两个and
    @Test
    public void testAND2() {
        String expected = "select * from table where (companyname = ernst handel) and ((id = picco) and (contacttitle <> manager))";
        Assert.assertEquals(expected, sqlParse.parse("(companyName == Ernst Handel) and (id == PICCO)and (ContactTitle != Manager)"));
    }

    //两个表达式 一个or
    @Test
    public void testOR1() {//只包含and连接符
        String expected = "select * from table where (companyname <> ernst handel) or (id = picco)";
        Assert.assertEquals(expected, sqlParse.parse("(companyName != Ernst Handel) or (id == PICCO)"));
    }


    //三个表达式 两个or

    @Test
    public void testOR2() {
        String expected ="select * from table where (companyname = ernst handel) or ((id = picco) or (contacttitle <> manager))";
        Assert.assertEquals(expected, sqlParse.parse("(companyName == Ernst Handel) or (id == PICCO) or (ContactTitle != Manager)"));
    }

    //三个表达式 一个or 一个and
    @Test
    public void testAndOR() {
        String expected = "select * from table where (companyname = ernst handel) and ((id = picco) or (contacttitle <> manager))";
        Assert.assertEquals(expected, sqlParse.parse("(companyName == Ernst Handel) and (id == PICCO) or (ContactTitle != Manager)"));
    }
}
