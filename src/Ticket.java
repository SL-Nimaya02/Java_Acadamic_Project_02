import java.util.function.ToDoubleFunction;

public class Ticket {
    public static ToDoubleFunction<? super Ticket> setPrice;
    private int row;
    private int seat;
    private int price;
    private Person person;

    public Ticket(int row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void print() {
        System.out.println("\nFirst Name: " + person.getName());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Row Number: " + (row+1));
        System.out.println("Seat Number: " + (seat+1));
        System.out.println("Price:$ " + price);
    }

    public double setPrice() {
        return 0;
    }

    public void Print() {
    }
}
