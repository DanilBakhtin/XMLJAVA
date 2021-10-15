package Main;

import java.io.*;
import org.xml.sax.SAXException;

public class Main {



    public static void main(String[] args) {

        try {
            String pathXML = "patients.xml";
            String pathXSD = "patients.xsd";

            ValidatorSAX valid = new ValidatorSAX(pathXML, pathXSD);
            //Проверка на целостность
            boolean isValid = valid.isValid();

            if (isValid){
                //Вывод информации в xml файле в консоль
                PatientsDOM patientsDOM = new PatientsDOM(pathXML);
                patientsDOM.print();
            }

            //обработчик ошибок
        } catch (SAXException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
