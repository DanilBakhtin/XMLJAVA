package Main;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;

public class ValidatorSAX {

    String filename;
    String schemaname;
    SchemaFactory factory;
    File schemaLocation;

    /**
     * @param pathXML - путь к xml файлу
     * @param pathXSD - путь к xsd файлу
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     */
    public ValidatorSAX (String pathXML, String pathXSD) throws IOException, SAXException {

        this.filename = pathXML;
        this.schemaname = pathXSD;
        this.factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        this.schemaLocation = new File(schemaname);

    }

    /**
     * Функция проверающая на целостность
     * @return true или false
     * @throws IOException
     * @throws SAXException
     */
    public boolean isValid() throws IOException, SAXException {
        try {

            // создание схемы
            Schema schema = factory.newSchema(schemaLocation);
            // создание валидатора на основе схемы
            Validator validator = schema.newValidator();
            // проверка документа
            Source source = new StreamSource(filename);
            validator.validate(source);
            System.out.println(filename + " is valid.");
            return true;

        } catch (SAXException e) {
            System.err.println(filename + " SAX error: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            return false;
        }

    }

}
