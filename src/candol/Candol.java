package candol;

import java.util.Scanner;

public class Candol {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("Enter action: ");
            
            int action = sc.nextInt();
            Candol customer = new Candol();
            
            switch (action) {
                case 1:
                    customer.addEvent(sc);
                    break;
                case 2:
                    customer.viewEvent();
                    break;
                case 3:
                    customer.viewEvent();
                    customer.updateEvent(sc);
                    customer.viewEvent();
                    break;
                case 4:
                    customer.deleteEvent(sc);
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
                    break;
            }
            
            System.out.println("Do you want to continue? (yes/no)");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));
        
        System.out.println("Thank you, see you soon!");
        sc.close();
    }
    
    public void addEvent(Scanner sc) {
        config conf = new config();
        
        System.out.print("Event Name: ");
        String ename = sc.next();
        System.out.print("Event Date: ");
        String edate = sc.next();
        System.out.print("Location: ");
        String location = sc.next();
        System.out.print("Description: ");
        String description = sc.next();
        System.out.print("Organizer: ");
        String organizer = sc.next();

        String sql = "INSERT INTO tbl_events (e_eventname, e_eventdate, e_location, e_description, e_organizer) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, ename, edate, location, description, organizer);
    }

    private void viewEvent() {
        config conf = new config();
        String votersQuery = "SELECT * FROM tbl_events";
        String[] votersHeaders = {"Event ID", "Event Name", "Event Date", "Location", "Description", "Organizer"};
        String[] votersColumns = {"e_id", "e_eventname", "e_eventdate", "e_location", "e_description", "e_organizer"};

        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
    }

    private void updateEvent(Scanner sc) {
        System.out.println("Enter Event ID to update: ");
        int id = sc.nextInt();
        
        System.out.println("New Event Name: ");
        String nedate = sc.next();
        System.out.println("New Event Date: ");
        String nlocation = sc.next();
        System.out.println("New Description: ");
        String ndescription = sc.next();
        System.out.println("New Organizer: ");
        String norganizer = sc.next();

        String qry = "UPDATE tbl_events SET e_eventname = ?, e_eventdate = ?, e_location = ?, e_description = ?, e_organizer = ? WHERE e_id = ?";
        
        config conf = new config();
        conf.updateRecord(qry, nedate, nlocation, ndescription, norganizer, id);
    }

    private void deleteEvent(Scanner sc) {
        System.out.println("Enter ID to delete: ");
        int id = sc.nextInt();
        
        String qry = "DELETE FROM tbl_events WHERE e_id = ?";
        
        config conf = new config();
        conf.deleteRecord(qry, id);
    }
}
