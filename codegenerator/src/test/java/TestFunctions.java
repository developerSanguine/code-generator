import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class TestFunctions {

    public static void main(String[] args){

        convertStringToOffsetDateTime("2023-03-31T12:27:29+05:30");
    }


    public static void test(){
        String code = "G";
        long lastNo = 1;

        String masterName = "Menu Item";

        /*StringBuilder master = new StringBuilder(masterName.trim());
        String c = master.append("Controller").toString();
        String s = master.append("Service").toString();

        System.out.println("Master name = "+masterName);
        System.out.println("Master name SB = "+master);
        System.out.println("Service Master name = "+c);
        System.out.println("Controller Master name = "+s);*/


        String c = masterName.concat("Controller").replaceAll(" ","");
        String s = masterName.concat("Service").replaceAll(" ","");;

        System.out.println("Master name = "+masterName);
        System.out.println("Service Master name = "+c);
        System.out.println("Controller Master name = "+s);

        System.out.println("\n\n");

        String columnName = "strGroupCode";

        String field = columnName.substring(3);
        System.out.println(field);


        field = "strGroupCode";
        String fieldName = field;
        if(field.length() > 3 && (field.startsWith("str")
                || field.startsWith("dbl")
                || field.startsWith("int")
                || field.startsWith("dte"))) {
            fieldName = field.substring(3);
        }

        System.out.println("field = "+fieldName);
    }


    public static OffsetDateTime convertStringToOffsetDateTime(String dateTime){

        /*DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss.nnn VV");*/

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;


        // 2. parse date/time in String to default OffsetDateTime format
        OffsetDateTime offsetDateTime = OffsetDateTime
                .parse(dateTime, dateTimeFormatter);

        System.out.println(offsetDateTime);

        return offsetDateTime;
    }
}
