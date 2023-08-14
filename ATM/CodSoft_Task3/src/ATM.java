import javax.swing.*;

public class ATM {
    private BankAccount accUser;

    public ATM(BankAccount acc) {
        this.accUser = acc;
    }

    public void run() {
        while (true) {
            int choice = Integer.parseInt(JOptionPane.showInputDialog(
                    "ATM Menu:\n" +
                            "1. Check Balance\n" +
                            "2. Deposit\n" +
                            "3. Withdraw\n" +
                            "4. Exit\n" +
                            "Enter your choice: "
            ));

            switch (choice) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Your balance is: Rs." + accUser.getBalance());
                    break;
                case 2:
                    double depositAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter deposit amount: Rs."));
                    accUser.deposit(depositAmount);
                    JOptionPane.showMessageDialog(null, "Deposited Rs." + depositAmount + " successfully.");
                    break;
                case 3:
                    double withdrawAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter withdrawal amount: Rs."));
                    if (accUser.withdraw(withdrawAmount)) {
                        JOptionPane.showMessageDialog(null, "Withdrawn Rs." + withdrawAmount + " successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                    }
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Thanks for using our ATM service...Come Again");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        double iniBal = Double.parseDouble(JOptionPane.showInputDialog("Enter initial account balance: Rs."));
        BankAccount acc = new BankAccount(iniBal);
        ATM atm = new ATM(acc);
        atm.run();
    }
}


