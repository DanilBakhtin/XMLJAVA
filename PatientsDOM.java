package Main;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class PatientsDOM {

    private DocumentBuilder docBuilder;
    private File fileXML;

    /**
     * @param pathXML - путь к xml файлу
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     */
    public PatientsDOM(String pathXML) throws SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            this.fileXML = new File(pathXML);
            // создание DOM-анализатора
            docBuilder = factory.newDocumentBuilder();
            //обработчик ошибок
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    /**
     * Метод для вывода информации из xml файла в консоль
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     */
    public void print() throws SAXException, IOException {
        try {

            // parsing XML-документа и нормализация
            Document doc = docBuilder.parse(fileXML);
            doc.getDocumentElement().normalize();
            //выводим root элемент
            System.out.println("\nКорневой элемент: " + doc.getDocumentElement().getNodeName());
            // получение списка дочерних элементов <patient>
            NodeList patient = doc.getElementsByTagName("patient");
            //выводим результаты
            System.out.println("Пациенты:\n");
            //получаем длину списка и выводим все эти элементы в цикле
            for (int i = 0; i < patient.getLength(); i++) {
                //берем i-го пациента списка
                Node elNodePatient = patient.item(i);
                // если его дочерние узлы типа ELEMENT_NODE
                if (elNodePatient.getNodeType() == elNodePatient.ELEMENT_NODE) {

                    Element elemPatient = (Element) elNodePatient;
                    //Получаем имя пациента и отправляем в NodeList
                    NodeList name = elemPatient.getElementsByTagName("name");
                    Element elName = (Element) name.item(0);
                    NodeList ResultNodeName = elName.getChildNodes();
                    //Получаем фамилию пациента и отправляем в NodeList
                    NodeList surnname = elemPatient.getElementsByTagName("surname");
                    Element elSurname = (Element) surnname.item(0);
                    NodeList ResultNodeSurnname = elSurname.getChildNodes();
                    //Получаем отчество пациента и отправляем в NodeList
                    NodeList patronymic = elemPatient.getElementsByTagName("patronymic");
                    Element elPatronymic = (Element) patronymic.item(0);
                    NodeList ResultNodePatronymic = elPatronymic.getChildNodes();
                    //Получаем день рождления пациента и отправляем в NodeList
                    NodeList NLbirth = elemPatient.getElementsByTagName("birthday");
                    Element elBirth = (Element) NLbirth.item(0);
                    NodeList ResultNodeBirth = elBirth.getChildNodes();
                    //Получаем номер полиса пациента и отправляем в NodeList
                    NodeList NLpolicy = elemPatient.getElementsByTagName("policynumber");
                    Element elpolicy = (Element) NLpolicy.item(0);
                    NodeList ResultNodePolicy = elpolicy.getChildNodes();

                    //Выводим значения нулевых элементов всех NodeList
                    System.out.println("ID пациента: " + elemPatient.getAttribute("id")
                            + "\nФИО: "
                            + ((Node) ResultNodeName.item(0)).getNodeValue() + " "
                            + ((Node) ResultNodeSurnname.item(0)).getNodeValue() + " "
                            + ((Node) ResultNodePatronymic.item(0)).getNodeValue() + " "
                            + "\nДата рождения: "
                            + ((Node) ResultNodeBirth.item(0)).getNodeValue()
                            + "\nНомер полиса: "
                            + ((Node) ResultNodePolicy.item(0)).getNodeValue());


                    //получаем все тесты, пройденные i-м пациентом
                    NodeList Test = elemPatient.getElementsByTagName("test");
                    //Вывод тестов i-ого пациента
                    for (int j = 0; j < Test.getLength(); j++) {
                        //берем j-й тест i-го пациента списка
                        Node NodeTest = Test.item(j);
                        // если его дочерние узлы типа ELEMENT_NODE
                        if (elNodePatient.getNodeType() == elNodePatient.ELEMENT_NODE) {
                            Element elemTest = (Element) NodeTest;
                            //Получаем дату прохождения теста
                            NodeList NLdate = elemTest.getElementsByTagName("date");
                            Element eldate = (Element) NLdate.item(0);
                            NodeList ResultNodeDate = eldate.getChildNodes();
                            //Получаем тип теста
                            NodeList NLtype = (NodeList) elemTest.getElementsByTagName("type");
                            Element eltype = (Element) NLtype.item(0);
                            NodeList ResultNodeType = eltype.getChildNodes();
                            //Получаем id лаборатории
                            NodeList NLlabID = elemTest.getElementsByTagName("idlab");
                            Element ellabID = (Element) NLlabID.item(0);
                            NodeList ResultNodeLabID = ellabID.getChildNodes();

                            //Вывод в консоль
                            System.out.println("\nTest №" + (j + 1)
                                    + "\nДата прохождения теста: "
                                    + ((Node) ResultNodeDate.item(0)).getNodeValue()
                                    + "\nТип теста: "
                                    + ((Node) ResultNodeType.item(0)).getNodeValue()
                                    + "\nID лаборатории: "
                                    + ((Node) ResultNodeLabID.item(0)).getNodeValue());

                        }
                    }

                    System.out.println("-----------------------------------------");

                }
            }
            //обработчик ошибок
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

}