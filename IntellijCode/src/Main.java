import controllayer.InventoryController;
import modellayer.Customer;
import modellayer.Location;
import modellayer.SaleLineItem;
import modellayer.containers.CustomerCont;
import tuilayer.MainMenu;

public class Main {
    public static void main(String[] args) {
//
//        Employee em = new Employee("Cunty McCuntFace", "on the top of the world", 0, "cisco");
//        Employee em2 = new Employee("Cunty McCuntFace", "on the top of the world", 5, "cisco");
//
//        EmployeeCont.getInstance().put("cunt", em);
//        EmployeeCont.getInstance().put("cunty", em2);
//
//        System.out.println(
//                new EmployeeController().toStringAll());

        Customer customer = new Customer("Asd", "Asd", "Asd", "Asd", 20);
        CustomerCont.getInstance().put(CustomerCont.getInstance().getID(), customer);

        InventoryController inventoryController = new InventoryController();
        inventoryController.createItem("1", "ASd", "ASd", 123.4, 99.9);

        SaleLineItem saleLineItem = inventoryController.getSaleLineItem("1");
        saleLineItem.addStock(1, Location.DIY);

        new MainMenu();
    }
}
