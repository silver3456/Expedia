package tests.alex_kaiser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    public static void main(String args[]) {

//        String pattern = "yyyy-MM-dd";
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String dateFormat = simpleDateFormat.format(new Date());
        System.out.println(dateFormat);

        File file  = new File("./output.txt");
    }
}
